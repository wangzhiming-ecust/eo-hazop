package com.rao.kg.dao.impl;

import com.rao.kg.dao.NormalNodeSql;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NormalNodeSqlImpl implements NormalNodeSql {

    //返回list[0]为sql，list[1]为新节点类型
    @Override
    public String node2NewNode(String node_name, String node_type, String query_type, int query_number) {

        String sql = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX : <http://www.keylab.org/resource/hazop/>\n" +
                "PREFIX onto: <http://www.keylab.org/ontology/ontohazop#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "SELECT ?slabel ?p ?olabel\n" +
                "WHERE {\n" +
                "  ?s ?p ?o .\n" +
                "  ?s rdf:type onto:" + node_type + ". \n" +
                "  ?o rdf:type onto:" + query_type + ".\n" +
                "  ?s rdfs:label ?slabel. \n" +
                "  ?o rdfs:label ?olabel. \n" +
                "  Filter regex(?slabel," + "'"+ node_name +"'"+ ")\n" +
                "} LIMIT" + query_number;

        return sql;
    }

    @Override
    public String newNode2node(String node_name, String node_type, String query_type, int query_number) {

        String sql = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX : <http://www.keylab.org/resource/hazop/>\n" +
                "PREFIX onto: <http://www.keylab.org/ontology/ontohazop#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "SELECT ?slabel ?p ?olabel\n" +
                "WHERE {\n" +
                "  ?s ?p ?o .\n" +
                "  ?s rdf:type onto:" + query_type + ". \n" +
                "  ?o rdf:type onto:" + node_type + ".\n" +
                "  ?s rdfs:label ?slabel. \n" +
                "  ?o rdfs:label ?olabel. \n" +
                "  Filter regex(?olabel," + "'"+ node_name +"'"+ ")\n" +
                "}LIMIT" + query_number;
        return sql;
    }
}
