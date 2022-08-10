package com.nocdy.elasticsearchdemo.Dao;

import com.nocdy.elasticsearchdemo.entity.UserEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Nocdy
 * @description TODO
 * @date 2022/8/10 23:46
 */
@Repository
public interface UserDao extends ElasticsearchRepository<UserEntity,Integer> {
}
