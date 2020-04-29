package restapp

import grails.converters.JSON
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation

@Api(tags=["Faculty Endpoints"], description="Service to test collection", position=1)
class FacultyController {

    def facultyService

    @ApiOperation(value = "Get All Faculty", nickname = "/faculty/",
            produces = "application/json", consumes = "application/json", httpMethod = "GET")
    def index() {
        respond facultyService.list()
    }

    @ApiOperation(value = "Get Faculty by path Param", nickname = "/faculty/{id}",
            produces = "application/json", consumes = "application/json", httpMethod = "GET")
    @ApiImplicitParams([
            @ApiImplicitParam(name = "id",  paramType = "path", dataType = "string" ,required = true)])
    def show(String id) {
        def listFaculty = facultyService.list()
        def facultyFound
        for(Integer i=0; i<listFaculty.size(); i++){
            if(listFaculty[i]["facultyid"] == id) {
                facultyFound = listFaculty[i]
                break
            }
        }

        if(facultyFound!=null) {
            respond facultyFound
        } else {
            render([message: "No faculty with given id exists"] as JSON)
        }
    }

    @ApiOperation(value = "Get Faculty by query Param", nickname = "/faculty/facultyId?id={id}",
            produces = "application/json", consumes = "application/json", httpMethod = "GET")
    @ApiImplicitParams([
            @ApiImplicitParam(name = "id",  paramType = "query", dataType = "string" ,required = true)])
    def showQuery(String id) {
        println("Did i reach here (Query Param) ?? ")
        def listFaculty = facultyService.list()
        def facultyFound
        for(Integer i=0; i<listFaculty.size(); i++){
            if(listFaculty[i]["facultyid"] == id) {
                facultyFound = listFaculty[i]
                break
            }
        }
        if(facultyFound!=null) {
            respond facultyFound
        } else {
            render([message: "No faculty with given id exists"] as JSON)
        }
    }

    @ApiOperation(value = "Save Faculty", nickname = "/faculty/",
            produces = "application/json", consumes = "application/json", httpMethod = "POST")
    @ApiImplicitParams([
            @ApiImplicitParam(name = "faculty", paramType = "body", dataType = "restapp.Faculty", required = true)])
    def save(Faculty faculty) {
        facultyService.save(faculty)
        render([message: "Save Success"] as JSON)
    }

    @ApiOperation(value = "Delete a Faculty", nickname = "/faculty/{id}",
            produces = "application/json", httpMethod = "DELETE")
    @ApiImplicitParams([
            @ApiImplicitParam(name = "id",  paramType = "path", dataType = "string" ,required = true)])
    def delete(String id) {
        def listFaculty = facultyService.list()
        def facultyFound
        for(Integer i=0; i<listFaculty.size(); i++){
            if(listFaculty[i]["facultyid"] == id) {
                facultyFound = listFaculty[i]
                break
            }
        }
        if(facultyFound!=null) {
            facultyService.delete(facultyFound["id"])
            render([message: "SUCCESSFULLY DELETED THE FACULTY FROM OUR DB"] as JSON)
        } else {
            render([message: "No faculty with given id exists"] as JSON)
        }
    }

}
