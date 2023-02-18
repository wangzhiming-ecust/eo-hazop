package com.rao.kg.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rao.kg.controller.KgController;
import com.rao.kg.dao.impl.NormalNodeSqlImpl;
import com.rao.kg.dao.impl.QuerySparqlFromEndpoint;
import com.rao.kg.dao.impl.QuerySparqlFromNtFile;
import com.rao.kg.pojo.JsonMenuOrPre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.Map;


@Component
public class Extraction {

    @Autowired
    NormalNodeSqlImpl commonQuery;
    @Autowired
    JsonMenuOrPre jsonMenuOrPre;
    @Autowired
    QuerySparqlFromEndpoint querySparqlFromEndpoint;
    @Autowired
    QuerySparqlFromNtFile querySparqlFromNtFile;
    @Autowired
    GetSubOrObj getSubOrObj;
    @Autowired
    SpecialGetNodeAndLink specialGetNodeAndLink;
    @Autowired
    Extraction extraction;
    @Autowired
    KgController kgController;
    @Value("${spring.kg.endpoint}")
    String endpoint;
    @Value("${spring.kg.saveExtractionFile}")
    String filename;


    final private String[] sinopecClass = {"Deviations",
            "HazopInstance",
            "Consequence",
            "Abnormity",
            "FGS",
            "SIL",
            "BPCS",
            "physicalProtection",
            "Quantity",
            "pH",
            "Volume",
            "Pressure",
            "Time",
            "FlowRate",
            "TemperatureDiff",
            "Level",
            "Voltage",
            "Viscosity",
            "Composition",
            "Energy",
            "Revolution",
            "Velocity",
            "Frequency",
            "Outlet",
            "ShellPass",
            "TubePass",
            "InternalUpstream",
            "InternalDownstream",
            "Inlet",
            "Universal_Exhaust",
            "Universal_Feed",
            "Instrumentation",
            "Compressor",
            "Reactor",
            "Tower",
            "HeatExchanger",
            "Pump",
            "Furnace",
            "Tank",
            "Turbine",
            "Boiler",
            "ControlValve",
            "ActionsToTake",
            "Controller",
            "Instrumentation",
            "ProcessEquipment",
            "Recommendation",
            "Phase"};

    final private String[] sinopecObjectProperties = {
            "below",
            "deviatedOn",
            "hasUnitEquipment",
            "hasRecommendation",
            "hasComponent",
            "resultFrom",
            "isQuantityOf",
            "resultIn",
            "onSideOf",
            "onBottomOf",
            "onTopOf",
            "hasDeviation",
            "hasProtection",
            "hasQuantity",
            "hasAbnormity",
            "hasFlow",
            "hasInternalFlow",
            "hasTubePass",
            "hasOutlet",
            "hasInlet",
            "hasShellPass",
            "controls",
            "monitoring",
            "controlledBy",
            "isMonitoredBy",
            "connectTo",
            "connectedFrom",
            "hasProtectionLayer",
            "above"
    };

    final private String[] sinopecDataProperties = {
            "severityLevel",
            "tagID",
            "metricUnit",
            "initialRiskLevel",
            "altName",

            "hasGuideWorld",
            "frequenceOfCause",
            "frequenceOfConsequence",
            "numericValue",
            "numericalUpperLimit",
            "numericalLowerLimit",
            "residualRisk",
            "reducedRisk"
    };


    //10点10分执行，每两天一次
    @Scheduled(cron = "0 10 10 */2 * ?")
    @PostConstruct
    public void extractionMethod(){
        kgController.setExtraStatus(true);
//        extraction.extractionEntity();
  //      extraction.extractionObjectProperties();
    //    extraction.extractionDataProperties();
        querySparqlFromNtFile.loadNtFile();
        System.out.println("--------");
        kgController.setExtraStatus(false);
    }


    //抽取Class的实体
    public void extractionEntity() {

        for (int i = 0; i < sinopecClass.length; i++) {
            boolean appdend = true;
            String sqlExtraction = "PREFIX : <http://www.keylab.org/ontology/ontohazop#>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "SELECT DISTINCT ?o \n" +
                    "WHERE {\n" +
                    "  ?s a :"+sinopecClass[i]+".\n" +
                    "  ?s rdfs:label ?o"+".\n" +
                    "}";
            JSONObject json = querySparqlFromEndpoint.endpointSparql(sqlExtraction, endpoint);
            if (json != null){
                //第一次为覆盖，其他时候
                if (i == 0){
                    appdend = false;
                }
                else {appdend = true;
                }
                saveEntityToFile(json, filename, sinopecClass[i], appdend);
            }
            else {}
        }

    }

    //抽取?s :objectProperties ?p

    public void extractionObjectProperties() {

        for (int i = 0; i < sinopecObjectProperties.length; i++) {
            String sqlExtraction = "PREFIX : <http://www.keylab.org/ontology/ontohazop#>\n" +
                    "SELECT  ?s  ?o \n" +
                    "WHERE {\n" +
                    "  ?s  :"+sinopecObjectProperties[i]+" ?o.\n" +
                    "}";
            JSONObject json = querySparqlFromEndpoint.endpointSparql(sqlExtraction, endpoint);
            if (json != null){
                saveObjectPropertiesToFile(json, filename, sinopecObjectProperties[i], true);
            }
            else {}
        }

    }
    //抽取数据属性
    public void extractionDataProperties() {

        for (int i = 0; i < sinopecDataProperties.length; i++) {
            String sqlExtraction = "PREFIX : <http://www.keylab.org/ontology/ontohazop#>\n" +
                    "SELECT  ?s  ?o \n" +
                    "WHERE {\n" +
                    "  ?s  :"+sinopecDataProperties[i]+" ?o.\n" +
                    "}";
            JSONObject json = querySparqlFromEndpoint.endpointSparql(sqlExtraction, endpoint);
            if (json != null){
                saveObjectPropertiesToFile(json, filename, sinopecDataProperties[i], true);
            }
            else {}
        }

    }

    public void saveEntityToFile(JSONObject json, String filename, String nodeClass, Boolean appendOrNot) {

        JSONArray jsonArray = json.getJSONObject("results").getJSONArray("bindings");
        OutputStreamWriter write = null;
        BufferedWriter writer = null;
        try {
            File f = new File(filename);
            write = new OutputStreamWriter(new FileOutputStream(f,appendOrNot));
            writer = new BufferedWriter(write);
            if (!f.exists()) {
                f.createNewFile();
            }
            //读取N-triple字符串
            for (Object obj :
                    jsonArray) {
                Map<String, Map<String, String>> map = (Map<String, Map<String, String>>) obj;
                String s = map.get("o").get("value").replace("%20","").replace( "\t","").replace("\n","").replace("%28","（").replace("%29","）");

                String out = "<http://www.keylab.org/resource/hazop/" + s + ">\t<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>\t"
                        + "<http://www.keylab.org/ontology/ontohazop#"
                        +nodeClass +">."+System.getProperty("line.separator");

                writer.write(out);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try
            {
                writer.close();

            } catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                write.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void saveObjectPropertiesToFile(JSONObject json, String filename, String objectName, Boolean appendOrNot) {

        JSONArray jsonArray = json.getJSONObject("results").getJSONArray("bindings");
        OutputStreamWriter write = null;
        BufferedWriter writer = null;
        try {
            File f = new File(filename);
            write = new OutputStreamWriter(new FileOutputStream(f,appendOrNot));
            writer = new BufferedWriter(write);
            if (!f.exists()) {
                f.createNewFile();
            }
            //读取N-triple字符串
            for (Object obj :
                    jsonArray) {


                Map<String, Map<String, String>> map = (Map<String, Map<String, String>>) obj;
                String s =map.get("s").get("value").replace("%20","").replace( "\t","").replace("\n","").replace("%28","（").replace("%29","）");
                String o = map.get("o").get("value").replace("%20","").replace( "\t","").replace("\n","").replace("%28","（").replace("%29","）");
                String out = "<" + s + ">\t<http://www.keylab.org/ontology/ontohazop#"+objectName+">\t" +
                        "<"+ o +">."+System.getProperty("line.separator");

                writer.write(out);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try
            {
                writer.close();

            } catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                write.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}
