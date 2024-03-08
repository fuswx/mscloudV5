package com.fuswx.cloud.Mapper;

import com.fuswx.cloud.Entities.Account;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface AccountMapper extends Mapper<Account> {
    /*
    扣减余额
     */
    void decrease(@Param("userId") Long userId, @Param("money") Long money);
}