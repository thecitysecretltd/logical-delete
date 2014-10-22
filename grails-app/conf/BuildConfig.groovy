grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"

grails.project.fork = false

grails.project.dependency.resolver = "maven" // or ivy
grails.project.dependency.resolution = {
	// inherit Grails' default dependencies
	inherits("global") {
		// uncomment to disable ehcache
		// excludes 'ehcache'
	}
	log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
	repositories {
		grailsCentral()
		mavenLocal()
		mavenCentral()

		//mavenRepo "http://repository.codehaus.org"
		//mavenRepo "http://download.java.net/maven/2/"
		//mavenRepo "http://repository.jboss.com/maven2/"
	}
	dependencies {

	}

	plugins {
		build(":release:3.0.1",
				":rest-client-builder:1.0.3") {
			export = false
		}

		test ":code-coverage:2.0.3-2"
	}
}

coverage {
	enabledByDefault = true
	xml = true
}