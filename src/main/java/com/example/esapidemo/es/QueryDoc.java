package com.example.esapidemo.es;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

/**
 * @author ：zhangsw
 * @date ：2022/8/30
 */
public class QueryDoc {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

        SearchRequest request = new SearchRequest();
        request.indices("user");


        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.termQuery("age", 20));
        request.source(sourceBuilder);


        final SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        final SearchHits hits = response.getHits();

        System.out.println("took: " + response.getTook());
        System.out.println("timeout: " + response.isTimedOut());
        System.out.println("total hits: " + response.getHits().getTotalHits());
        System.out.println("maxScore :" + hits.getMaxScore() );
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        for (SearchHit hit : hits) {
            System.out.println(JSON.toJSONString(hit));
            System.out.println(hit.getSourceAsString());
        }
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");


        client.close();

    }
}
