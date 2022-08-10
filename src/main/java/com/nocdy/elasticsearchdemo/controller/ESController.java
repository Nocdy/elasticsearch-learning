package com.nocdy.elasticsearchdemo.controller;

import com.nocdy.elasticsearchdemo.entity.UserEntity;
import com.nocdy.elasticsearchdemo.service.DocumentService;
import com.nocdy.elasticsearchdemo.service.IndexService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author Nocdy
 * @description TODO
 * @date 2022/7/28 23:57
 */
@RestController
@RequiredArgsConstructor
public class ESController {

    private final DocumentService documentService;

    private final IndexService indexService;



    @RequestMapping("/createIndex/{name}")
    public String createIndex(@PathVariable("name") String name) throws IOException {
        indexService.createIndex(name);
        return "创建索引"+name;
    }

    @RequestMapping("/createData")
    public String createData(@RequestBody UserEntity user) throws IOException {
        documentService.addDocument("user","1",user);
        return "添加document";
    }

    @RequestMapping("/queryData/{name}/{id}")
    public UserEntity queryData(@PathVariable("name") String name,
                            @PathVariable("id") String id) throws IOException {
        return documentService.getDocument(name,id);
    }




}
