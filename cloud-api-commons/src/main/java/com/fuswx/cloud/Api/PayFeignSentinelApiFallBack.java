package com.fuswx.cloud.Api;

import com.fuswx.cloud.Resp.ResultData;
import com.fuswx.cloud.Resp.ReturnCodeEnum;
import org.springframework.stereotype.Component;

@Component
public class PayFeignSentinelApiFallBack implements PayFeignSentinelApi{
    @Override
    public ResultData getPayByOrderNo(String orderNo) {
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), "对方服务宕机或不可用，FallBack服务降级 /(ㄒoㄒ)/~~");
    }
}
