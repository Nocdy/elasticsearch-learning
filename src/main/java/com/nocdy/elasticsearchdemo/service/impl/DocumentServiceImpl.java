package com.nocdy.elasticsearchdemo.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.UpdateRequest;
import co.elastic.clients.elasticsearch.core.UpdateResponse;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import com.nocdy.elasticsearchdemo.entity.UserEntity;
import com.nocdy.elasticsearchdemo.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.springframework.data.elasticsearch.client.elc.Aggregation;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.QueryBuilders;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author Nocdy
 * @description TODO
 * @date 2022/7/28 22:26
 */
@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final ElasticsearchClient client;

    private final ElasticsearchTemplate elasticsearchTemplate;


    @Override
    public void addDocument(String indexName, String id, UserEntity user) throws IOException {
        IndexResponse indexResponse = client.index(
                objectBuilder -> objectBuilder.index(indexName)
                        .id(id)
                        .document(user));
    }

    @Override
    public void updateDocument(String indexName, String id, UserEntity user) throws IOException {
        UpdateResponse<UserEntity> userEntityUpdateResponse;
        userEntityUpdateResponse = client.update(userEntityBuilder -> userEntityBuilder
                        .index(indexName).id(id)
                        .doc(user)
                , UserEntity.class);
    }

    @Override
    public boolean existDocument(String indexName, String id) throws IOException {
        BooleanResponse booleanResponse = client.exists(builder -> builder.index(indexName).id(id));
        return booleanResponse.value();
    }

    @Override
    public UserEntity getDocument(String indexName, String id) throws IOException {
        GetResponse<UserEntity> getResponse = client.get(builder ->
                        builder.index(indexName)
                                .id(id)
                , UserEntity.class);
        client.search(builder ->
                builder.index("ingdex")
                        .aggregations("avg", builder1 ->
                                builder1.avg(builder2 ->
                                                builder2.field("age"))), UserEntity.class);
        AvgAggregationBuilder avgAggregationBuilder= AggregationBuilders.avg("avg").field("age");
        NativeSearchQuery nativeSearchQuery=new NativeSearchQueryBuilder().withAggregations(avgAggregationBuilder).build();
        elasticsearchTemplate.search(nativeSearchQuery,UserEntity.class);

        return getResponse.source();
    }

    @Override
    public String deleteDocument(String indexName, String id) {
        return null;
    }

    @Override
    public void insertBatchDocument(List<UserEntity> userEntities) {

    }
}
