package com.fuswx.cloud.Service.impl;

import com.fuswx.cloud.Api.AccountFeignApi;
import com.fuswx.cloud.Api.StorageFeignApi;
import com.fuswx.cloud.Entities.Order;
import com.fuswx.cloud.Mapper.OrderMapper;
import com.fuswx.cloud.Service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource  //订单微服务通过OpenFeign去调用库存微服务
    private StorageFeignApi storageFeignApi;

    @Resource  //订单微服务通过OpenFeign去调用账户微服务
    private AccountFeignApi accountFeignApi;

    @Override
    @GlobalTransactional(name = "yzc-create-order", rollbackFor = Exception.class)  //AT
    public void create(Order order) {
        //XID全局事务id的检查，重要
        String xid = RootContext.getXID();
        //1.新建订单
        log.info("-----------------开始新建订单：\t xid:" + xid);
        //订单新建时，默认初始订单状态是零
        order.setStatus(0);
        int result = orderMapper.insertSelective(order);
        //插入订单成功后获得插入mysql的实体对象
        Order orderFromDB = null;
        if (result > 0){
            //从mysql中查出来刚插入的记录
            orderFromDB = orderMapper.selectOne(order);
            log.info("--------------> 新建订单成功，orderFromDB info：" + orderFromDB);
            System.out.println();
            //2.扣减库存
            log.info("-------------->订单微服务开始调用Storage库存，做扣减count");
            storageFeignApi.decrease(orderFromDB.getProductId(), orderFromDB.getCount());
            log.info("-------------->订单微服务结束调用Storage库存，做扣减完成");
            System.out.println();

            //3.扣减账户余额
            log.info("-------------->订单微服务开始调用Account账户，做扣减money");
            accountFeignApi.decrease(orderFromDB.getUserId(), orderFromDB.getMoney());
            log.info("-------------->订单微服务开始调用Account账户，做扣减完成");
            System.out.println();

            //4.修改订单状态
            //将订单状态从0修改为1，表示已完成
            log.info("-------------->修改订单状态");
            orderFromDB.setStatus(1);
            log.info("-------------->修改订单状态完成");

            Example whereCondition = new Example(Order.class);
            Example.Criteria criteria = whereCondition.createCriteria();
            criteria.andEqualTo("userId", orderFromDB.getUserId());
            criteria.andEqualTo("status", 0);

            int updateResult = orderMapper.updateByExampleSelective(orderFromDB, whereCondition);

            log.info("----------->修改订单状态完成\t" + updateResult);
            log.info("----------->orderFromDB info:\t" + orderFromDB);
        }
        System.out.println();
        log.info("-----------------结束新建订单：\t xid:" + xid);
    }
}
