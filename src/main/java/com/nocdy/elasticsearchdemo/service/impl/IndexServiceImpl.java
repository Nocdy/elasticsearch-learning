package com.nocdy.elasticsearchdemo.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import com.nocdy.elasticsearchdemo.Dao.UserDao;
import com.nocdy.elasticsearchdemo.service.IndexService;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author Nocdy
 * @description TODO
 * @date 2022/7/28 22:03
 */
@Service
@RequiredArgsConstructor
public class IndexServiceImpl implements IndexService {

    private final ElasticsearchClient client;

    private final UserDao userDao;


    @Override
    public boolean createIndex(String indexName) throws IOException {
        CreateIndexResponse indexResponse=client.indices().create(c->c.index(indexName));
        return indexResponse.acknowledged();
    }

    @Override
    public void queryIndex(String indexName) throws IOException {
        GetIndexResponse getIndexResponse=client.indices().get(c->c.index(indexName));
    }

    @Override
    public boolean existIndex(String indexName) throws IOException {
        return client.indices().exists(c->c.index(indexName)).value();
    }

    @Override
    public boolean deleteIndex(String indexName) throws IOException {
        return client.indices().delete(c->c.index(indexName)).acknowledged();
    }
}
