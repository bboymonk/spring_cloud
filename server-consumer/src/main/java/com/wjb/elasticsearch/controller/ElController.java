package com.wjb.elasticsearch.controller;

import com.wjb.elasticsearch.config.ElRestClient;
import com.wjb.elasticsearch.service.ElClientService;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ElController {

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private ElClientService elClientService;

    @RequestMapping("el/createIndex")
    public String createIndex(){
        try {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("name","wjb");
            map.put("age","20");
            map.put("sex","boy");
            IndexRequest indexRequest = elClientService.createIndex("1", "firstIndex", map);
            //同步方式
            IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);

            String index = indexResponse.getIndex();
            String id = indexResponse.getId();
            if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {

            } else if (indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {

            }
            ReplicationResponse.ShardInfo shardInfo = indexResponse.getShardInfo();
            if (shardInfo.getTotal() != shardInfo.getSuccessful()) {

            }
            if (shardInfo.getFailed() > 0) {
                for (ReplicationResponse.ShardInfo.Failure failure :shardInfo.getFailures()) {
                    String reason = failure.reason();
                    System.out.println(reason);
                }
            }

            //异步方式
            /*client.indexAsync(indexRequest, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
                @Override
                public void onResponse(IndexResponse indexResponse) {

                }

                @Override
                public void onFailure(Exception e) {

                }
            });*/

        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

}
