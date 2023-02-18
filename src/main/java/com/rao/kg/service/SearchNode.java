package com.rao.kg.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rao.kg.dao.impl.QuerySparqlFromNtFile;
import com.rao.kg.pojo.JsonMenuOrPre;
import com.rao.kg.pojo.Link;
import com.rao.kg.pojo.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SearchNode {

    @Autowired
    QuerySparqlFromNtFile querySparqlFromNtFile;
    @Autowired
    JsonMenuOrPre jsonMenuOrPre;

    public Map<String, Object> searchNode(String search_name) {

        Set<Node> nodes = new HashSet();
        List<Link> links = new ArrayList();
        Map<String, List<Map<String, String>>> menus = new HashMap<>();

        String sql = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "PREFIX : <http://www.semanticweb.org/rob/ontologies/2020/9/jinshandata#>\n" +
                "select ?name ?class where{\n" +
                "  ?prefix  rdf:type ?className.\n" +
                "  BIND(substr(str(?prefix),62) AS ?name)\n" +
                "  BIND(substr(str(?className),62) AS ?class)\n" +
                "  FILTER (REGEX(?name , '"+search_name.trim()+"', 'i'))\n" +
                "}\n";
        JSONObject json = querySparqlFromNtFile.getRdfJson(sql);
        Map<String, Object> res = new HashMap<>();
        if (json != null) {
            JSONArray jsonArray = json.getJSONObject("results").getJSONArray("bindings");
            //生成新的JSON格式
            if (!jsonArray.isEmpty()) {
//                int flag = -1;
                for (Object obj :
                        jsonArray) {
                    Map<String, Map<String, String>> map = (Map<String, Map<String, String>>) obj;
                    String name = map.get("name").get("value");
                    String type = map.get("class").get("value");
                    nodes.add(new Node(name, type, name));
                    menus.put(type, jsonMenuOrPre.getJson(type));
//                    flag = 0;
                }
                res.put("nodes", nodes);
                res.put("links", links);
                res.put("menu", menus);
                res.put("code",0);
             //   res.put("code", flag);
            }else {
             //   res.put("code",-1);
                res.put("code",0);
            }
        }else {
           //  res.put("code",-1);
            res.put("code",0);
        }
        return res;
    }

}
