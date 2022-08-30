package com.example.esapidemo.es;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class BatchInsertDocument {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

        BulkRequest request = new BulkRequest();

//        request.add(new IndexRequest("user").id("10001").source(XContentType.JSON, "name", "zhangsan", "age", 20, "gender", "man"));
//        request.add(new IndexRequest("user").id("10002").source(XContentType.JSON, "name", "lisi", "age", 20, "gender", "woman"));
//        request.add(new IndexRequest("user").id("10003").source(XContentType.JSON, "name", "wangwu", "age", 30, "gender", "man"));
//        request.add(new IndexRequest("user").id("10004").source(XContentType.JSON, "name", "zhaoliu", "age", 25, "gender", "man"));
//        request.add(new IndexRequest("user").id("10005").source(XContentType.JSON, "name", "zhenli", "age", 40, "gender", "woman"));
        request.add(new IndexRequest("user").id("10007").source(XContentType.JSON, "name", "wangwu1", "age", 32, "gender", "man"));
        request.add(new IndexRequest("user").id("10008").source(XContentType.JSON, "name", "wangwu2", "age", 33, "gender", "man"));
        request.add(new IndexRequest("user").id("10009").source(XContentType.JSON, "name", "wangwu40", "age", 40, "gender", "woman"));


        BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);

        System.out.println(JSON.toJSONString(response));

        client.close();
    }
}
