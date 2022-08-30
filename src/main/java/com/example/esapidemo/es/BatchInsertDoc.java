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

/**
 * @author ：zhangsw
 * @date ：2022/8/30
 */
public class BatchInsertDoc {

    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

        BulkRequest request = new BulkRequest();

        request.add(new IndexRequest().index("user").id("10001").source(XContentType.JSON, "name", "zhangsan", "age", 20, "sex", "man"));
        request.add(new IndexRequest().index("user").id("10002").source(XContentType.JSON, "name", "lisi", "age", 23, "sex", "woman"));
        request.add(new IndexRequest().index("user").id("10003").source(XContentType.JSON, "name", "wangwu", "age", 20, "sex", "man"));
        request.add(new IndexRequest().index("user").id("10004").source(XContentType.JSON, "name", "fuxi", "age", 63, "sex", "man"));
        request.add(new IndexRequest().index("user").id("10005").source(XContentType.JSON, "name", "gyuqing", "age", 60, "sex", "woman"));
        request.add(new IndexRequest().index("user").id("10006").source(XContentType.JSON, "name", "gaobo", "age", 29, "sex", "man"));

        final BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);

        System.out.println("took: " + response.getTook()) ;
        System.out.println("items: " + JSON.toJSONString(response.getItems()));

        client.close();

    }
}
