package com.example.esapidemo.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class UpdateDoc {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

        UpdateRequest request = new UpdateRequest();
        request.index("user2").id("100001");

        request.doc(XContentType.JSON, "sex", "男性");

        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);

        System.out.println("_index = " + response.getIndex());
        System.out.println("_id = " + response.getId());
        System.out.println("_result = " + response.getResult());

        client.close();


    }
}
