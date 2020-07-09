package com.wjb.elasticsearch.service;

import com.wjb.elasticsearch.model.HighLevelRestClientObject;
import com.wjb.util.PageResult;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestClient;

import java.util.List;
import java.util.Map;

public interface ElClientService<T> {

    /**
     * 创建索引
     * @param id
     * @param index
     * @param map
     * @return
     */
    IndexRequest createIndex(String id, String index, Map<String,Object> map);

    /**
     * 关闭连接
     */
    void close() throws Exception;

    /**
     * 获取低水平客户端
     * @return
     */
    RestClient getLowLevelClient();

    /**
     * 新增和修改数据
     */
    void insertOrUpdate(T t) throws Exception;

    /**
     * 删除数据
     */
    void delete(Long id) throws Exception;

    /**
     *通过文档id获取数据
     */
    T get(Long id) throws Exception;

    /**
     * 获取所有文档
     */
    List<T> getAll() throws Exception;

    /**
     * 搜索
     */
    List<String> search(HighLevelRestClientObject qo,String index) throws Exception;

}
