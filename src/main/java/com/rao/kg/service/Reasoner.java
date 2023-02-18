package com.rao.kg.service;

import org.apache.jena.graph.Triple;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.reasoner.InfGraph;
import org.apache.jena.reasoner.rulesys.GenericRuleReasoner;
import org.apache.jena.reasoner.rulesys.Rule;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.util.PrintUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wangzhiming on 2022/10/25 23:36
 * 知识推理
 */


@Service
public class Reasoner {
    @Autowired
    QueryNodeLink queryNodeLink;
    public void Reasoning() throws IOException {
        queryNodeLink.reasonNumber++;
//        String finance = "http://www.keylab.org/ontology/ontohazop#";
//        Model myMod = ModelFactory.createDefaultModel();
//        OntModel om = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RDFS_INF,myMod);
//
//
//	    /*Resource 温度上升 = myMod.createResource(finance + "温度上升");
//	    Resource 事故1 = myMod.createResource(finance + "事故1");
//	    Resource 事故2 = myMod.createResource(finance + "事故2");
//	    Resource 事故3 = myMod.createResource(finance + "事故3");
//	    Property 导致 = myMod.createProperty(finance + "导致");
//
//	    // 加入三元组
//	    myMod.add(温度上升, 导致, 事故1);
//	    myMod.add(事故1, 导致, 事故2);
//	    myMod.add(事故2, 导致, 事故3);*/
//
//
//        myMod.read("file:D:\\git-coking\\sinopec-eo\\dataset\\rdf\\eo-hazop.owl");
//
//
//        PrintUtil.registerPrefix("", finance);
//
//        // 输出当前模型
//	    /*StmtIterator i = myMod.listStatements(null,null,(RDFNode)null);
//	    while (i.hasNext()) {
//	        System.out.println(" - " + PrintUtil.print(i.nextStatement()));
//	    }*/
//        List<Rule> rules = Rule.rulesFromURL("file:D:\\毕业论文\\程序\\test_sinopec.rules");
//
//        GenericRuleReasoner reasoner = new GenericRuleReasoner(rules);
//        //List<Rule> rules = Rule.rulesFromURL("file:D:\\git-coking\\coking\\dataset\\rules\\test.rules");
//        //GenericRuleReasoner reasoner = new GenericRuleReasoner(rules);
///*	    GenericRuleReasoner reasoner = (GenericRuleReasoner) GenericRuleReasonerFactory.theInstance().create(null);
//
//	    reasoner.setRules(Rule.parseRules(
//				"[rule1:(?x :tagID ?y),regex(?y, '(2R-.*)') -> (?x rdf:type :Reactor)] \n"
//						+"[rule2:(?x :tagID ?y),regex(?y, '(2C-.*)') -> (?x rdf:type :Compressor)] \n"
//						+"[rule3:(?x :tagID ?y),regex(?y, '(2D-.*)') -> (?x rdf:type :Tank)] \n"
//						+"[rule4:(?x :tagID ?y),regex(?y, '(2E-.*)') -> (?x rdf:type :HeatExchanger)] \n"
//						+"[rule5:(?x :tagID ?y),regex(?y, '(2G-.*)') -> (?x rdf:type :Pump)] \n"
//						+"[rule6:(?x :tagID ?y),regex(?y, '(2T-.*)') -> (?x rdf:type :Tower)] \n"
//	                    + "-> tableAll()."));*/
//        // reasoner.setMode(GenericRuleReasoner.HYBRID);
//        reasoner.setRules(rules);
//
//
//        InfModel inf = ModelFactory.createInfModel(reasoner, om);
//        String filepath = "C:\\Users\\wangzhiming\\Desktop\\毕业论文\\dataSet\\eo-hazop_result.owl";
//        FileOutputStream file = new FileOutputStream(filepath);
//        //inf.write(file, "RDF/XML-ABBREV");
//        RDFDataMgr.write(file,om, Lang.RDFXML);
//        file.close();
//
//        InfGraph infgraph = reasoner.bind(myMod.getGraph());
//        infgraph.setDerivationLogging(true);
//
//        System.out.println("推理后...\n");
//
//        Iterator<Triple> tripleIterator = infgraph.find(null, null, null);
//        while (tripleIterator.hasNext()) {
//            System.out.println(" - " + PrintUtil.print(tripleIterator.next()));
//        }



    }
}
