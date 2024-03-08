package com.fuswx.cloud.Controller;

import com.fuswx.cloud.Entities.Order;
import com.fuswx.cloud.Resp.ResultData;
import com.fuswx.cloud.Service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    /*
    创建订单
     */
    @GetMapping("/order/create")
    public ResultData create(Order order){
        orderService.create(order);
        return ResultData.success(order);
    }
}
