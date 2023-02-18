package com.rao.kg.dao.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.jena.query.*;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;

@Component
public class QuerySparqlFromEndpoint {


    public JSONObject endpointSparql(String sql, String endpoint) {

        String xmlStr = null;
        Query query = QueryFactory.create(sql);
        QueryExecution qe = QueryExecutionFactory.sparqlService(endpoint, query);

        try {
            ResultSet rs = qe.execSelect();

//            while (rs.hasNext()) {
                  ByteArrayOutputStream baos = new ByteArrayOutputStream();
                  ResultSetFormatter.outputAsJSON(baos, rs);
                  xmlStr = baos.toString();
                  baos.close();
//            }
        } catch (Exception e) {
            try {
                ResultSet rs = qe.execSelect();
//                while (rs.hasNext()) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ResultSetFormatter.outputAsJSON(baos, rs);
                    xmlStr = baos.toString();
                    baos.close();
//                }
            } catch (Exception f) {
                try {
                    ResultSet rs = qe.execSelect();
//                    while (rs.hasNext()) {
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        ResultSetFormatter.outputAsJSON(baos, rs);
                        xmlStr = baos.toString();
                        baos.close();
//                    }
                } catch (Exception g) {
                    System.out.println(g.getMessage());
                    System.out.println(sql);
                }
            }
        } finally {
            try {
                if (qe != null) {
                    qe.close();
                }
            } catch (Exception e) {
                System.out.println("endpoint的查询qe，关闭失败");
            }
        }
        return JSONObject.parseObject(xmlStr);

    }


}
