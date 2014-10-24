package com.nanlabs.grails.plugin.logicaldelete;

import org.hibernate.annotations.Filter;

import javax.persistence.MappedSuperclass;

/**
 * Created by poundera on 24/10/14.
 */
@MappedSuperclass
@Filter(name = "deletedFilter")
public abstract class LogicallyDeletableEntity
{


	public Boolean deleted = false;
}
