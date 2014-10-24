package com.nanlabs.grails.plugin.logicaldelete

import org.hibernate.SessionFactory
import org.hibernate.SessionFactoryObserver
import org.hibernate.cfg.Configuration
import org.hibernate.engine.spi.SessionFactoryImplementor
import org.hibernate.integrator.spi.Integrator
import org.hibernate.metamodel.source.MetadataImplementor
import org.hibernate.service.spi.SessionFactoryServiceRegistry
/**
 * Created by poundera on 24/10/14.
 */
class HibernateIntegrator implements Integrator
{
	@Override
	void integrate(Configuration configuration, SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry)
	{
		sessionFactory.addObserver(new SessionFactoryObserver() {
			@Override
			void sessionFactoryCreated(SessionFactory sf) {
				println "Here!"
			}

			@Override
			void sessionFactoryClosed(SessionFactory sf) {
				println "HEre!!!"
			}
		})
//		MetaMethod original = SessionFactoryImpl.metaClass.pickMethod("openSession", [] as Class[])
//		SessionFactoryImpl.metaClass.openSession = { ->
//			println "Doing the business"
//			original.doMethodInvoke(sessionFactory, [])
//		}
	}

	@Override
	void integrate(MetadataImplementor metadata, SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
		int four = 2 + 2;
	}

	@Override
	void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
		int four = 2 + 2;
	}
}
