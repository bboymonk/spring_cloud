package com.wjb.elasticsearch.service.impl;


import com.wjb.elasticsearch.model.HighLevelRestClientObject;
import com.wjb.elasticsearch.service.BaseMethod;
import com.wjb.util.PageResult;
import org.elasticsearch.client.RestClient;

import java.util.List;

public class BaseMethodImpl<T> implements BaseMethod {


    @Override
    public void close() throws Exception {

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
    public Object get(Long id) throws Exception {
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
