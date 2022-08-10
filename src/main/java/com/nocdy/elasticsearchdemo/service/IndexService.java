package com.nocdy.elasticsearchdemo.service;

import java.io.IOException;

/**
 * @author Nocdy
 * @description TODO
 * @date 2022/7/28 21:59
 */
public interface IndexService {

    boolean createIndex(String indexName) throws IOException;

    void queryIndex(String indexName) throws IOException;

    boolean existIndex(String indexName) throws IOException;

    boolean  deleteIndex(String indexName) throws IOException;



}
