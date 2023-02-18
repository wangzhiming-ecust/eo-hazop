package com.rao.kg.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rao.kg.pojo.JsonMenuOrPre;
import com.rao.kg.pojo.Link;
import com.rao.kg.pojo.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class GetSubOrObj {


    @Autowired
    JsonMenuOrPre jsonMenuOrPre;
    @Autowired
    EncodeAndDecode encodeAndDecode;
    //获取主语和LINKS
    public List getSubAndLink(JSONObject json, String query_type) {

        Set<Node> nodes = new HashSet();
        List<Link> links = new ArrayList();

        JSONArray jsonArray = json.getJSONObject("results").getJSONArray("bindings");

        //生成新的JSON格式
        for (Object obj :
                jsonArray) {
            Map<String, Map<String, String>> map = (Map<String, Map<String, String>>) obj;
            String sub_value = map.get("slabel").get("value");

            if (sub_value != null) {
//                source_id = node_map.get(split_sub[1]);
                sub_value = encodeAndDecode.decode(sub_value);
            } else {
                sub_value = "数据库空";
//                source_id = node_map.get(sub_value);
            }

            nodes.add(new Node(sub_value, query_type, sub_value));

            String obj_value = map.get("olabel").get("value");

            if (obj_value != null) {
                //split分割数组长度为2
//                target_id = node_map.get(split_obj[1]);
                obj_value = encodeAndDecode.decode(obj_value);
            } else {
                //split分割数组长度为1
//                target_id = node_map.get(obj_value);
//                Nodes.add(new Node(target_id, menu_type, obj_value));
                obj_value = "数据库空";
            }
            String pre_value = map.get("p").get("value");
            String[] split_pre = pre_value.split("#");
            if (split_pre.length > 1) {
                //split分割数组长度为2
//                target_id = node_map.get(split_obj[1]);
                pre_value = split_pre[1];
            } else {
                //split分割数组长度为1
//                target_id = node_map.get(obj_value);
//                Nodes.add(new Node(target_id, menu_type, obj_value));
                pre_value = "";
            }
            String pre_str = jsonMenuOrPre.getPre(pre_value);
            String linkId = sub_value + "_" + pre_str + "_" + obj_value;

            links.add(new Link(linkId, sub_value, pre_str, obj_value));
        }
        List<Object> list = new ArrayList();
        list.add(nodes);
        list.add(links);
        return list;
    }

    //获取宾语和LINKS
    public List getObjAndLink(JSONObject json, String query_type) {

        Set<Node> nodes = new HashSet();
        List<Link> links = new ArrayList();

        JSONArray jsonArray = json.getJSONObject("results").getJSONArray("bindings");
        //生成新的JSON格式
        for (Object obj :
                jsonArray) {

            Map<String, Map<String, String>> map = (Map<String, Map<String, String>>) obj;
            String sub_value = map.get("slabel").get("value");
//            String[] split_sub = sub_value.split(":");
//            for (String a: split_sub
//                 ) {
//                System.out.println(a);
//            }

//            if (split_sub.length > 1) {
////                source_id = node_map.get(split_sub[1]);
//                sub_value = encodeAndDecode.decode(split_sub[1]);
//            } else {
//                sub_value = "数据库空";
////                source_id = node_map.get(sub_value);
//            }

            String obj_value = map.get("olabel").get("value");
//            String[] split_obj = obj_value.split(":");
//            if (split_obj.length > 1) {
//                //split分割数组长度为2
////                target_id = node_map.get(split_obj[1]);
//                obj_value = encodeAndDecode.decode(split_obj[1]);
//            } else {
//                //split分割数组长度为1
////                target_id = node_map.get(obj_value);
////                Nodes.add(new Node(target_id, menu_type, obj_value));
//                obj_value = "数据库空";
//            }

            nodes.add(new Node(obj_value, query_type, obj_value));
//
            String pre_value = map.get("p").get("value");
            String[] split_pre = pre_value.split("#");
            if (split_pre.length > 1) {
                //split分割数组长度为2
//                target_id = node_map.get(split_obj[1]);
                pre_value = split_pre[1];
            } else {
                //split分割数组长度为1
//                target_id = node_map.get(obj_value);
//                Nodes.add(new Node(target_id, menu_type, obj_value));
                pre_value = "";
            }
//            String[] split_pre = pre_value.split("onto:");
//            if (split_pre.length > 1) {
//                //split分割数组长度为2
////                target_id = node_map.get(split_obj[1]);
//                pre_value = split_pre[1];
//            } else {
//                //split分割数组长度为1
////                target_id = node_map.get(obj_value);
//                Nodes.add(new Node(target_id, menu_type, obj_value));
//                pre_value = "";
//            }
            String pre_str = jsonMenuOrPre.getPre(pre_value);

            String linkId = sub_value + "__" + obj_value;
            links.add(new Link(linkId, sub_value, pre_str, obj_value));

        }
        List<Object> list = new ArrayList();
        list.add(nodes);
        list.add(links);

        return list;
    }


}
