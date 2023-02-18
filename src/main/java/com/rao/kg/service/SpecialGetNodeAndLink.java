package com.rao.kg.service;

import com.alibaba.fastjson.JSONObject;
import com.rao.kg.dao.impl.QuerySparqlFromEndpoint;
import com.rao.kg.dao.impl.QuerySparqlFromNtFile;
import com.rao.kg.dao.impl.SpecialNodeSqlSqlImpl;
import com.rao.kg.pojo.JsonMenuOrPre;
import com.rao.kg.pojo.Link;
import com.rao.kg.pojo.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SpecialGetNodeAndLink {

    @Autowired
    SpecialNodeSqlSqlImpl specialNodeSqlSql;
    @Autowired
    GetSubOrObj getSubOrObj;
    @Autowired
    QuerySparqlFromNtFile querySparqlFromNtFile;
    @Autowired
    JsonMenuOrPre jsonMenuOrPre;

    @Value("${spring.kg.endpoint}")
    private String endpoint;

    //企业->资产
    List getEnterprise2AssetNodeLink(String node_name, String node_type, int limit) {
        Set<Node> nodes = new HashSet();
        List<Link> links = new ArrayList();
        Map<String, List<Map<String, String>>> menus = new HashMap<>();
        List<String> list = specialNodeSqlSql.Enterprise2Asset(node_name, node_type, limit);

        String sql1 = list.get(0);
        String query_type1 = list.get(1);
        JSONObject json1 = querySparqlFromNtFile.getRdfJson(sql1);
        if (json1 != null) {
            menus.put(query_type1, jsonMenuOrPre.getJson(query_type1));
            List temp = new ArrayList();
            temp = getSubOrObj.getObjAndLink(json1, query_type1);
            nodes.addAll((Set<? extends Node>) temp.get(0));
            links.addAll((List<? extends Link>) temp.get(1));
        }

        String sql2 = list.get(2);
        String query_type2 = list.get(3);
        JSONObject json2 = querySparqlFromNtFile.getRdfJson(sql2);
        if (json2 != null) {
            menus.put(query_type2, jsonMenuOrPre.getJson(query_type2));
            List temp = new ArrayList();
            temp = getSubOrObj.getObjAndLink(json2, query_type2);
            nodes.addAll((Set<? extends Node>) temp.get(0));
            links.addAll((List<? extends Link>) temp.get(1));
        }

        String sql3 = list.get(4);
        String query_type3 = list.get(5);
        JSONObject json3 = querySparqlFromNtFile.getRdfJson(sql3);
        if (json3 != null) {
            menus.put(query_type3, jsonMenuOrPre.getJson(query_type3));
            List temp = new ArrayList();
            temp = getSubOrObj.getObjAndLink(json3, query_type3);
            nodes.addAll((Set<? extends Node>) temp.get(0));
            links.addAll((List<? extends Link>) temp.get(1));
        }

        String sql4 = list.get(6);
        String query_type4 = list.get(7);
        JSONObject json4 = querySparqlFromNtFile.getRdfJson(sql4);
        if (json4 != null) {
            menus.put(query_type4, jsonMenuOrPre.getJson(query_type4));
            List temp = new ArrayList();
            temp = getSubOrObj.getObjAndLink(json4, query_type4);
            nodes.addAll((Set<? extends Node>) temp.get(0));
            links.addAll((List<? extends Link>) temp.get(1));
        }

        List<Object> res = new ArrayList();
        res.add(nodes);
        res.add(links);
        res.add(menus);
        return res;

    }

    //企业事件菜单获得的数据
    List getEnterprise2EventNodeLink(String node_name, String node_type, int limit) {
        Set<Node> nodes = new HashSet();
        List<Link> links = new ArrayList();
        Map<String, List<Map<String, String>>> menus = new HashMap<>();
        List<String> list = specialNodeSqlSql.Enterprise2Event(node_name, node_type, limit);

        String sql1 = list.get(0);
        String query_type1 = list.get(1);
        JSONObject json1 = querySparqlFromNtFile.getRdfJson(sql1);
        if (json1 != null) {
            menus.put(query_type1, jsonMenuOrPre.getJson(query_type1));
            List temp = new ArrayList();
            temp = getSubOrObj.getSubAndLink(json1, query_type1);
            nodes.addAll((Set<? extends Node>) temp.get(0));
            links.addAll((List<? extends Link>) temp.get(1));
        }

        String sql2 = list.get(2);
        String query_type2 = list.get(3);
        JSONObject json2 = querySparqlFromNtFile.getRdfJson(sql2);
        if (json2 != null) {
            menus.put(query_type2, jsonMenuOrPre.getJson(query_type2));
            List temp = new ArrayList();
            temp = getSubOrObj.getSubAndLink(json2, query_type2);
            nodes.addAll((Set<? extends Node>) temp.get(0));
            links.addAll((List<? extends Link>) temp.get(1));
        }

        String sql3 = list.get(4);
        String query_type3 = list.get(5);
        JSONObject json3 = querySparqlFromNtFile.getRdfJson(sql3);
        if (json3 != null) {
            menus.put(query_type3, jsonMenuOrPre.getJson(query_type3));
            List temp = new ArrayList();
            temp = getSubOrObj.getObjAndLink(json3, query_type3);
            nodes.addAll((Set<? extends Node>) temp.get(0));
            links.addAll((List<? extends Link>) temp.get(1));
        }

        List<Object> res = new ArrayList();
        res.add(nodes);
        res.add(links);
        res.add(menus);
        return res;

    }

    //企业“其他”菜单获得的数据
    List getEnterprise2OtherNodeLink(String node_name, String node_type, int limit) {

        List temp = new ArrayList();
        Set<Node> nodes = new HashSet();
        List<Link> links = new ArrayList();
        Map<String, List<Map<String, String>>> menus = new HashMap<>();
        List<String> list = specialNodeSqlSql.Enterprise2Other(node_name, node_type, limit);

        String sql1 = list.get(0);
        String query_type1 = list.get(1);
        JSONObject json1 = querySparqlFromNtFile.getRdfJson(sql1);
        if (json1 != null) {
            menus.put(query_type1, jsonMenuOrPre.getJson(query_type1));

            temp = getSubOrObj.getObjAndLink(json1, query_type1);
            nodes.addAll((Set<? extends Node>) temp.get(0));
            links.addAll((List<? extends Link>) temp.get(1));
        }

        String sql2 = list.get(2);
        String query_type2 = list.get(3);
        JSONObject json2 = querySparqlFromNtFile.getRdfJson(sql2);
        if (json2 != null) {
            menus.put(query_type2, jsonMenuOrPre.getJson(query_type2));

            temp = getSubOrObj.getSubAndLink(json2, query_type2);
            nodes.addAll((Set<? extends Node>) temp.get(0));
            links.addAll((List<? extends Link>) temp.get(1));
        }

        String sql3 = list.get(4);
        String query_type3 = list.get(5);
        JSONObject json3 = querySparqlFromNtFile.getRdfJson(sql3);
        if (json3 != null) {
            menus.put(query_type3, jsonMenuOrPre.getJson(query_type3));

            temp = getSubOrObj.getObjAndLink(json3, query_type3);
            nodes.addAll((Set<? extends Node>) temp.get(0));
            links.addAll((List<? extends Link>) temp.get(1));
        }

        String sql4 = list.get(6);
        String query_type4 = list.get(7);
        JSONObject json4 = querySparqlFromNtFile.getRdfJson(sql4);
        if (json4 != null) {
            menus.put(query_type4, jsonMenuOrPre.getJson(query_type4));

            temp = getSubOrObj.getObjAndLink(json4, query_type4);
            nodes.addAll((Set<? extends Node>) temp.get(0));
            links.addAll((List<? extends Link>) temp.get(1));
        }

        String sql5 = list.get(8);
        String query_type5 = list.get(9);
        JSONObject json5 = querySparqlFromNtFile.getRdfJson(sql5);
        if (json5 != null) {
            menus.put(query_type5, jsonMenuOrPre.getJson(query_type5));
            temp = getSubOrObj.getObjAndLink(json5, query_type5);
            nodes.addAll((Set<? extends Node>) temp.get(0));
            links.addAll((List<? extends Link>) temp.get(1));
        }



        List<Object> res = new ArrayList();
        res.add(nodes);
        res.add(links);
        res.add(menus);
        return res;
    }

    //化学品出发的SQL查询函数
    List getChemicals2MajorDangerSourceNodeLink(String node_name, String node_type, int limit) {

        List temp = new ArrayList();
        Set<Node> nodes = new HashSet();
        List<Link> links = new ArrayList();
        Map<String, List<Map<String, String>>> menus = new HashMap<>();
        List list = specialNodeSqlSql.Chemicals2MajorDangerSource(node_name, node_type, limit);

        int i = list.size();
        for (int j = 0; j < i / 3; j++) {
            String sql = (String) list.get(3 * j);
            String query_type = (String) list.get(3 * j + 1);
            JSONObject json = querySparqlFromNtFile.getRdfJson(sql);
            if (json != null) {
                menus.put(query_type, jsonMenuOrPre.getJson(query_type));
                if (((Integer) list.get(3 * j + 2)) == 1) {
                    temp = getSubOrObj.getSubAndLink(json, query_type);
                    nodes.addAll((Set<? extends Node>) temp.get(0));
                    links.addAll((List<? extends Link>) temp.get(1));
                } else {
                    temp = getSubOrObj.getObjAndLink(json, query_type);
                    nodes.addAll((Set<? extends Node>) temp.get(0));
                    links.addAll((List<? extends Link>) temp.get(1));
                }
            }
        }
        List<Object> res = new ArrayList();
        res.add(nodes);
        res.add(links);
        res.add(menus);
        return res;
    }

    //重大危险源出发的SQL查询函数
    List getMajorDangerSource2Equipment(String node_name, String node_type, int limit) {

        List temp = new ArrayList();
        Set<Node> nodes = new HashSet();
        List<Link> links = new ArrayList();
        Map<String, List<Map<String, String>>> menus = new HashMap<>();
        List list = specialNodeSqlSql.MajorDangerSource2Equipment(node_name, node_type, limit);

        int i = list.size();
        for (int j = 0; j < i / 3; j++) {
            String sql = (String) list.get(3 * j);
            String query_type = (String) list.get(3 * j + 1);
            JSONObject json = querySparqlFromNtFile.getRdfJson(sql);
            if (json != null) {
                menus.put(query_type, jsonMenuOrPre.getJson(query_type));
                if (((Integer) list.get(3 * j + 2)) == 1) {
                    temp = getSubOrObj.getSubAndLink(json, query_type);
                    nodes.addAll((Set<? extends Node>) temp.get(0));
                    links.addAll((List<? extends Link>) temp.get(1));
                } else {
                    temp = getSubOrObj.getObjAndLink(json, query_type);
                    nodes.addAll((Set<? extends Node>) temp.get(0));
                    links.addAll((List<? extends Link>) temp.get(1));
                }
            }
        }
        List<Object> res = new ArrayList();
        res.add(nodes);
        res.add(links);
        res.add(menus);
        return res;
    }

}
