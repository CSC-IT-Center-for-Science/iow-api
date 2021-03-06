/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csc.fi.ioapi.config;


/**
 *
 * @author malonen
 */
public final class EndpointServices {
    
private String endpoint;
private String scheme;

public EndpointServices() {
    this.endpoint = ApplicationProperties.getEndpoint();
    this.scheme = ApplicationProperties.getSchemeId();
}

public String getCoreReadAddress() {
    return endpoint+"/core/get";
}

public String getCoreReadWriteAddress() {
    return endpoint+"/core/data";
}

public String getCoreSparqlAddress() {
    return endpoint+"/core/sparql";
}

public String getSchemesReadAddress() {
    return endpoint+"/scheme/get";
}

public String getSchemesReadWriteAddress() {
    return endpoint+"/scheme/data";
}

public String getSchemesSparqlAddress() {
    return endpoint+"/scheme/sparql";
}

public String getLocalhostCoreSparqlAddress() {
    if(!ApplicationProperties.getEndpoint().startsWith("http://localhost"))
        return "http://localhost/core/sparql";
    else if(ApplicationProperties.getDebugMode()) 
        return endpoint+"/core/sparql"; 
    else 
        return "http://localhost/core/sparql";
}

public String getCoreSparqlUpdateAddress() {
    return endpoint+"/core/update";
}

public String getUsersReadAddress() {
    return endpoint+"/users/get";
}

public String getUsersReadWriteAddress() {
    return endpoint+"/users/data";
}

public String getUsersSparqlAddress() {
    return endpoint+"/users/sparql";
}

public String getUsersSparqlUpdateAddress() {
    return endpoint+"/users/update";
}

public String getConceptSchemeUri() {
     return scheme;
}

public String getConceptAPI() {
     return "http://dev.finto.fi/rest/v1/data";
}

public String getTempConceptReadWriteAddress() {
    return endpoint+"/concept/data";
}

public String getTempConceptReadSparqlAddress() {
    return endpoint+"/concept/sparql";
}


public String getTempConceptSparqlUpdateAddress() {
    return endpoint+"/concept/update";
}

public String getProvReadWriteAddress() {
    return endpoint+"/prov/data";
}

public String getProvReadSparqlAddress() {
    return endpoint+"/prov/sparql";
}

public String getProvSparqlUpdateAddress() {
    return endpoint+"/prov/update";
}

public String getConceptSearchAPI() {
     return scheme+"rest/v1/search";
}

public String getConceptSearchAPI(String id) {
     return scheme+"rest/v1/"+id+"/search";
}

public String getSchemeSearchAPI() {
     return scheme+"rest/v1/vocabularies";
}

public String getVocabExportAPI(String vocab) {
    return scheme+"rest/v1/"+vocab+"/data";
}

public String getImportsReadWriteAddress() {
      return endpoint+"/imports/data";
}

public String getImportsReadAddress() {
      return endpoint+"/imports/get";
}

public String getImportsSparqlAddress() {
      return endpoint+"/imports/sparql";
}

public String getLocalhostConceptSparqlAddress() {
    if(!ApplicationProperties.getEndpoint().startsWith("http://localhost"))
        return "http://localhost/concept/sparql";
    else if(ApplicationProperties.getDebugMode()) 
        return endpoint+"/concept/sparql"; 
    else 
        return "http://localhost/concept/sparql";
    }
}
