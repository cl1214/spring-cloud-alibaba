package com.cl.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("cloud-payment-service")
public interface PaymentApi {

    @GetMapping("pay/pay")
    public void pay();
}
