package com.example.esapidemo.es;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * @author ：zhangsw
 * @date ：2022/8/30
 */
public class BatchDeleteDoc {

    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

        BulkRequest request = new BulkRequest();
        request.add(new DeleteRequest().index("user").id("20001"));
        request.add(new DeleteRequest().index("user").id("20002"));
        request.add(new DeleteRequest().index("user").id("20003"));


        final BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);

        System.out.println("took: " + response.getTook()) ;
        System.out.println("items: " + JSON.toJSONString(response.getItems()));


        client.close();
    }
}
