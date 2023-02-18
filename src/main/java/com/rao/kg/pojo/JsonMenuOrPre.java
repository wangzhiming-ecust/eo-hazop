package com.rao.kg.pojo;

import com.alibaba.fastjson.JSONObject;
import org.apache.jena.tdb.store.Hash;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JsonMenuOrPre {
    //缺少化学品、企业、重大危险源、人员
    private String str = "{\n" +
            "\t\t'Accident': [{ name: '人员', type: 'People' },{ name: '企业', type: 'Enterprise' }],\n" +
            "\t\t'AlarmRecord':[{ name: '化学品', type: 'Chemicals' }],\n" +
            "\t\t'ChemicalProcess':[{ name: '化学品', type: 'Chemicals' },{ name: '企业', type: 'Enterprise'},{ name: '装置', type: 'Device' }],\n" +
            "\t\t'ChemicalReaction':[{ name: '化学品', type: 'Chemicals' }],\n" +
            "\t\t'Device':[{ name: '重大危险源', type: 'MajorDangerSource' }],\n" +
            "\t\t'EmergencyEvent':[{ name: '企业', type: 'Enterprise' },{ name: '政府部门', type: 'Government' }],\n" +
            "\t\t'EmergencyPlan':[{ name: '企业', type: 'Enterprise' },{ name: '救援队', type: 'RescueTeam' },{ name: '化学品', type: 'Chemicals' },{ name: '人员', type: 'People' }],\n" +
            "\t\t'EmergencyResource':[{ name: '企业', type: 'Enterprise' },{ name: '化学品', type: 'Chemicals' }],  \n" +
            "\t\t'Government':[{ name: '企业', type: 'Enterprise' },{ name: '人员', type: 'People' }],   \n" +
            "\t\t'Law':[{ name: '化学品', type: 'Chemicals' }],\n" +
            "\t\t'RescueTeam':[{ name: '应急预案', type: 'EmergencyPlan'},{ name: '人员', type: 'People' }],\n" +
            "\t\t'Risk': [{ name: '人员', type: 'People' },{ name: '企业', type: 'Enterprise' }],\n" +
            "\t\t'SpecialEquipment': [{ name: '人员', type: 'People' },{ name: '企业', type: 'Enterprise' }],\n" +
            "\t\t'StorageTank':[{ name: '化学品', type: 'Chemicals' },{ name: '重大危险源', type:'MajorDangerSource' }],\n" +
            "\t\t'Vehicle':[{ name: '企业', type: 'Enterprise' },{ name: '运单', type: 'Waybill' },{ name: '化学品', type: 'Chemicals' },{ name: '人员', type: 'People' }],\n" +
            "\t\t'Warehouse':[{ name: '化学品', type: 'Chemicals' },{ name: '重大危险源', type:'MajorDangerSource' }],\t\n" +
            "\t\t'Waybill':[{ name: '企业', type: 'Enterprise' },{ name: '载具', type: 'Vehicle' },{ name: '化学品', type: 'Chemicals' },{ name: '人员', type: 'People' }],\t  \n" +
            "\t\t'Workshop':[{ name: '化学品', type: 'Chemicals' },{ name: '重大危险源', type: 'MajorDangerSource' }],\n" +
            "\t\t'Enterprise':[{ name: '资产', type: 'Enterprise2Asset' },{ name: '事件', type: 'Enterprise2Event' },{ name: '人员', type: 'People' },{ name: '重大危险源', type: 'MajorDangerSource' },{ name: '其他', type: 'Enterprise2Other' },{ name: '运单', type: 'Waybill' }],\t \n" +
            "\t\t'Chemicals':[{ name: '涉及企业', type: 'Enterprise' },{ name: '危险源类', type: 'Chemicals2MajorDangerSource' },{ name: '载具', type: 'Vehicle' },{ name: '运单', type: 'Waybill' }],\t \n" +
            "\t\t'MajorDangerSource':[{ name: '企业', type: 'Enterprise' },{ name: '化学品', type: 'Chemicals' },{ name: '车间仓库等', type: 'MajorDangerSource2Equipment' }],   \n" +
            "\t\t'People':[{ name: '企业', type: 'Enterprise' },{ name: '载具', type: 'Vehicle' },{ name: '运单', type: 'Waybill' },{name: '救援队', type: 'RescueTeam'}], \n" +
            "    }";

    private String str1 = "{'below':'低于',\n" +
            "'deviatedOn':'偏差作用于',\n" +
            "'hasUnitEquipment':'包含单元设备',\n" +
            "'hasRecommendation':'包含建议',\n" +
            "'hasComponent':'包含组件',\n" +
            "'resultFrom':'原因为'\n" +
            ",'isQuantityOf':'参数属于'\n" +
            ",'resultIn':'后果为'\n" +
            ",'onSideOf':'在侧面',\n" +
            "'onBottomOf':'在底部',\n" +
            "'onTopOf':'在顶部',\n" +
            "'hasDeviation':'存在偏差',\n" +
            "'hasProtection':'存在安全防范措施',\n" +
            "'hasQuantity':'存在工艺参数',\n" +
            "'hasAbnormity':'存在异常',\n" +
            "'hasFlow':'存在物流',\n" +
            "'hasInternalFlow':'包含内部物流',\n" +
            "'hasTubePass':'壳程物流',\n" +
            "'hasOutlet':'有出料',\n" +
            "'hasInlet':'有进料',\n" +
            "'hasShellPass':'管程物流',\n" +
            "'controls':'控制',\n" +
            "'monitoring':'测量',\n" +
            "'controlledBy':'被控',\n" +
            "'isMonitoredBy':'被测量',\n" +
            "'connectTo':'连接到',\n" +
            "'connectedFrom':'连接自',\n" +
            "'hasProtectionLayer':'配备保护层',\n" +
            "'above':'高于'}";


    private Map<String, String> pre_map = new HashMap<>();
    private JSONObject json = null;

    public JsonMenuOrPre() {
        this.json = JSONObject.parseObject(str);
        this.pre_map = JSONObject.parseObject(str1, HashMap.class);
        ;
    }

    public List getJson(String key) {
        return this.json.getJSONArray(key);
    }

    public String getPre(String key) {
        return this.pre_map.get(key);
    }

}
