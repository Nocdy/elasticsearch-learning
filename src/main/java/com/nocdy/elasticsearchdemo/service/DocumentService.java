package com.nocdy.elasticsearchdemo.service;

import com.nocdy.elasticsearchdemo.entity.UserEntity;

import java.io.IOException;
import java.util.List;

/**
 * @author Nocdy
 * @description TODO
 * @date 2022/7/28 22:15
 */
public interface DocumentService {

    void addDocument(String indexName,String id,UserEntity user) throws IOException;

    void updateDocument(String indexName,String id,UserEntity user) throws IOException;

    boolean existDocument(String indexName,String id) throws IOException;

    UserEntity getDocument(String indexName,String id) throws IOException;

    String deleteDocument(String indexName,String id);

    void insertBatchDocument(List<UserEntity> userEntities);

}
