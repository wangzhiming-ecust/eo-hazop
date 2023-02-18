package com.rao.kg.dao.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.RiotException;
import org.apache.jena.util.FileManager;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class QuerySparqlFromNtFile {

    //static final String filename = "F:\\rdf.nt";
    static final String filename = "D:/毕业论文/程序/kg/root/rdf.nt";

    private Model model;
    public void loadNtFile() throws RiotException {
        if (model != null){
            model.close();
        }
        model = ModelFactory.createDefaultModel();
        System.gc();
        InputStream in = FileManager.getInternal().open(filename);
//        if (in == null)
//        {
//            throw new IllegalArgumentException("File: " + filename + " not found");
//        }
        //model.read(in, "","RDF/XML");//根据文件格式选用参数即可解析不同类型
        //model.read(in, "","N3");\

        if (in != null) {
            this.model.read(in, null, "N-TRIPLES");
            System.out.println("文件加载成功");
        }
        else {
            System.out.println("读取文件失败、文件为空或不存在");
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getRdfJson(String sql) {
//           loadNtFile();
//        model = ModelFactory.createDefaultModel();
//
//        InputStream in = FileManager.getInternal().open(filename);
//        if (in == null)
//        {
//            throw new IllegalArgumentException("File: " + filename + " not found");
//        }
//        //model.read(in, "","RDF/XML");//根据文件格式选用参数即可解析不同类型
//        //model.read(in, "","N3");
//        model.read(in, null,"N-TRIPLES");

        String xmlStr = null;
        Query query = QueryFactory.create(sql);
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        try {
            ResultSet rs = qe.execSelect();
            while (rs.hasNext()) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ResultSetFormatter.outputAsJSON(baos, rs);
                xmlStr = baos.toString();
                baos.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("查询Nt文件时发生错误");
        } finally {
            if (qe != null) {
                qe.close();
            }
        }
        JSONObject json = JSONObject.parseObject(xmlStr);
        return json;
    }


}
