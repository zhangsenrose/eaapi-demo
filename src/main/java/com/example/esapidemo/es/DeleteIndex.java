package com.example.esapidemo.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * @author ：zhangsw
 * @date ：2022/8/29
 */
public class DeleteIndex {

    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

        DeleteIndexRequest request = new DeleteIndexRequest("user2");

        final AcknowledgedResponse acknowledgedResponse = client.indices().delete(request, RequestOptions.DEFAULT);

        System.out.println("操作结果： = " + acknowledgedResponse);

        client.close();
    }
}
