package com.wjb.elasticsearch.controller;

import com.wjb.elasticsearch.model.HighLevelRestClientObject;
import com.wjb.elasticsearch.service.ElClientService;
import com.wjb.util.PageResult;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ElController {

    private static final Logger logger = LoggerFactory.getLogger(ElController.class);

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private ElClientService elClientService;

    @RequestMapping("el/createIndex")
    public void createIndex(){
        try {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("name","wjb ok good nice");
            map.put("age","20");
            map.put("sex","boy");
            IndexRequest indexRequest = elClientService.createIndex("1", "bboy", map);
            //同步方式
            IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);

            String index = indexResponse.getIndex();
            String id = indexResponse.getId();
            System.out.println(index+"==="+id);
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
            logger.error("create index error",e);
        }
    }
    @RequestMapping("el/getIndex")
    public String getIndex(HttpServletRequest r,String index, String id,String query){
        try {
            GetRequest request = new GetRequest(index,id);
            GetResponse getResponse = client.get(request, RequestOptions.DEFAULT);
            String result = getResponse.getField(query).getValue();
            return result;
        } catch (Exception e) {
            logger.error("get index error",e);
            return "get index error";
        }
    }

    @RequestMapping("el/search")
    public String search(HttpServletRequest request,String index, String id,String keyWord){
        try {
            HighLevelRestClientObject highLevelRestClientObject = new HighLevelRestClientObject();
            highLevelRestClientObject.setKeyword(keyWord);
            PageResult search = elClientService.search(highLevelRestClientObject);
            return null;
        } catch (Exception e) {
            logger.error("get index error",e);
            return "null";
        }
    }

}
