package com.rao.kg.dao;

import java.util.List;

public interface SpecialNodeSql {
    List Enterprise2Asset(String node_name, String node_type, int limit);

    List Enterprise2Event(String node_name, String node_type, int limit);

    List Enterprise2Other(String node_name, String node_type, int limit);

    List Chemicals2MajorDangerSource(String node_name, String node_type, int limit);

    List MajorDangerSource2Equipment(String node_name, String node_type, int limit);
}
