package com.rao.kg;

import com.alibaba.fastjson.JSONObject;

import com.rao.kg.service.Wang;
import org.apache.jena.ontology.OntModelSpec;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@SpringBootTest
class KgApplicationTests {

    @Test
    void contextLoads() {
        String str1 = "{'nodes':''}";
        Map<String, String> map = JSONObject.parseObject(str1, HashMap.class);
        System.out.println(map);
    }

    private String str = "";

    @Test
    public void replaceAchara() {
        String str1 = "{'has':'有',\n" +
                "'hasAccidentContact':'事故联系人',\n" +
                "'hasAlert':'警报',\n" +
                "'hasCarrierEnterprise':'承运企业',\n" +
                "'hasChargePerson':'负责人',\n" +
                "'hasDangerChemical':'危险源化学品'\n" +
                ",'hasDeliveryEnterprise':'发货企业'\n" +
                ",'hasDeliveryEnterpriseContacts':'发货企业联系人'\n" +
                ",'hasDied':'致死',\n" +
                "'hasDriver':'驾驶员',\n" +
                "'hasEmergencyPlanTeam':'涉及预案队伍',\n" +
                "'hasFormulate':'制定',\n" +
                "'hasHazards':'包含危险源',\n" +
                "'hasIn':'属于',\n" +
                "'hasInjury':'致伤',\n" +
                "'hasInspectionObject':'检查对象',\n" +
                "'hasInvolvedEnterprise':'涉及企业',\n" +
                "'hasInvolvedMaterial':'涉事物质',\n" +
                "'hasInvolvedPeople':'涉事人员',\n" +
                "'hasLegalPerson':'企业法人',\n" +
                "'hasMainSubstance':'主要物质',\n" +
                "'hasManage':'监管',\n" +
                "'hasMarshals':'执法人员',\n" +
                "'hasOwn':'拥有',\n" +
                "'hasOwnAsset':'拥有资产',\n" +
                "'hasPrincipalResponsiblePerson':'主要负责人',\n" +
                "'hasProducts':'生成物','hasProvide':'配备',\n" +
                "'hasRawMaterials':'使用原料',\n" +
                "'hasReactant':'反应物',\n" +
                "'hasReceiveEnterprise':'收货企业',\n" +
                "'hasReceiveEnterpriseContacts':'收货企业联系人',\n" +
                "'hasRegisteredSafetyEngineer':'注册安全工程师',\n" +
                "'hasRescueContact':'救援联系人',\n" +
                "'hasSafetyProductionManagementPerson':'安全生产管理人员',\n" +
                "'hasSpecialOperatePeople':'特种操作人员','hasSupercargo':'押运员',\n" +
                "'hasTransport':'运输工具',\n" +
                "'hasWaybillGoods':'运单货品',\n" +
                "'hasWorkSafetyPeople':'安全生产责任人',\n" +
                "'isDisposeOf':'参与处置','isDrive':'驾驶',\n" +
                "'isHandle':'处置','isLoad':'装载',\n" +
                "'isManage':'管理',\n" +
                "'isManaged':'归属管理机构',\n" +
                "'isMention':'提及',\n" +
                "'isMentioned':'被提及',\n" +
                "'isOperate':'操作',\n" +
                "'isProduct':'生产',\n" +
                "'isPublish':'颁布',\n" +
                "'isUse':'运用',\n" +
                "'isUseTechnology':'采用工艺'}";
        Map<String, String> map = JSONObject.parseObject(str1, HashMap.class);

        SimpleDateFormat ft = new SimpleDateFormat("EEE d MMM yyyy HH:mm:ss 'GMT'", Locale.ENGLISH);
        Date dateT = new Date( );
        System.out.println(ft.format(dateT));
        System.out.println("");
        String result =  null;
        try {
            result = java.net.URLEncoder.encode("", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("jieguoshi"+result);
//        return json;
    }
    @Autowired
    Wang wang;
    @Test
    public void test(){
        wang.main();
        wang.method();
    }




}
