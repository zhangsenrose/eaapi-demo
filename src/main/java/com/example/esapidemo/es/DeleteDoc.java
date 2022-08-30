package com.example.esapidemo.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * @author ：zhangsw
 * @date ：2022/8/30
 */
public class DeleteDoc {

    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

        DeleteRequest request = new DeleteRequest();
        request.index("user2").id("CE5-7IIB9nC-e5OIE88N");
        final DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);

        System.out.println(response.toString());

        client.close();
    }
}
