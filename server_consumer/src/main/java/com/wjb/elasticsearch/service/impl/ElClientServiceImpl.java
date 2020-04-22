package com.wjb.elasticsearch.service.impl;


import com.wjb.elasticsearch.config.ApplicationContextHolder;
import com.wjb.elasticsearch.model.HighLevelRestClientObject;
import com.wjb.elasticsearch.service.ElClientService;
import com.wjb.util.PageResult;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ElClientServiceImpl<T> implements ElClientService<T> {

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private HighLevelRestClientObject hlrcObject;


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
        QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("1", "kimchy")
                                                        .fuzziness(Fuzziness.AUTO)
                                                        .prefixLength(3)
                                                        .maxExpansions(10);
        SearchSourceBuilder sourceBuilder = hlrcObject.createSearchSourceBuilder().query(matchQueryBuilder);
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("posts");
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit : searchHits) {
            hit.getSourceAsString()
        }
        return null;
    }



}
