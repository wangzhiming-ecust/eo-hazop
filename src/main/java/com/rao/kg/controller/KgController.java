package com.rao.kg.controller;

import com.rao.kg.service.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Data
@RestController
public class KgController {


    private boolean extraStatus = false;

    @Autowired
    InitialNode initialNode;
    @Autowired
    Extraction extraction;
    @Autowired
    QueryNodeLink queryNodeLink;
    @Autowired
    SearchNode searchNode;
    @Autowired
    Reasoner reasoner;
    @Autowired
    LinkPrediction linkPrediction;

    @Value("${spring.kg.endpoint}")
    String endpoint;

    @GetMapping("/helloworld")
    public String helloworld() {
        return "帅哥，jar包服务已启动，祝工作顺利、生活愉快";
    }

    @GetMapping("/KG/extra")
    public String extract(){
        extraction.extractionMethod();
        return "完成抽取过程";
    }

    //初始化接口
    @GetMapping(value = "/KG/init", produces = "application/json;charset=UTF-8")
    Map<String, Object> init(@RequestParam String node_type, @RequestParam int init_num_limit) {
        Map<String, Object> res = new HashMap<>();;;
        if (!extraStatus){
        res = initialNode.getIntialNode(node_type, init_num_limit);
        }else {
            res.put("code",0);
        }
        queryNodeLink.reasonNumber = 0;
        return res;
    }


    //查询接口
    @GetMapping(value = "/KG/Query", produces = "application/json;charset=UTF-8")
    Map<String, Object> query(@RequestParam String node_name, @RequestParam String node_type, @RequestParam String query_type, @RequestParam int query_num_limit) {
        node_name = node_name.replace(" ", "%20");
        Map<String, Object> res = new HashMap<>();;
        if (!extraStatus){
            res = queryNodeLink.getQueryNodeLink(node_name, node_type, query_type, endpoint, query_num_limit);
        }else {
            res.put("code",0);
        }
        return res;
    }


    //搜索接口
    @GetMapping(value = "/KG/Search", produces = "application/json;charset=UTF-8")
    Map<String, Object> search(@RequestParam String search_name) {
        Map<String, Object> res = new HashMap<>();
        if (!extraStatus){
            res = searchNode.searchNode(search_name);
        }else {
            res.put("code",0);
        }
        return res;
    }

    //推理接口
    @GetMapping(value = "/KG/Reason",produces = "application/json;charset=UTF-8")
    public String Reason() throws IOException {
        reasoner.Reasoning();
        return "完成推理";
    }

    //链接预测接口
    @GetMapping(value = "/KG/LinkPrediction",produces = "application/json;charset=UTF-8")
    public String LinkPrediction(){
        linkPrediction.LinkPre();
        return "完成链接预测";
    }


}
