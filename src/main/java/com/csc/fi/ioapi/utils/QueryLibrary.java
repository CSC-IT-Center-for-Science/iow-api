/*
 * Licensed under the European Union Public Licence (EUPL) V.1.1 
 */
package com.csc.fi.ioapi.utils;

/**
 *
 * @author malonen
 */
public class QueryLibrary {
    
        final public static String provModelQuery = 
                "CONSTRUCT {"
                + "?every ?darn ?thing . }"
                + "WHERE {"
                + "GRAPH ?graph {"
                + " ?every ?darn ?thing . }"
                + "}";
    
    final public static String modelQuery = LDHelper.expandSparqlQuery(
                     "CONSTRUCT { "
                     + "?s ?p ?o . "
                     + "?graph dcterms:requires ?req . "
                     + "?req rdfs:label ?reqLabel . "
                     + "?req a ?reqType . "
                     + "?req dcap:preferredXMLNamespaceName ?namespaces . "
                     + "?req dcap:preferredXMLNamespacePrefix ?prefixes . "
                     + "?graph dcterms:isPartOf ?group . "
                     + "?group a foaf:Group . "
                     + "?group dcterms:references ?skosScheme . "
                     + "?skosScheme termed:id ?schemeId . "
                     + "?skosScheme dcterms:title ?schemeTitle . "
                     + "?skosScheme termed:graph ?termedGraph . "
                     + "?termedGraph termed:id ?termedGraphId . "
                     + "?termedGraph termed:code ?termedGraphCode . "
                     + "?group rdfs:label ?groupLabel . "
                     + "?group foaf:homepage ?homepage . "
                     + "} WHERE { "
                     + "GRAPH ?graph { "
                     + " ?s ?p ?o . "
                     + "} "
                     + "OPTIONAL { "
                     + "GRAPH ?graph {"
                     + " ?graph dcterms:requires ?req . "
                     + " ?req a ?reqType . "
                     + " VALUES ?reqType { dcap:DCAP dcap:MetadataVocabulary }}"
                     + " GRAPH ?req { "
                     + "  ?req rdfs:label ?reqLabel . "
                     + "  ?req dcap:preferredXMLNamespaceName ?namespaces . "
                     + "  ?req dcap:preferredXMLNamespacePrefix ?prefixes . "
                     + " }"
                     + "}" 
                     + "GRAPH <urn:csc:iow:sd> { "
                     + " ?metaGraph a sd:NamedGraph . "
                     + " ?metaGraph sd:name ?graph . "
                     + " ?metaGraph dcterms:isPartOf ?group . "
                     + "} "
                     + "GRAPH <urn:csc:groups> { "
                     + " ?group a foaf:Group . "
                     + "?group dcterms:references ?skosScheme . "
                     + "?skosScheme termed:id ?schemeId . "
                     + "?skosScheme dcterms:title ?schemeTitle . "
                     + "?skosScheme termed:graph ?termedGraph . "
                     + "?termedGraph termed:id ?termedGraphId . "
                     + "?termedGraph termed:code ?termedGraphCode . "
                     + " ?group foaf:homepage ?homepage . "
                     + " ?group rdfs:label ?groupLabel . "
                     + "}"
                     + "}");
    
    
        final public static String listClassesQuery = LDHelper.expandSparqlQuery(
                    "CONSTRUCT { "
                    + "?class rdfs:label ?label . "
                    + "?class rdfs:comment ?description . "
                    + "?class a ?type . "
                    + "?class dcterms:modified ?modified . "
                    + "?class rdfs:isDefinedBy ?source . "
                    + "?source rdfs:label ?sourceLabel . "
                    + "?source a ?sourceType . "
                    + "} WHERE { "
                    + "GRAPH ?hasPartGraph { "
                    + "?library dcterms:hasPart ?class . } "
                    + "GRAPH ?class { "
                    + "?class dcterms:modified ?modified . "
                    + "?class rdfs:label ?label . "
                    + "OPTIONAL { ?class rdfs:comment ?description . } "
                    + "?class a ?type . "
                    + "VALUES ?type { rdfs:Class sh:Shape } "
                    + "?class rdfs:isDefinedBy ?source .  } "
                    + "GRAPH ?source { "
                    + "?source a ?sourceType . "
                    + "?source rdfs:label ?sourceLabel . "
                    + "}}"      
        );
    
     final public static String classQuery = LDHelper.expandSparqlQuery(
                     "CONSTRUCT { "
                     + "?s ?p ?o . "
                     + "?graph rdfs:isDefinedBy ?library . "
                     + "?graph dcterms:subject ?concept . "
                     + "?concept skos:inScheme ?collection . "
                     + "?collection a skos:ConceptScheme . "
                     + "?collection dcterms:title ?collectionTitle . "
                     + "?library a ?type . "
                     + "?library rdfs:label ?label . "
                     + "} WHERE { "
                        + "{"
                        + "GRAPH ?graph {"
                        + "?graph a sh:Shape ."
                        + "?graph sh:scopeClass ?refGraph ."
                        + "?s ?p ?o . "
                        + "?graph rdfs:isDefinedBy ?library . "
                        + "OPTIONAL { ?graph dcterms:subject ?concept . "
                        + "?concept skos:inScheme ?collection . } "
                        + "} "
                        + "OPTIONAL {"
                                + "GRAPH ?refGraph {"
                                + "?refGraph rdfs:isDefinedBy ?refModel ."
                                + "} "
                                + "GRAPH ?refModel {"
                                + "?refModel dcterms:references ?collection . "
                                + "?collection a ?collectionType . "
                                + "OPTIONAL { ?collection dcterms:title ?collectionTitle . }"
                                + "} "
                        + "} "
                        + "GRAPH ?library { "
                        + " ?library a ?type . "
                        + " ?library rdfs:label ?label . }"
                     + "} UNION {"
                        + "GRAPH ?graph { "
                        + "?graph a rdfs:Class . "
                        + "FILTER NOT EXISTS { ?graph sh:scopeClass ?any . }"
                        + "?s ?p ?o . "
                        + "?graph rdfs:isDefinedBy ?library . "
                        + "{ "
                        + "?graph dcterms:subject ?concept . "
                        + "?concept skos:inScheme ?collection . "
                        + "} UNION { "
                        + "?graph dcterms:subject ?concept . "
                        + "?concept skos:inScheme ?collection . "
                        + "?collection dcterms:title ?title . "
                        + "} UNION { "
                        + "?graph dcterms:subject ?concept . "
                        + "?concept skos:inScheme ?scheme . "
                        + "?scheme dcterms:title ?title . "
                        + "?scheme termed:id ?schemeId . "
                        + "?scheme termed:graph ?termedGraph . "
                        + "?concept termed:graph ?termedGraph . "
                        + "?termedGraph termed:id ?termedGraphId . "
                        + "}"
                        + "}"
                        + "GRAPH ?library { "
                        + " ?library a ?type . "
                        + " ?library rdfs:label ?label . "
                        + "}"
                     + "}"
                     + "}");
     
     
     final public static String listPredicatesQuery = LDHelper.expandSparqlQuery(
             "CONSTRUCT { "
                + "?property rdfs:label ?label . "
                + "?property rdfs:comment ?description . "
                + "?property a ?type . "
                + "?property rdfs:isDefinedBy ?source . "
                + "?source rdfs:label ?sourceLabel . "
                + "?source a ?sourceType . "
                + "?property dcterms:modified ?date . } "
                + "WHERE { "
                + "GRAPH ?hasPartGraph { "
                + "?library dcterms:hasPart ?property . } "
                + "GRAPH ?property { ?property rdfs:label ?label . "
                    + "OPTIONAL { ?property rdfs:comment ?description . } "
                    + "VALUES ?type { owl:ObjectProperty owl:DatatypeProperty } "
                    + "?property a ?type . "
                    + "?property rdfs:isDefinedBy ?source . "
                    + "OPTIONAL {?property dcterms:modified ?date . } "
                + "} "
                + "GRAPH ?source { ?source a ?sourceType . ?source rdfs:label ?sourceLabel . }}"
     );
     
      final public static String predicateQuery = LDHelper.expandSparqlQuery(
                 "CONSTRUCT { "
                 + "?s ?p ?o . "
                 + "?graph dcterms:subject ?concept . "
                 + "?concept skos:inScheme ?collection . "
                 + "?collection a ?collectionType . "
                 + "?collection dcterms:title ?collectionTitle . "
                 + "?graph rdfs:isDefinedBy ?library . "
                 + "?library a ?type . "
                 + "?library rdfs:label ?label . "
                 + "} WHERE { "
                 + "GRAPH ?graph { "
                 + "?s ?p ?o . "
                 + "{ "
                 + "?graph dcterms:subject ?concept . "
                 + "?concept skos:inScheme ?collection . "
                 + "} UNION { "
                 + "?graph dcterms:subject ?concept . "
                 + "?concept skos:inScheme ?collection . "
                 + "?collection dcterms:title ?title . "
                 + "} UNION { "
                 + "?graph dcterms:subject ?concept . "
                 + "?concept skos:inScheme ?scheme . "
                 + "?scheme dcterms:title ?title . "
                 + "?scheme termed:id ?schemeId . "
                 + "?scheme termed:graph ?termedGraph . "
                 + "?concept termed:graph ?termedGraph . "
                 + "?termedGraph termed:id ?termedGraphId . "
                 + "}"
                 + "?graph rdfs:isDefinedBy ?library . } "
                 + "GRAPH ?library { "
                 + " ?library a ?type . "
                 + " ?library rdfs:label ?label . "
                 + "}"
                 + "}");
      
      
        final public static String hasPartListQuery = LDHelper.expandSparqlQuery(
                 "CONSTRUCT { "
                 + "?model a ?type . "
                 + "?model dcterms:isPartOf ?group . "
                 + "?model dcterms:hasPart ?resource . "
                 + "} WHERE { "
                 + "GRAPH ?hasPartGraph { "
                 + "?model dcterms:hasPart ?resource . "
                 + "}"
                 + "GRAPH ?model { "
                 + "?model dcterms:isPartOf ?group . "
                 + "?model a ?type . "
                 + "}"
                 + "}");  
        
        final public static String externalClassQuery = LDHelper.expandSparqlQuery(
        "CONSTRUCT { "
                    + "?externalModel rdfs:label ?externalModelLabel . "
                    + "?classIRI rdfs:isDefinedBy ?externalModel . "
                    + "?classIRI a rdfs:Class . "
                    + "?classIRI rdfs:label ?label . "
                    + "?classIRI rdfs:comment ?comment . "
                    + "?classIRI sh:property ?property . "
                    + "?property sh:datatype ?datatype . "
                    + "?property dcterms:type ?propertyType . "
                    + "?property sh:valueShape ?valueClass . "
                    + "?property sh:predicate ?predicate . "
                    + "?property rdfs:label ?propertyLabel . "
                    + "?property rdfs:comment ?propertyComment . "
                     + "} WHERE { "
                     + "SERVICE ?modelService { "
                     + "GRAPH ?library { "
                     + "?library dcterms:requires ?externalModel . "
                    + "?externalModel rdfs:label ?externalModelLabel . "
                     + "}}"
                    + "GRAPH ?externalModel {"
                    + "?classIRI a ?type . "
                    + "FILTER(STRSTARTS(STR(?classIRI), STR(?externalModel)))"
                    + "VALUES ?type { rdfs:Class owl:Class sh:Shape } "
                    /* Get class label */
                     + "{?classIRI rdfs:label ?labelStr . FILTER(LANG(?labelStr) = '') BIND(STRLANG(?labelStr,'en') as ?label) }"
                     + "UNION"
                     + "{ ?classIRI rdfs:label ?label . FILTER(LANG(?label)!='') }"
                     /* Get class comment */
                    + "{ ?classIRI ?commentPred ?commentStr . "
                     + "VALUES ?commentPred { rdfs:comment skos:definition dcterms:description dc:description prov:definition }"
                     + "FILTER(LANG(?commentStr) = '') BIND(STRLANG(STR(?commentStr),'en') as ?comment) }"
                     + "UNION"
                     + "{ ?classIRI ?commentPred ?comment . "
                     + "VALUES ?commentPred { rdfs:comment skos:definition dcterms:description dc:description prov:definition }"
                     + " FILTER(LANG(?comment)!='') }"
                    
                    + "OPTIONAL { "
                    + "?classIRI rdfs:subClassOf* ?superclass . "
                    + "?predicate rdfs:domain ?superclass . "
                    + "BIND(UUID() AS ?property)"
                    + "{"
                    + "?predicate a owl:DatatypeProperty . "
                    + "FILTER NOT EXISTS { ?predicate a owl:ObjectProperty }"
                    + "BIND(owl:DatatypeProperty as ?propertyType) "
                    + "} UNION {"
                    + "?predicate a owl:ObjectProperty . "
                    + "FILTER NOT EXISTS { ?predicate a owl:DatatypeProperty }"
                    + "BIND(owl:ObjectProperty as ?propertyType) "
                    + "} UNION {"
                    /* Treat owl:AnnotationProperty as DatatypeProperty */
                    + "?predicate a owl:AnnotationProperty. "
                    + "?predicate rdfs:label ?atLeastSomeLabel . "
                    + "FILTER NOT EXISTS { ?predicate a owl:DatatypeProperty }"
                    + "BIND(owl:DatatypeProperty as ?propertyType) "
                    + "} UNION {"
                    /* TODO: Add all XSD types? */
                    + "VALUES ?literalValue { rdfs:Literal xsd:String }"
                    /* IF Predicate Type is rdf:Property and range is rdfs:Literal = DatatypeProperty */
                    + "?predicate a rdf:Property . "
                    + "?predicate rdfs:range ?literalValue ."
                    + "BIND(owl:DatatypeProperty as ?propertyType) "
                    + "FILTER NOT EXISTS { ?predicate a ?multiType . VALUES ?multiType { owl:DatatypeProperty owl:ObjectProperty } }"
                     + "} UNION {"
                    /* IF Predicate Type is rdf:Property and range is rdfs:Resource then property is object property */
                    + "?predicate a rdf:Property . "
                    + "?predicate rdfs:range rdfs:Resource ."
                    + "BIND(owl:ObjectProperty as ?propertyType) "
                    + "FILTER NOT EXISTS { ?predicate a ?multiType . VALUES ?multiType { owl:DatatypeProperty owl:ObjectProperty } }"
                    + "} UNION {"
                    /* IF Predicate Type is rdf:Property and range is resource that is class or thing */
                    + "?predicate a rdf:Property . "
                    + "FILTER NOT EXISTS { ?predicate a ?multiType . VALUES ?multiType { owl:DatatypeProperty owl:ObjectProperty } }"
                    + "?predicate rdfs:range ?rangeClass . "
                    + "FILTER(?rangeClass!=rdfs:Literal)"
                    + "?rangeClass a ?rangeClassType . "
                    + "VALUES ?rangeClassType { skos:Concept owl:Thing }"
                    + "BIND(owl:ObjectProperty as ?propertyType) "
                    + "}"
                    
                    + "OPTIONAL { ?predicate a owl:DatatypeProperty . ?predicate rdfs:range ?datatype . FILTER (!isBlank(?datatype))  } "
                    + "OPTIONAL { ?predicate a owl:ObjectProperty . ?predicate rdfs:range ?valueClass . } "

                    /* Predicate label - if lang unknown create english tag */
                    + "OPTIONAL {?predicate rdfs:label ?propertyLabelStr . FILTER(LANG(?propertyLabelStr) = '') BIND(STRLANG(?propertyLabelStr,'en') as ?propertyLabel) }"
                    + "OPTIONAL { ?predicate rdfs:label ?propertyLabel . FILTER(LANG(?propertyLabel)!='') }"
                   
                    /* Predicate comments - if lang unknown create english tag */
                    + "OPTIONAL { "
                    + "VALUES ?predicateCommentPred { rdfs:comment skos:definition dcterms:description dc:description }"
                    + "?predicate ?predicateCommentPred ?propertyCommentStr . FILTER(LANG(?propertyCommentStr) = '') "
                    + "BIND(STRLANG(STR(?propertyCommentStr),'en') as ?propertyComment) }"
                    + "OPTIONAL { "
                    + "VALUES ?predicateCommentPred { rdfs:comment skos:definition dcterms:description dc:description }"
                    + "?predicate ?predicateCommentPred ?propertyCommentToStr . FILTER(LANG(?propertyCommentToStr)!='') "
                    + "BIND(?propertyCommentToStr as ?propertyComment) }"

                    + "}"
                    + "} }");
        
        final public static String externalClassQueryWithSchemaOrg = LDHelper.expandSparqlQuery(
                    "CONSTRUCT { "
                    + "?externalModel rdfs:label ?externalModelLabel . "
                    + "?classIRI rdfs:isDefinedBy ?externalModel . "
                    + "?classIRI a rdfs:Class . "
                    + "?classIRI rdfs:label ?label . "
                    + "?classIRI rdfs:comment ?comment . "
                    + "?classIRI sh:property ?property . "
                    + "?property sh:datatype ?datatype . "
                    + "?property dcterms:type ?propertyType . "
                    + "?property sh:valueShape ?valueClass . "
                    + "?property sh:predicate ?predicate . "
                    + "?property rdfs:label ?propertyLabel . "
                    + "?property rdfs:comment ?propertyComment . "
                     + "} WHERE { "
                     + "SERVICE ?modelService { "
                     + "GRAPH ?library { "
                     + "?library dcterms:requires ?externalModel . "
                    + "?externalModel rdfs:label ?externalModelLabel . "
                     + "}}"
                    + "GRAPH ?externalModel {"
                    + "?classIRI a ?type . "
                    + "FILTER(STRSTARTS(STR(?classIRI), STR(?externalModel)))"
                    + "VALUES ?type { rdfs:Class owl:Class sh:Shape } "
                    /* Get class label */
                     + "{?classIRI rdfs:label ?labelStr . FILTER(LANG(?labelStr) = '') BIND(STRLANG(?labelStr,'en') as ?label) }"
                     + "UNION"
                     + "{ ?classIRI rdfs:label ?label . FILTER(LANG(?label)!='') }"
                     /* Get class comment */
                    + "{ ?classIRI ?commentPred ?commentStr . "
                     + "VALUES ?commentPred { rdfs:comment skos:definition dcterms:description dc:description prov:definition }"
                     + "FILTER(LANG(?commentStr) = '') BIND(STRLANG(STR(?commentStr),'en') as ?comment) }"
                     + "UNION"
                     + "{ ?classIRI ?commentPred ?comment . "
                     + "VALUES ?commentPred { rdfs:comment skos:definition dcterms:description dc:description prov:definition }"
                     + " FILTER(LANG(?comment)!='') }"
                    
                    + "OPTIONAL { "
                    + "{?classIRI rdfs:subClassOf* ?superclass . "
                    + "?predicate rdfs:domain ?superclass . } UNION {"
                    + "?classIRI a ?schemaClass . "
                    + "?predicate schema:domainIncludes ?classIRI ."
                    + "}"
                    + "BIND(UUID() AS ?property)"
                    + "VALUES ?range { rdfs:range schema:rangeIncludes }"
                    + "{"
                    + "?predicate a owl:DatatypeProperty . "
                    + "FILTER NOT EXISTS { ?predicate a owl:ObjectProperty }"
                    + "BIND(owl:DatatypeProperty as ?propertyType) "
                    + "} UNION {"
                    + "?predicate a owl:ObjectProperty . "
                    + "FILTER NOT EXISTS { ?predicate a owl:DatatypeProperty }"
                    + "BIND(owl:ObjectProperty as ?propertyType) "
                    + "} UNION {"
                    /* Treat owl:AnnotationProperty as DatatypeProperty */
                    + "?predicate a owl:AnnotationProperty. "
                    + "?predicate rdfs:label ?atLeastSomeLabel . "
                    + "FILTER NOT EXISTS { ?predicate a owl:DatatypeProperty }"
                    + "BIND(owl:DatatypeProperty as ?propertyType) "
                    + "} UNION {"
                    + "VALUES ?literalValue { rdfs:Literal schema:Text schema:Integer schema:DateTime schema:Boolean schema:Date }"
                    /* IF Predicate Type is rdf:Property and range is rdfs:Literal = DatatypeProperty */
                    + "?predicate a rdf:Property . "
                    + "?predicate ?range ?literalValue ."
                    + "BIND(owl:DatatypeProperty as ?propertyType) "
                    + "FILTER NOT EXISTS { ?predicate a ?multiType . VALUES ?multiType { owl:DatatypeProperty owl:ObjectProperty } }"
                     + "} UNION {"
                    /* IF Predicate Type is rdf:Property and range is rdfs:Resource then property is object property */
                    + "?predicate a rdf:Property . "
                    + "?predicate ?range rdfs:Resource ."
                    + "BIND(owl:ObjectProperty as ?propertyType) "
                    + "FILTER NOT EXISTS { ?predicate a ?multiType . VALUES ?multiType { owl:DatatypeProperty owl:ObjectProperty } }"
                    + "} UNION {"
                    /* IF Predicate Type is rdf:Property and range is resource that is class or thing */
                    + "?predicate a rdf:Property . "
                    + "FILTER NOT EXISTS { ?predicate a ?multiType . VALUES ?multiType { owl:DatatypeProperty owl:ObjectProperty } }"
                    + "?predicate ?range ?rangeClass . "
                    + "FILTER(?rangeClass!=rdfs:Literal)"
                    + "?rangeClass a ?rangeClassType . "
                    + "FILTER(?rangeClassType!=schema:DataType)"
                    + "VALUES ?rangeClassType { skos:Concept owl:Thing rdfs:Class }"
                    + "BIND(owl:ObjectProperty as ?propertyType) "
                    + "}"
                    
                    + "OPTIONAL { ?predicate a owl:DatatypeProperty . ?predicate rdfs:range ?datatype . FILTER (!isBlank(?datatype))  } "
                    + "OPTIONAL { ?predicate a owl:ObjectProperty . ?predicate ?range ?valueClass . } "

                    /* Predicate label - if lang unknown create english tag */
                    + "OPTIONAL {?predicate rdfs:label ?propertyLabelStr . FILTER(LANG(?propertyLabelStr) = '') BIND(STRLANG(?propertyLabelStr,'en') as ?propertyLabel) }"
                    + "OPTIONAL { ?predicate rdfs:label ?propertyLabel . FILTER(LANG(?propertyLabel)!='') }"
                   
                    /* Predicate comments - if lang unknown create english tag */
                    + "OPTIONAL { "
                    + "VALUES ?predicateCommentPred { rdfs:comment skos:definition dcterms:description dc:description }"
                    + "?predicate ?predicateCommentPred ?propertyCommentStr . FILTER(LANG(?propertyCommentStr) = '') "
                    + "BIND(STRLANG(STR(?propertyCommentStr),'en') as ?propertyComment) }"
                    + "OPTIONAL { "
                    + "VALUES ?predicateCommentPred { rdfs:comment skos:definition dcterms:description dc:description }"
                    + "?predicate ?predicateCommentPred ?propertyCommentToStr . FILTER(LANG(?propertyCommentToStr)!='') "
                    + "BIND(?propertyCommentToStr as ?propertyComment) }"

                    + "}"
                    + "} }");
        
                final public static String externalShapeQuery = LDHelper.expandSparqlQuery(
                    "CONSTRUCT  { "
                    + "?shapeIRI owl:versionInfo ?draft . "
                    + "?shapeIRI dcterms:modified ?modified . "
                    + "?shapeIRI dcterms:created ?creation . "
                    + "?shapeIRI sh:scopeClass ?classIRI . "
                    + "?shapeIRI a sh:Shape . "
                    + "?shapeIRI rdfs:isDefinedBy ?model . "
                    + "?model rdfs:label ?externalModelLabel . "
                    + "?shapeIRI rdfs:label ?label . "
                    + "?shapeIRI rdfs:comment ?comment . "
                    + "?shapeIRI sh:property ?property . "
                    + "?property dcterms:type ?propertyType . "    
                    + "?property sh:predicate ?predicate . "
                    + "?property rdfs:comment ?propertyComment .  "
                    + "?property rdfs:label ?propertyLabel .  "
                    /* TODO: Fix pointing to AP classes? */
                    + "?property sh:valueShape ?valueClass . "
                    + "?property sh:class ?valueClass . "
                    + "?property sh:datatype ?datatype . "
                    + "} WHERE { "
                    + "BIND(now() as ?creation) "
                    + "BIND(now() as ?modified) "
                    + "SERVICE ?modelService { "
                    + "GRAPH ?model { "
                    + "?model dcterms:requires ?externalModel . "
                    + "?externalModel rdfs:label ?externalModelLabel . "
                    + "}}"
                    + "GRAPH ?externalModel {"
                    + "?classIRI a ?type . "
                    + "FILTER(STRSTARTS(STR(?classIRI), STR(?externalModel)))"
                    + "VALUES ?type { rdfs:Class owl:Class sh:Shape } "
                    /* Labels */
                     + "{?classIRI rdfs:label ?labelStr . FILTER(LANG(?labelStr) = '') BIND(STRLANG(?labelStr,'en') as ?label) }"
                     + "UNION"
                     + "{ ?classIRI rdfs:label ?label . FILTER(LANG(?label)!='') }"
                    /* Comments */
                     + "{ ?classIRI ?commentPred ?commentStr . "
                     + "VALUES ?commentPred { rdfs:comment skos:definition dcterms:description dc:description prov:definition }"
                     + "FILTER(LANG(?commentStr) = '') BIND(STRLANG(STR(?commentStr),'en') as ?comment) }"
                     + "UNION"
                     + "{ ?classIRI ?commentPred ?comment . "
                     + "VALUES ?commentPred { rdfs:comment skos:definition dcterms:description dc:description prov:definition }"
                     + " FILTER(LANG(?comment)!='') }"
                            
                    + "OPTIONAL { "
                    + "?classIRI rdfs:subClassOf* ?superclass . "
                    + "?predicate rdfs:domain ?superclass .  "
                    + "BIND(UUID() AS ?property)"   
                            
                    /* Types of properties */        
                    + "{"
                    + "?predicate a owl:DatatypeProperty . "
                    + "FILTER NOT EXISTS { ?predicate a owl:ObjectProperty }"
                    + "BIND(owl:DatatypeProperty as ?propertyType) "
                    + "} UNION {"
                    + "?predicate a owl:ObjectProperty . "
                    + "FILTER NOT EXISTS { ?predicate a owl:DatatypeProperty }"
                    + "BIND(owl:ObjectProperty as ?propertyType) "
                    + "} UNION {"
                    + "?predicate a owl:AnnotationProperty. "
                    + "?predicate rdfs:label ?atLeastSomeLabel . "
                    + "FILTER NOT EXISTS { ?predicate a owl:DatatypeProperty }"
                    + "BIND(owl:DatatypeProperty as ?propertyType) "
                    + "} UNION {"
                    + "?predicate a rdf:Property . "
                    + "?predicate rdfs:range rdfs:Literal ."
                    + "BIND(owl:DatatypeProperty as ?propertyType) "
                    + "FILTER NOT EXISTS { ?predicate a ?multiType . VALUES ?multiType { owl:DatatypeProperty owl:ObjectProperty } }"
                     + "} UNION {"
                     + "?predicate a rdf:Property . "
                    + "?predicate rdfs:range rdfs:Resource ."
                    + "BIND(owl:ObjectProperty as ?propertyType) "
                    + "FILTER NOT EXISTS { ?predicate a ?multiType . VALUES ?multiType { owl:DatatypeProperty owl:ObjectProperty } }"
                    + "}UNION {"
                     + "?predicate a rdf:Property . "
                    + "FILTER NOT EXISTS { ?predicate a ?multiType . VALUES ?multiType { owl:DatatypeProperty owl:ObjectProperty } }"
                    + "?predicate rdfs:range ?rangeClass . "
                    + "FILTER(?rangeClass!=rdfs:Literal)"
                    + "?rangeClass a ?rangeClassType . "
                    + "VALUES ?rangeClassType { skos:Concept owl:Thing rdfs:Class }"
                    + "BIND(owl:ObjectProperty as ?propertyType) "
                    + "}"
                    
                            
                     + "OPTIONAL { ?predicate a owl:DatatypeProperty . ?predicate rdfs:range ?datatype . FILTER (!isBlank(?datatype))  } "
                    + "OPTIONAL { ?predicate a owl:ObjectProperty . ?predicate rdfs:range ?valueClass . } "

                    /* Predicate label - if lang unknown create english tag */
                    + "OPTIONAL {?predicate rdfs:label ?propertyLabelStr . FILTER(LANG(?propertyLabelStr) = '') BIND(STRLANG(?propertyLabelStr,'en') as ?propertyLabel) }"
                    + "OPTIONAL { ?predicate rdfs:label ?propertyLabel . FILTER(LANG(?propertyLabel)!='') }"
                            
                            
                   
                            
                    /* Predicate comments - if lang unknown create english tag */
                    + "OPTIONAL { "
                    + "VALUES ?predicateCommentPred { rdfs:comment skos:definition dcterms:description dc:description }"
                    + "?predicate ?predicateCommentPred ?propertyCommentStr . FILTER(LANG(?propertyCommentStr) = '') "
                    + "BIND(STRLANG(STR(?propertyCommentStr),'en') as ?propertyComment) }"
                    + "OPTIONAL { "
                    + "VALUES ?predicateCommentPred { rdfs:comment skos:definition dcterms:description dc:description }"
                    + "?predicate ?predicateCommentPred ?propertyCommentToStr . FILTER(LANG(?propertyCommentToStr)!='') "
                    + "BIND(?propertyCommentToStr as ?propertyComment) }"        
                            
                            
                            
                

                    + "}"    
                    + "}"
                    + "}");
     
}
