package com.nanlabs.grails.plugin.logicaldelete

import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.tools.ast.TransformTestHelper
import spock.lang.Specification

class LogicalDeleteASTTransformationSpec extends Specification
{
	void test()
	{
		setup:
		def file = new File("test/unit/com/nanlabs/grails/plugin/logicaldelete/LogicalDeleteTest.groovy")

		expect:
		file.exists()

		when:
		def invoker = new TransformTestHelper(new LogicalDeleteASTTRansformation(), CompilePhase.CANONICALIZATION)
		def clazz = invoker.parse(file)
		def test = clazz.newInstance()
		test.deleted = true

		then:
		test.deleted
	}

}
