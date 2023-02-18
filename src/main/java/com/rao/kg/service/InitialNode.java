package com.rao.kg.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rao.kg.dao.impl.QuerySparqlFromEndpoint;
import com.rao.kg.dao.impl.QuerySparqlFromNtFile;
import com.rao.kg.pojo.JsonMenuOrPre;
import com.rao.kg.pojo.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InitialNode {

    @Autowired
    JsonMenuOrPre jsonMenuOrPre;
    @Autowired
    EncodeAndDecode encodeAndDecode;
    @Autowired
    QuerySparqlFromNtFile querySparqlFromNtFile;
    @Autowired
    QuerySparqlFromEndpoint querySparqlFromEndpoint;
    @Value("http://172.20.138.202:3030/eo-hazop/query")
    String endpoint;
    final private String hazopInstance = "hazopInstance";
    final private String enterprise = "Enterprise";
    final private String accident = "Accident";
    final private String waybill = "Waybill";
    final private String vehicle = "Vehicle";
    final private String majorDangerSource = "MajorDangerSource";
    final private String chemicals = "Chemicals";
//  还少重大危险源

    public Map<String, Object> getIntialNode(String node_type, int limit) {

        String sql = null;
        Set<Node> nodes = new HashSet();
        Map<String, List<Map<String, String>>> menus = new HashMap<>();
        Map<String, Object> res = new HashMap<>();

     //   if (node_type.equals(hazopInstance)) {
            sql = "prefix hazop: <http://www.keylab.org/resource/hazop#>\n" +
                    "prefix onto: <http://www.keylab.org/ontology/ontohazop#>\n" +
                    "prefix owl:  <http://www.w3.org/2002/07/owl#>\n" +
                    "prefix rdf:  <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "prefix xsd:  <http://www.w3.org/2001/XMLSchema#>\n" +
                    "SELECT  ?o  \n" +
                    "WHERE {\n" +
                    "  ?s rdf:type onto:"+node_type+".\n" +
                    "  ?s rdfs:label ?o.\n" +
                    "}\n" +
                    "LIMIT " + limit;
     //   }


        if (sql != null) {
            JSONObject json = querySparqlFromEndpoint.endpointSparql(sql,endpoint);
            System.out.println(json);
            if (json != null) {

                menus.put(node_type, jsonMenuOrPre.getJson(node_type));
                //        System.out.println(json.getJSONObject("results").getJSONArray("bindings"));
                JSONArray jsonArray = json.getJSONObject("results").getJSONArray("bindings");
                if (!jsonArray.isEmpty()){
                    for (Object obj :
                            jsonArray) {

                        Map<String, Map<String, String>> map = (Map<String, Map<String, String>>) obj;
                        String sub_value = map.get("o").get("value");
//                        System.out.println(sub_value);
//                        String[] split_sub = sub_value.split("/hazop/");
//                        if (split_sub.length > 1) {
//                            System.out.println("成功");
//                            String sub = encodeAndDecode.decode(split_sub[1]);
//                            nodes.add(new Node(sub, node_type, sub));
//                        } else {
//                            nodes.add(new Node("数据空", node_type, "数据空"));
//                        }
                        String sub = encodeAndDecode.decode(sub_value);
                        nodes.add(new Node(sub, node_type, sub));
                        //            nodeId++;
                    }
                    res.put("nodes", nodes);
                    res.put("menu", menus);
                    res.put("code",0);
                }else {
                   // res.put("code",-1);
                    res.put("code",0);
                }

            }else {
                //res.put("code",-1);
                res.put("code",0);
            }
        }else {
            //res.put("code",-1);
            res.put("code",0);
        }
        return res;
    }

}