package com.rao.kg.service;

import com.alibaba.fastjson.JSONObject;
import com.rao.kg.dao.impl.NormalNodeSqlImpl;
import com.rao.kg.dao.impl.QuerySparqlFromEndpoint;
import com.rao.kg.dao.impl.QuerySparqlFromNtFile;
import com.rao.kg.pojo.JsonMenuOrPre;
import com.rao.kg.pojo.Link;
import com.rao.kg.pojo.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QueryNodeLink {

        @Autowired
        NormalNodeSqlImpl commonQuery;
        @Autowired
        JsonMenuOrPre jsonMenuOrPre;
        @Autowired
        QuerySparqlFromNtFile querySparqlFromNtFile;
        @Autowired
        QuerySparqlFromEndpoint querySparqlFromEndpoint;
        @Autowired
        GetSubOrObj getSubOrObj;
        @Autowired
        SpecialGetNodeAndLink specialGetNodeAndLink;
        @Value("http://172.20.138.202:3030/eo-hazop/query")
        String endpoint1;
        @Value("http://172.20.138.202:3030/eo-hazop1/query")
        String endpoint2;
        String endpoint3;
        public int reasonNumber = 0;


        public Map<String, Object>getQueryNodeLink(String node_name, String node_type, String query_type, String endpoint, int query_number){
                if(reasonNumber == 0){
                        endpoint3 = endpoint1;
                }else endpoint3 = endpoint2;
                Map<String, Object> res =new HashMap<>();
                Set<Node> nodes = new HashSet();
                List<Link> links = new ArrayList();
                Map<String, List<Map<String, String>>> menus = new HashMap<>();

                //五种特殊菜单请求
                if (query_type.equals("Enterprise2Asset")){

                        List list= specialGetNodeAndLink.getEnterprise2AssetNodeLink(node_name, node_type, query_number);
                        nodes.addAll((Set<? extends Node>) list.get(0));
                        links.addAll((List<? extends Link>) list.get(1));
                        menus.putAll((Map<? extends String, ? extends List<Map<String, String>>>) list.get(2));
                }
                else if (query_type.equals("Enterprise2Event")){

                        List list= specialGetNodeAndLink.getEnterprise2EventNodeLink(node_name, node_type, query_number);
                        nodes.addAll((Set<? extends Node>) list.get(0));
                        links.addAll((List<? extends Link>) list.get(1));
                        menus.putAll((Map<? extends String, ? extends List<Map<String, String>>>) list.get(2));
                }
                else if (query_type.equals("Enterprise2Other")){
                        List list= specialGetNodeAndLink.getEnterprise2OtherNodeLink(node_name, node_type, query_number);
                        nodes.addAll((Set<? extends Node>) list.get(0));
                        links.addAll((List<? extends Link>) list.get(1));
                        menus.putAll((Map<? extends String, ? extends List<Map<String, String>>>) list.get(2));
                }
                else if (query_type.equals("Chemicals2MajorDangerSource")){
                        List list= specialGetNodeAndLink.getChemicals2MajorDangerSourceNodeLink(node_name, node_type, query_number);
                        nodes.addAll((Set<? extends Node>) list.get(0));
                        links.addAll((List<? extends Link>) list.get(1));
                        menus.putAll((Map<? extends String, ? extends List<Map<String, String>>>) list.get(2));
                }
                else if (query_type.equals("MajorDangerSource2Equipment")){
                        List list= specialGetNodeAndLink.getMajorDangerSource2Equipment(node_name, node_type, query_number);
                        nodes.addAll((Set<? extends Node>) list.get(0));
                        links.addAll((List<? extends Link>) list.get(1));
                        menus.putAll((Map<? extends String, ? extends List<Map<String, String>>>) list.get(2));

                }

                //通用菜单请求
                else {
                       try {
                               //获取宾语和LINKS
                               String sql1 = commonQuery.node2NewNode(node_name, node_type, query_type, query_number);
                               //JSONObject json1 = querySparqlFromNtFile.getRdfJson(sql1);
                               JSONObject json1 = querySparqlFromEndpoint.endpointSparql(sql1, endpoint3);
                               System.out.println(json1);
                               if (json1 != null) {
                                       menus.put(query_type, jsonMenuOrPre.getJson(query_type));
                                       List list = new ArrayList();
                                       list = getSubOrObj.getObjAndLink(json1, query_type);
                                       nodes.addAll((Set<? extends Node>) list.get(0));
                                       links.addAll((List<? extends Link>) list.get(1));
                               }

                                //获取主语和Links
                               String sql2 = commonQuery.newNode2node(node_name, node_type, query_type, query_number);
                              // JSONObject json2 = querySparqlFromNtFile.getRdfJson(sql2);
                               JSONObject json2 = querySparqlFromEndpoint.endpointSparql(sql2,endpoint3);
                               if (json2 != null) {
                                       menus.put(query_type, jsonMenuOrPre.getJson(query_type));
                                       List list = new ArrayList();
                                       list = getSubOrObj.getSubAndLink(json2, query_type);
                                       nodes.addAll((Set<? extends Node>) list.get(0));
                                       links.addAll((List<? extends Link>) list.get(1));
                               }
                       }catch (Exception e){
                               System.out.println("查询类型:"+node_type+"->"+query_type+"查询:"+node_name);
                       }
                }
                if (nodes.isEmpty() || links.isEmpty()){
                     //   res.put("code",-1);
                        res.put("code",0);
                }else {
                        res.put("nodes", nodes);
                        res.put("links", links);
                        res.put("menu", menus);
                        res.put("code",0);
                }
                return res;

        }


}
