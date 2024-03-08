package com.fuswx.cloud.Mapper;

import com.fuswx.cloud.Entities.Storage;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface StorageMapper extends Mapper<Storage> {
    /*
    扣减库存
     */
    void decrease(@Param("productId") Long productId, @Param("count") Integer count);

}