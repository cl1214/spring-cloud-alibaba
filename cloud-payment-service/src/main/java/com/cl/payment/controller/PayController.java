package com.cl.payment.controller;

import com.cl.payment.entity.Pay;
import com.cl.payment.service.PayService;
import com.cl.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("pay")
public class PayController {

    @Autowired
    private PayService payService;

    @Value("${server.port}")
    private String port;

//    @Value("${consule.testvalue}")
    private String confValue;

    @GetMapping("getPay")
    public Pay getPay(@RequestParam("id") Long id) {
        return payService.getPay(id);
    }

    @GetMapping("getConfig")
    public String getConfig() {
        return confValue;
    }

    @GetMapping("loadBanlance")
    public String loadBanlance() {
        return "cloud-payment-service:" + port;
    }

    @GetMapping("pay")
    public void pay() {
        System.out.println("当前用户id：" + UserContext.getUserId());
        System.out.println("付款成功");
    }

    @GetMapping("timeOut")
    public void timeOut() {
        try {
            System.out.println(111);

            Thread.sleep(79000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("circuitBreaker")
    public String circuitBreaker(@RequestParam("id") Long id) {
        if (id == 2) {
           throw new ArithmeticException();
        }
        if (id == 3) {
            throw new NullPointerException();
        }
        return "正常返回";
    }

    @GetMapping("bulkhead")
    public String bulkhead(@RequestParam("id") Long id) {
        if (id == 2 || id == 3) {
            try {
                Thread.sleep(50000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return "正常返回";
    }


    @GetMapping("ratelimit")
    public String ratelimit(@RequestParam("id") Long id) {
        return "正常返回";
    }
}
