package com.nocdy.elasticsearchdemo.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nocdy
 * @description TODO
 * @date 2022/7/28 21:33
 */

@Configuration
public class ElasticsearchConfig  {

    @Bean
    public ElasticsearchClient elasticsearchClient(){
        RestClient client=RestClient.builder(new HttpHost("localhost",9200,"http")).build();
        ElasticsearchTransport transport=new RestClientTransport(client,new JacksonJsonpMapper());
        return new ElasticsearchClient(transport);
    }

}
