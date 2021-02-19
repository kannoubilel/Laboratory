package com.example.demo.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import com.example.demo.beans.UserBean;
@FeignClient("membre-service")
public interface MemberProxy {
	@GetMapping("/membres")
    public List<UserBean> findAll();
    @GetMapping("/membres/{id}")
    public UserBean findById(@PathVariable Long id);
    @GetMapping("/membres/authentification/{email}")
	public UserBean findUserByEmail(@PathVariable String email);
}



