import com.nanlabs.grails.plugin.logicaldelete.LogicalDeleteDomainClassEnhancer

class LogicalDeleteGrailsPlugin {
	def version = "0.3-SNAPSHOT"
	def grailsVersion = "2.4 > *"
	def title = "Logical Delete Plugin"
	def description = 'Allows you to do a logical deletion of domain classes'
	def documentation = "http://grails.org/plugin/logical-delete"

	def license = "APACHE"
	def organization = [name: "NaN Labs", url: "http://www.nan-labs.com/"]
	def developers = [
		[name: "Ezequiel Parada", email: "ezequiel@nan-labs.com"]
	]
	def issueManagement = [system: 'GITHUB', url: 'https://github.com/nanlabs/logical-delete/issues']
	def scm = [url: 'https://github.com/nanlabs/logical-delete']

	def doWithSpring = {

	}

	def doWithDynamicMethods = { ctx ->
		LogicalDeleteDomainClassEnhancer.enhance(application.domainClasses)

		application.domainClasses.each {
			Class clazz = it.clazz
			def ss = clazz.metaClass.'static'
			int four = 2 + 2
		}
	}
}
