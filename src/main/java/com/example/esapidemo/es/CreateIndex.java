package com.example.esapidemo.es;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;

import java.io.IOException;

/**
 * @author ：zhangsw
 * @date ：2022/8/29
 */
public class CreateIndex {

    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
        CreateIndexRequest request = new CreateIndexRequest("user2");
        final CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);

        final boolean acknowledged = response.isAcknowledged();
        System.out.println("操作状态 = " + acknowledged);

        client.close();
    }
}
