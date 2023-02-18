package com.rao.kg.dao.impl;

import com.rao.kg.dao.SpecialNodeSql;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SpecialNodeSqlSqlImpl implements SpecialNodeSql {

    //返回list[0]为sql，list[1]为新节点类型,依次类推
    //Enterprise2Asset菜单的查询
    @Override
    public List<String> Enterprise2Asset(String node_name, String node_type, int limit) {

        String query_type1 = "Chemicals";
        //企业->化学品
        String sql1 = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX : <http://www.semanticweb.org/rob/ontologies/2020/9/jinshandata#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "SELECT ?s ?p ?o\n" +
                "WHERE {\n" +
                "  ?s ?p ?o .\n" +
                "  ?s a :Enterprise. \n" +
                "  ?o a :Chemicals.\n" +
                "  Filter(?s = :" + node_name + ")\n" +
                "}LIMIT" + limit;
        //企业->载具
        String query_type2 = "Vehicle";
        String sql2 = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX : <http://www.semanticweb.org/rob/ontologies/2020/9/jinshandata#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "SELECT ?s ?p ?o\n" +
                "WHERE {\n" +
                "  ?s ?p ?o .\n" +
                "  ?s a :Enterprise. \n" +
                "  ?o a :Vehicle.\n" +
                "  Filter(?s = :" + node_name + ")\n" +
                "}LIMIT" + limit;
        //企业->特种设备
        String query_type3 = "SpecialEquipment";
        String sql3 = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX : <http://www.semanticweb.org/rob/ontologies/2020/9/jinshandata#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "SELECT ?s ?p ?o\n" +
                "WHERE {\n" +
                "  ?s ?p ?o .\n" +
                "  ?s a :Enterprise. \n" +
                "  ?o a :SpecialEquipment.\n" +
                "  Filter(?s = :" + node_name + ")\n" +
                "}LIMIT" + limit;

        //企业->应急资源
        String query_type4 = "EmergencyResource";
        String sql4 = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX : <http://www.semanticweb.org/rob/ontologies/2020/9/jinshandata#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "SELECT ?s ?p ?o\n" +
                "WHERE {\n" +
                "  ?s ?p ?o .\n" +
                "  ?s a :Enterprise. \n" +
                "  ?o a :EmergencyResource.\n" +
                "  Filter(?s = :" + node_name + ")\n" +
                "}LIMIT" + limit;

        List list = new ArrayList();

        list.add(sql1);
        list.add(query_type1);
        list.add(sql2);
        list.add(query_type2);
        list.add(sql3);
        list.add(query_type3);
        list.add(sql4);
        list.add(query_type4);
        return list;
    }


    //Enterprise2Event菜单的查询
    @Override
    public List<String> Enterprise2Event(String node_name, String node_type, int limit) {

        //事故->企业
        String query_type1 = "Accident";
        String sql1 = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX : <http://www.semanticweb.org/rob/ontologies/2020/9/jinshandata#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "SELECT ?s ?p ?o\n" +
                "WHERE {\n" +
                "  ?s ?p ?o .\n" +
                "  ?s a :Accident. \n" +
                "  ?o a :Enterprise.\n" +
                "  Filter(?o = :" + node_name + ")\n" +
                "}Limit" + limit;

        //应急事件->企业
        String query_type2 = "EmergencyEvent";
        String sql2 = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX : <http://www.semanticweb.org/rob/ontologies/2020/9/jinshandata#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "SELECT ?s ?p ?o\n" +
                "WHERE {\n" +
                "  ?s ?p ?o .\n" +
                "  ?s a :EmergencyEvent. \n" +
                "  ?o a :Enterprise.\n" +
                "  Filter(?o = :" + node_name + ")\n" +
                "}LIMIT" + limit;
        //企业->应急事件
        String query_type3 = "EmergencyEvent";
        String sql3 = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX : <http://www.semanticweb.org/rob/ontologies/2020/9/jinshandata#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "SELECT ?s ?p ?o\n" +
                "WHERE {\n" +
                "  ?s ?p ?o .\n" +
                "  ?s a :Enterprise. \n" +
                "  ?o a :EmergencyEvent.\n" +
                "  Filter(?s = :" + node_name + ")\n" +
                "}LIMIT" + limit;

        List list = new ArrayList();

        list.add(sql1);
        list.add(query_type1);
        list.add(sql2);
        list.add(query_type2);
        list.add(sql3);
        list.add(query_type3);
        return list;
    }

    //Enterprise2Other菜单的查询
    @Override
    public List<String> Enterprise2Other(String node_name, String node_type, int limit) {



        //企业->政府部门
        String query_type1 = "Government";
        String sql1 = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX : <http://www.semanticweb.org/rob/ontologies/2020/9/jinshandata#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "SELECT ?s ?p ?o\n" +
                "WHERE {\n" +
                "  ?s ?p ?o .\n" +
                "  ?s a :Enterprise. \n" +
                "  ?o a :Government.\n" +
                "  Filter(?s = :" + node_name + ")\n" +
                "} ";

        //政府部门->企业
        String query_type2 = "Government";
        String sql2 = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX : <http://www.semanticweb.org/rob/ontologies/2020/9/jinshandata#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "SELECT ?s ?p ?o\n" +
                "WHERE {\n" +
                "  ?s ?p ?o .\n" +
                "  ?s a :Government. \n" +
                "  ?o a :Enterprise.\n" +
                "  Filter(?o = :" + node_name + ")\n" +
                "}LIMIT" + limit;

        //企业->安全风险
        String query_type3 = "Risk";
        String sql3 = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX : <http://www.semanticweb.org/rob/ontologies/2020/9/jinshandata#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "SELECT ?s ?p ?o\n" +
                "WHERE {\n" +
                "  ?s :has ?o .\n" +
                "  ?s a :Enterprise. \n" +
                "  ?o a :Risk.\n" +
                "  Filter(?s = :" + node_name + ")\n" +
                "} LIMIT" + limit;

        //企业->应急预案
        String query_type4 = "EmergencyPlan";
        String sql4 = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX : <http://www.semanticweb.org/rob/ontologies/2020/9/jinshandata#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "SELECT ?s ?p ?o\n" +
                "WHERE {\n" +
                "  ?s ?p ?o .\n" +
                "  ?s a :Enterprise. \n" +
                "  ?o a :EmergencyPlan.\n" +
                "  Filter(?s = :" + node_name + ")\n" +
                "} LIMIT" + limit;

        //企业->化工工艺
        String query_type5 = "ChemicalProcess";
        String sql5 = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX : <http://www.semanticweb.org/rob/ontologies/2020/9/jinshandata#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "SELECT ?s ?p ?o\n" +
                "WHERE {\n" +
                "  ?s ?p ?o .\n" +
                "  ?s a :Enterprise. \n" +
                "  ?o a :ChemicalProcess.\n" +
                "  Filter(?s = :" + node_name + ")\n" +
                "} LIMIT" + limit;
        List list = new ArrayList();

        list.add(sql1);
        list.add(query_type1);
        list.add(sql2);
        list.add(query_type2);
        list.add(sql3);
        list.add(query_type3);
        list.add(sql4);
        list.add(query_type4);
        list.add(sql5);
        list.add(query_type5);
        return list;
    }

    //化学品出发的查询
    @Override
    public List Chemicals2MajorDangerSource(String node_name, String node_type, int limit) {

        //重大危险源->化学品
        String query_type1 = "MajorDangerSource";
        String sql1 = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX : <http://www.semanticweb.org/rob/ontologies/2020/9/jinshandata#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "SELECT ?s ?p ?o\n" +
                "WHERE {\n" +
                "  ?s ?p ?o .\n" +
                "  ?s a :MajorDangerSource. \n" +
                "  ?o a :Chemicals.\n" +
                "  Filter(?o = :" + node_name + ")\n" +
                "} LIMIT" + limit;


        //车间->化学品
        String query_type2 = "Workshop";
        String sql2 = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX : <http://www.semanticweb.org/rob/ontologies/2020/9/jinshandata#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "SELECT ?s ?p ?o\n" +
                "WHERE {\n" +
                "  ?s ?p ?o .\n" +
                "  ?s a :Workshop. \n" +
                "  ?o a :Chemicals.\n" +
                "  Filter(?o = :" + node_name + ")\n" +
                "} LIMIT" + limit;

        //仓库->化学品
        String query_type3 = "Warehouse";
        String sql3 = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX : <http://www.semanticweb.org/rob/ontologies/2020/9/jinshandata#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "SELECT ?s ?p ?o\n" +
                "WHERE {\n" +
                "  ?s ?p ?o .\n" +
                "  ?s a :Warehouse. \n" +
                "  ?o a :Chemicals.\n" +
                "  Filter(?o = :" + node_name + ")\n" +
                "} LIMIT" + limit;

        //储罐->化学品
        String query_type4 = "StorageTank";
        String sql4 = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX : <http://www.semanticweb.org/rob/ontologies/2020/9/jinshandata#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "SELECT ?s ?p ?o\n" +
                "WHERE {\n" +
                "  ?s ?p ?o .\n" +
                "  ?s a :StorageTank. \n" +
                "  ?o a :Chemicals.\n" +
                "  Filter(?o = :" + node_name + ")\n" +
                "} LIMIT" + limit;

        List list = new ArrayList();

        list.add(sql1);
        list.add(query_type1);
        //1表示下一步选取主语的函数，2表示取宾语
        list.add(1);
        list.add(sql2);
        list.add(query_type2);
        list.add(1);
        list.add(sql3);
        list.add(query_type3);
        list.add(1);
        list.add(sql4);
        list.add(query_type4);
        list.add(1);
        return list;
    }

    //重大危险源出发的查询
    @Override
    public List<String> MajorDangerSource2Equipment(String node_name, String node_type, int limit) {

        String query_type1 = "StorageTank";
        //重大危险源->储罐
        String sql1 = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX : <http://www.semanticweb.org/rob/ontologies/2020/9/jinshandata#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "SELECT ?s ?p?o\n" +
                "WHERE {\n" +
                "  ?s ?p ?o .\n" +
                "  ?s a :MajorDangerSource. \n" +
                "  ?o a :StorageTank.\n" +
                "  Filter(?s = :" + node_name + ")\n" +
                "} LIMIT" + limit;
        //重大危险源->车间
        String query_type2 = "Workshop";
        String sql2 = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX : <http://www.semanticweb.org/rob/ontologies/2020/9/jinshandata#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "SELECT ?s ?p?o\n" +
                "WHERE {\n" +
                "  ?s ?p ?o .\n" +
                "  ?s a :MajorDangerSource. \n" +
                "  ?o a :Workshop.\n" +
                "  Filter(?s = :" + node_name + ")\n" +
                "} LIMIT" + limit;
        //重大危险源->装置
        String query_type3 = "Device";
        String sql3 = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX : <http://www.semanticweb.org/rob/ontologies/2020/9/jinshandata#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "SELECT ?s ?p ?o\n" +
                "WHERE {\n" +
                "  ?s ?p ?o .\n" +
                "  ?s a :MajorDangerSource. \n" +
                "  ?o a :Device.\n" +
                "  Filter(?s = :" + node_name + ")\n" +
                "} LIMIT" + limit;
        //重大危险源->仓库
        String query_type4 = "Warehouse";
        String sql4 = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX : <http://www.semanticweb.org/rob/ontologies/2020/9/jinshandata#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "SELECT ?s ?p ?o\n" +
                "WHERE {\n" +
                "  ?s ?p ?o .\n" +
                "  ?s a :MajorDangerSource. \n" +
                "  ?o a :Warehouse.\n" +
                "  Filter(?s = :" + node_name + ")\n" +
                "} LIMIT" + limit;

        List list = new ArrayList();

        list.add(sql1);
        list.add(query_type1);
        list.add(2);
        list.add(sql2);
        list.add(query_type2);
        list.add(2);
        list.add(sql3);
        list.add(query_type3);
        list.add(2);
        list.add(sql4);
        list.add(query_type4);
        list.add(2);
        return list;
    }


}
