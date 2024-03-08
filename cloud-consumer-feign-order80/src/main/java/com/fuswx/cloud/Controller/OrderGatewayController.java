package com.fuswx.cloud.Controller;

import com.fuswx.cloud.Api.PayFeignApi;
import com.fuswx.cloud.Resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderGatewayController {

    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping(value = "/feign/pay/gateway/get/{id}")
    public ResultData getById(@PathVariable("id") Integer id){
        return payFeignApi.getById(id);
    }

    @GetMapping(value = "/feign/pay/gateway/info")
    public ResultData<String> getGatewayInfo(){
        return payFeignApi.getGatewayInfo();
    }
}
