package com.example.demo.proxies;

import com.example.demo.OutilBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@FeignClient("outil-service")
public interface OutilProxy {
    @GetMapping("/outils")
    public CollectionModel<OutilBean> findAll();
    @GetMapping("/outils/{id}")
    public OutilBean findById(@PathVariable Long id);
}
