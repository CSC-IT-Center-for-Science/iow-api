package com.csc.fi.ioapi.api.model;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import com.csc.fi.ioapi.config.EndpointServices;
import com.csc.fi.ioapi.utils.JerseyFusekiClient;
import com.hp.hpl.jena.query.DatasetAccessor;
import com.hp.hpl.jena.query.DatasetAccessorFactory;
import com.hp.hpl.jena.query.ParameterizedSparqlString;
import com.hp.hpl.jena.rdf.model.Model;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import javax.ws.rs.core.Response;
import org.apache.jena.iri.IRI;
import org.apache.jena.iri.IRIException;
import org.apache.jena.iri.IRIFactory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author malonen
 */

 
/**
 * Root resource (exposed at "classCreator" path)
 */
@Path("classVisualizer")
@Api(value = "/classVisualizer", description = "Construct new Class template")
public class ClassVisualizer {

    @Context ServletContext context;
    EndpointServices services = new EndpointServices();
    private static final Logger logger = Logger.getLogger(ClassVisualizer.class.getName());
    
    @GET
    @Produces("application/ld+json")
    @ApiOperation(value = "Create 1 level from class", notes = "Create new")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "New class is created"),
                    @ApiResponse(code = 400, message = "Invalid ID supplied"),
                    @ApiResponse(code = 403, message = "Invalid IRI in parameter"),
                    @ApiResponse(code = 404, message = "Service not found") })
    public Response visClass(
            @ApiParam(value = "Class ID", required = true) @QueryParam("classID") String classID,
            @ApiParam(value = "Model ID", required = true) @QueryParam("modelID") String modelID
    ) {

   
        IRI classIRI, modelIRI;

        try {
                IRIFactory iri = IRIFactory.semanticWebImplementation();
                classIRI = iri.construct(classID);
                modelIRI = iri.construct(modelID);
          } catch (IRIException e) {
                logger.log(Level.WARNING, "ID is invalid IRI!");
                return Response.status(403).build();
        }

        /*  TODO: Namespace serice!? */
        DatasetAccessor accessor = DatasetAccessorFactory.createHTTP(services.getCoreReadAddress());
        Model model = accessor.getModel(modelID);

        String queryString;
        ParameterizedSparqlString pss = new ParameterizedSparqlString();
        pss.setNsPrefixes(model.getNsPrefixMap());
        
        queryString = "CONSTRUCT  { "
                + "?classIRI a sh:ShapeClass . "
                + "?classIRI rdfs:label ?classLabel . "
                + "?classIRI sh:property ?property . "
                + "?property sh:predicate ?predicate . "
                + "?property rdfs:label ?propertyLabel . "
                + "?property ?range ?classRef . "
                + "?classRef a ?type . "
                + "?classRef rdfs:label ?classRefLabel . "
                + "?classRef sh:property ?propertyRef . "
                + "?propertyRef sh:predicate ?predicateRef . "
                + "?propertyRef rdfs:label ?propertyRefLabel . "
                + "?propertyRef ?refRange ?propertyRefRange . "
                + "} WHERE { "
                + "GRAPH ?classIRI { "
                + "?classIRI a sh:ShapeClass . "
                + "?classIRI rdfs:label ?classLabel . "
                + "?classIRI sh:property ?property . "
                + "?property rdfs:label ?propertyLabel . "
                + "?property sh:predicate ?predicate . "
                + "VALUES ?range { sh:valueClass sh:datatype } "
                + "?property ?range ?classRef . "
                + "}"
                + "OPTIONAL { "
                + "GRAPH ?classRef { ?classRef a ?type . "
                + "?classRef rdfs:label ?classRefLabel . "
                + "?classRef sh:property ?propertyRef . "
                + "?propertyRef sh:predicate ?predicateRef . "
                + "?propertyRef rdfs:label ?propertyRefLabel . "
                + "VALUES ?refRange { sh:valueClass sh:datatype } "
                + "?propertyRef ?refRange ?propertyRefRange . "
                + "}}"
                + "}";

        pss.setCommandText(queryString);
        pss.setIri("classIRI", classIRI);

        return JerseyFusekiClient.constructGraphFromService(pss.toString(), services.getCoreSparqlAddress());

    }   
 
}