package com.fuswx.cloud.Controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.fuswx.cloud.Entities.Pay;
import com.fuswx.cloud.Resp.ResultData;
import com.fuswx.cloud.Service.PayService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;

@RestController
public class PayGateWayController {

    @Resource
    private PayService payService;

    @GetMapping(value = "/pay/gateway/get/{id}")
    public ResultData<Pay> getById(@PathVariable("id") Integer id){
        Pay pay = payService.getById(id);
        return ResultData.success(pay);
    }

    @GetMapping(value = "/pay/gateway/info")
    public ResultData<String> getGatewayInfo(){
        return ResultData.success("gateway info test:" + IdUtil.simpleUUID());
    }

    @GetMapping(value = "/pay/gateway/filter")
    public ResultData<String> getGatewayFilter(HttpServletRequest request){
        StringBuilder result = new StringBuilder();
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()){
            String headerName = headers.nextElement();
            String headerValue = request.getHeader(headerName);
            System.out.println("请求头名：" + headerName + "\t\t\t" + "请求头值：" + headerValue);
            if (headerName.equalsIgnoreCase("X-Request-fuswx1") || headerName.equalsIgnoreCase("X-Request-fuswx2")){
                result.append(headerName).append("\t").append(headerValue).append(" ");
            }
        }
        System.out.println("====================================================");
        String customerId = request.getParameter("customerId");
        System.out.println("request Parameter customerId:" + customerId);
        String customerName = request.getParameter("customerName");
        System.out.println("request Parameter customerName:" + customerName);
        System.out.println("=====================================================");
        return ResultData.success("getGateway 过滤器 test:" + result + "\t" + DateUtil.now());
    }
}
