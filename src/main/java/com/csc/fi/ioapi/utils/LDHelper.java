/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csc.fi.ioapi.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

import com.github.jsonldjava.utils.JsonUtils;
import com.hp.hpl.jena.query.ParameterizedSparqlString;
import com.sun.jersey.api.uri.UriComponent;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import org.apache.commons.lang3.text.WordUtils;

/**
 *
 * @author malonen
 */
public class LDHelper {
   
  
   
   public static final Map<String, String> PREFIX_MAP = 
    Collections.unmodifiableMap(new HashMap<String, String>() {{ 
        put("owl", "http://www.w3.org/2002/07/owl#");
        put("xsd", "http://www.w3.org/2001/XMLSchema#");
        put("rdf","http://www.w3.org/1999/02/22-rdf-syntax-ns#");
        put("rdfs","http://www.w3.org/2000/01/rdf-schema#");
        put("foaf","http://xmlns.com/foaf/0.1/");
        put("dcterms","http://purl.org/dc/terms/");
        put("adms","http://www.w3.org/ns/adms#");
        put("dc","http://purl.org/dc/elements/1.1/");
        put("vann","http://purl.org/vocab/vann/");
        put("void","http://rdfs.org/ns/void#");
        put("sd","http://www.w3.org/ns/sparql-service-description#");
        put("text","http://jena.apache.org/text#");
        put("sh","http://www.w3.org/ns/shacl#");
        put("iow","http://urn.fi/urn:nbn:fi:csc-iow-meta#");
        put("skos","http://www.w3.org/2004/02/skos/core#");
        put("prov","http://www.w3.org/ns/prov#");
    }});
    
    public final static String prefix =   "PREFIX owl: <http://www.w3.org/2002/07/owl#> "+
                            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> "+
                            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
                            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "+
                            "PREFIX foaf: <http://xmlns.com/foaf/0.1/> "+
                            "PREFIX dcterms: <http://purl.org/dc/terms/> "+
                            "PREFIX adms: <http://www.w3.org/ns/adms#> "+
                            "PREFIX dc: <http://purl.org/dc/elements/1.1/> "+
                            "PREFIX vann: <http://purl.org/vocab/vann/> "+
                            "PREFIX void: <http://rdfs.org/ns/void#> "+
                            "PREFIX sd: <http://www.w3.org/ns/sparql-service-description#> "+
                            "PREFIX text: <http://jena.apache.org/text#> "+
                            "PREFIX sh: <http://www.w3.org/ns/shacl#> "+
                            "PREFIX skos: <http://www.w3.org/2004/02/skos/core#> "+
                            "PREFIX prov: <http://www.w3.org/ns/prov#> " +
                            "PREFIX iow: <http://urn.fi/urn:nbn:fi:csc-iow-meta#>";
    
    
    ParameterizedSparqlString pss = new ParameterizedSparqlString();
   
    
    static String query(String queryString) {
        queryString = prefix+queryString;
        return  UriComponent.encode(queryString,UriComponent.Type.QUERY_PARAM); // URLEncoder.encode(queryString, "UTF-8");
    }
    
    public static String modelName(String name) {
        name = name.toLowerCase();
        return removeInvalidCharacters(name);
    }
    
    public static String propertyName(String name) {
        name = StringUtils.uncapitalize(name);
        return removeInvalidCharacters(name);
    }
    
    public static String resourceName(String name) {
         name = WordUtils.capitalize(name);
         return removeInvalidCharacters(name);
    }
    
    public static String removeInvalidCharacters(String name) {
        name = removeAccents(name);
        name = name.replaceAll("[^a-zA-Z0-9_-]", "");
        return name;
    }
    
    public static String removeAccents(String text) {
    return text == null ? null :
        Normalizer.normalize(text, Form.NFD)
            .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
    
    public static InputStream getDefaultGraphInputStream() {
           return LDHelper.class.getClassLoader().getResourceAsStream("defaultGraph.json");
    }
      
    public static InputStream getDefaultGroupsInputStream() {
           return LDHelper.class.getClassLoader().getResourceAsStream("defaultGroups.json");
    }
    
    public static Object getUserContext() {
       try {
           //  return Thread.currentThread().getContextClassLoader().getResourceAsStream("userContext.json");
           return JsonUtils.fromInputStream(LDHelper.class.getClassLoader().getResourceAsStream("userContext.json"));
       } catch (IOException ex) {
           Logger.getLogger(LDHelper.class.getName()).log(Level.SEVERE, null, ex);
           return null;
       }
    }
    
        public static Object getDescriptionContext() {
       try {
           //  return Thread.currentThread().getContextClassLoader().getResourceAsStream("userContext.json");
           return JsonUtils.fromInputStream(LDHelper.class.getClassLoader().getResourceAsStream("descriptionContext.json"));
       } catch (IOException ex) {
           Logger.getLogger(LDHelper.class.getName()).log(Level.SEVERE, null, ex);
           return null;
       }
    }
    
        public static Object getGroupContext() {
       try {
           //  return Thread.currentThread().getContextClassLoader().getResourceAsStream("userContext.json");
           return JsonUtils.fromInputStream(LDHelper.class.getClassLoader().getResourceAsStream("groupContext.json"));
       } catch (IOException ex) {
           Logger.getLogger(LDHelper.class.getName()).log(Level.SEVERE, null, ex);
           return null;
       }
    }
    
    
}
