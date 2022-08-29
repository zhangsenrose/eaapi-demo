package com.example.esapidemo.es;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class InsertDoc {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

        IndexRequest request = new IndexRequest();
        request.index("user2");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "zhaoyue");
        jsonObject.put("age", 25);
        jsonObject.put("sex", "woman");

        request.source(jsonObject.toJSONString(), XContentType.JSON);

        IndexResponse response = client.index(request, RequestOptions.DEFAULT);


        System.out.println("_index = " + response.getIndex());
        System.out.println("_id = " + response.getId());
        System.out.println("_result = " + response.getResult());

        client.close();

    }
}
