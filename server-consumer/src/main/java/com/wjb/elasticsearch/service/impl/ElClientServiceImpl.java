package com.wjb.elasticsearch.service.impl;


import com.wjb.elasticsearch.config.ApplicationContextHolder;
import com.wjb.elasticsearch.model.HighLevelRestClientObject;
import com.wjb.elasticsearch.service.ElClientService;
import com.wjb.util.PageResult;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.util.List;
import java.util.Map;

public class ElClientServiceImpl<T> implements ElClientService<T> {


    protected RestHighLevelClient client = ApplicationContextHolder.getBean("client");

    /**
     * 创建索引
     * @param id
     * @param index
     * @param map
     * @return
     */
    @Override
    public IndexRequest createIndex(String id, String index, Map<String,Object> map) {
         return new IndexRequest(index).id(id).source(map);
    }

    @Override
    public void close() throws Exception {
        if (client != null){
            client.close();
        }
    }

    @Override
    public RestClient getLowLevelClient() {
        return null;
    }

    @Override
    public void insertOrUpdate(Object o) throws Exception {

    }

    @Override
    public void delete(Long id) throws Exception {

    }

    @Override
    public T get(Long id) throws Exception {
        return null;
    }

    @Override
    public List getAll() throws Exception {
        return null;
    }

    @Override
    public PageResult search(HighLevelRestClientObject qo) throws Exception {


        return null;
    }



}
