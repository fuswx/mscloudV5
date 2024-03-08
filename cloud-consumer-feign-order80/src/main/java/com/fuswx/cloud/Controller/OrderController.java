package com.fuswx.cloud.Controller;

import cn.hutool.core.date.DateUtil;
import com.fuswx.cloud.Api.PayFeignApi;
import com.fuswx.cloud.Entities.PayDTO;
import com.fuswx.cloud.Resp.ResultData;
import com.fuswx.cloud.Resp.ReturnCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @Resource
    private PayFeignApi payFeignApi;

    @PostMapping(value = "/feign/pay/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO){
        System.out.println("第一步：模拟本地addOrder新增订单成功（省略sql操作）；第二步：再开启addPay支付微服务远程调用");
        return payFeignApi.addPay(payDTO);
    }

    @GetMapping(value = "/feign/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id){
        System.out.println("=================支付微服务远程调用，按照id查询订单支付流水信息");
        ResultData resultData = null;
        try {
            System.out.println("调用开始---------------" + DateUtil.now());
            resultData = payFeignApi.getPayInfo(id);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("调用结束---------------" + DateUtil.now());
            ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
        }
        return resultData;
    }

    @GetMapping(value = "/feign/pay/mylb")
    public ResultData mylb(){
        return payFeignApi.mylb();
    }
}
