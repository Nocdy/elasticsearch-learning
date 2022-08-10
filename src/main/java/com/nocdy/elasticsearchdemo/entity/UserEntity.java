package com.nocdy.elasticsearchdemo.entity;

import co.elastic.clients.elasticsearch.indices.DeleteIndexResponse;
import lombok.Data;

/**
 * @author Nocdy
 * @description TODO
 * @date 2022/7/28 21:58
 */
@Data
public class UserEntity {

    private String name;

    private Integer age;


}
