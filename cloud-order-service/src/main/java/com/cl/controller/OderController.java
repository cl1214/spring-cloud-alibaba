package com.cl.controller;

import com.cl.feign.PaymentApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
@Lazy(false)
public class OderController {


    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private PaymentApi paymentApi;

    @GetMapping("order")
    public void order() {
        paymentApi.pay();
    }

}
