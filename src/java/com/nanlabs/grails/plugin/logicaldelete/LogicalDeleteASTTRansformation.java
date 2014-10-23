package com.nanlabs.grails.plugin.logicaldelete;

import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.AnnotationNode;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.PropertyNode;
import org.codehaus.groovy.ast.expr.AnnotationConstantExpression;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.control.CompilePhase;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.grails.compiler.injection.GrailsASTUtils;
import org.codehaus.groovy.transform.ASTTransformation;
import org.codehaus.groovy.transform.GroovyASTTransformation;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import java.lang.reflect.Modifier;

@GroovyASTTransformation(phase = CompilePhase.CANONICALIZATION)
public class LogicalDeleteASTTRansformation implements ASTTransformation{

	public final static String DELETED_FIELD_NAME = "deleted";
	public final static String LIST_DELETED_METHOD_NAME = "listDeleted";
	public final static int CLASS_NODE_ORDER = 1;

	@Override
	public void visit(ASTNode[] nodes, SourceUnit source)
	{
		if(!validate(nodes)) return;
		ClassNode classNode = (ClassNode)nodes[CLASS_NODE_ORDER];
		addDeletedProperty(classNode);
		addListDeletedMethod(classNode);
		implementDeletedDomainClassInterface(classNode);
	}

	private boolean validate(ASTNode[] nodes){
		return nodes != null && nodes[0] != null && nodes[1] != null;
	}

	private void addDeletedProperty(ClassNode node) {
		if(!GrailsASTUtils.hasOrInheritsProperty(node, DELETED_FIELD_NAME))
		{
			PropertyNode deleted = new PropertyNode(DELETED_FIELD_NAME,
					Modifier.PUBLIC,
					new ClassNode(Boolean.class),
					node,
					ConstantExpression.FALSE,
					null,
					null);

			AnnotationNode filter = new AnnotationNode(new ClassNode(Filter.class));
			filter.addMember("name", new ConstantExpression("deletedFilter"));
			filter.addMember("condition", new ConstantExpression("deleted = :deletedParam"));

			deleted.addAnnotation(filter);
			node.addProperty(deleted);
		}
	}
	
	private void addListDeletedMethod(ClassNode node)
	{
		AnnotationNode filterDef = new AnnotationNode(new ClassNode(FilterDef.class));
		filterDef.addMember("name", new ConstantExpression("deletedFilter"));

		AnnotationNode paramDef = new AnnotationNode(new ClassNode(ParamDef.class));
		paramDef.addMember("name", new ConstantExpression("deletedParam"));
		paramDef.addMember("type", new ConstantExpression("bool"));

		filterDef.addMember("parameters", new AnnotationConstantExpression(paramDef));
		node.addAnnotation(filterDef);
	}

	private void implementDeletedDomainClassInterface(ClassNode node){
		ClassNode iNode = new ClassNode(LogicalDeleteDomainClass.class);
		if(!iNode.implementsInterface(iNode)){
			node.addInterface(iNode);
		}
	}

}
