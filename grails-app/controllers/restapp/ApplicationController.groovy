package restapp

import grails.converters.JSON
import grails.core.GrailsApplication
import grails.plugins.*

class ApplicationController implements PluginManagerAware {

    GrailsApplication grailsApplication
    GrailsPluginManager pluginManager

    def index() {
        [grailsApplication: grailsApplication, pluginManager: pluginManager]
    }

    def show() {
        log.info("show() invoked")
        def response = ["message": "Nothing to show for user id : ${params.id}"]
        render response as JSON
    }
}
