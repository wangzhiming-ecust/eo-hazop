package com.rao.kg.dao;

public interface NormalNodeSql {

    public String node2NewNode(String node_name, String node_type, String query_type, int query_number);

    public String newNode2node(String node_name, String node_type, String query_type, int query_number);
}
