package com.fuswx.cloud.Controller;

import com.fuswx.cloud.Entities.Pay;
import com.fuswx.cloud.Entities.PayDTO;
import com.fuswx.cloud.Resp.ResultData;
import com.fuswx.cloud.Resp.ReturnCodeEnum;
import com.fuswx.cloud.Service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@Tag(name = "支付微服务模块", description = "支付CRUD")
public class PayController {

    @Resource
    private PayService payService;

    @PostMapping(value = "/pay/add")
    @Operation(summary = "新增", description = "新增支付流水方法，json串做参数")
    public ResultData<String> addPay(@RequestBody Pay pay){
        System.out.println(pay.toString());
        int i = payService.add(pay);
        return ResultData.success("成功插入记录，返回值："+ i);
    }

    @DeleteMapping(value = "/pay/del/{id}")
    @Operation(summary = "删除", description = "删除支付流水方法")
    public ResultData<Integer> deletePay(@PathVariable("id") Integer id){
        int i = payService.delete(id);
        return ResultData.success(i);
    }

    @PutMapping(value = "/pay/update")
    @Operation(summary = "修改", description = "修改支付流水方法")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO){
        Pay pay= new Pay();
        BeanUtils.copyProperties(payDTO, pay);

        int i = payService.update(pay);
        return ResultData.success("成功修改记录，返回值：" + i);
    }

    @GetMapping(value = "/pay/get/{id}")
    @Operation(summary = "按照ID查流水", description = "查询支付流水方法")
    public ResultData<Pay> getById(@PathVariable("id") Integer id){
        if (id == -4) throw new RuntimeException("id不能为负数");
        //暂停62秒钟线程，故意写bug，测试出feign的默认调用超时时间
        try {
            TimeUnit.SECONDS.sleep(62);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return ResultData.success(payService.getById(id));
    }

    @GetMapping(value = "/pay/getAll")
    public ResultData<List<Pay>> getAll(){
        return ResultData.success(payService.getAll());
    }

    @GetMapping(value = "/pay/error")
    public ResultData<Integer> getPayError(){
        Integer integer = Integer.valueOf(200);
        try {
            System.out.println("come in pay_error test");
            int age = 10 / 0;
        }catch (Exception e){
            e.printStackTrace();
            return ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
        }
        return ResultData.success(integer);
    }

    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/pay/get/info")
    public ResultData getInfoByConsul(@Value("${fuswx.info}") String info){
        return ResultData.success("fuswxInfo:" + info + "\t" + "port:" + port);
    }
}
