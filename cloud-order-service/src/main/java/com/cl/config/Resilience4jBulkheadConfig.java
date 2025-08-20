package com.cl.config;

public class Resilience4jBulkheadConfig {

//    @Bean
//    public BulkheadRegistry bulkheadRegistry() {
//        //为Bulkhead创建自定义的配置
//        BulkheadConfig config = BulkheadConfig.custom()
//                .maxConcurrentCalls(150)
//                .maxWaitDuration(Duration.ofMillis(500))
//                .build();
//
//        // 使用自定义全局配置创建BulkheadRegistry
//        BulkheadRegistry registry = BulkheadRegistry.of(config);
//
//// 使用默认的配置从registry中创建Bulkhead
//        Bulkhead bulkheadWithDefaultConfig = registry.bulkhead("name1");
//
//// 使用自定义的配置从regidtry中创建bulkhead
//        Bulkhead bulkheadWithCustomConfig = registry.bulkhead("name2", custom);
//        // 注册名为"cloud-payment-service"的舱壁实例
//        return BulkheadRegistry.of(defaultConfig)
//                .bulkhead("cloud-payment-service", defaultConfig);
//    }
}
