package com.fuswx.cloud.Api;

import com.fuswx.cloud.Entities.PayDTO;
import com.fuswx.cloud.Resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient(value = "cloud-payment-service")
@FeignClient(value = "cloud-gateway")
public interface PayFeignApi {

    //新增一条相关流水记录
    @PostMapping(value = "/pay/add")
    ResultData addPay(@RequestBody PayDTO payDTO);

    //按照主键记录查询支付流水信息
    @GetMapping(value = "/pay/get/{id}")
    ResultData getPayInfo(@PathVariable("id") Integer id);

    //openfeign天然支持负载均衡演示
    @GetMapping(value = "/pay/get/info")
    ResultData mylb();

    // Resilience4j CircuitBreaker的例子
    @GetMapping(value = "/pay/circuit/{id}")
    String myCircuit(@PathVariable("id") Integer id);

    // Resilience4j Bulkhead的例子
    @GetMapping(value = "/pay/bulkhead/{id}")
    String myBulkhead(@PathVariable("id") Integer id);

    // Resilience4j Ratelimit的例子
    @GetMapping(value = "/pay/ratelimit/{id}")
    String myRatelimit(@PathVariable("id") Integer id);

    // Micrometer(Sleuth)进行链路监控的例子
    @GetMapping(value = "/pay/micrometer/{id}")
    String myMicrometer(@PathVariable("id") Integer id);

    // Gateway进行网管测试案例01
    @GetMapping(value = "/pay/gateway/get/{id}")
    ResultData getById(@PathVariable("id") Integer id);

    // Gateway进行网关测试案例02
    @GetMapping(value = "/pay/gateway/info")
    ResultData<String> getGatewayInfo();

    @GetMapping(value = "/pay/gateway/filter")
    ResultData<String> getGatewayFilter();
}
