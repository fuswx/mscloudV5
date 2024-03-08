package com.fuswx.cloud.Service.impl;

import com.fuswx.cloud.Mapper.StorageMapper;
import com.fuswx.cloud.Service.StorageService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageMapper storageMapper;

    /*
    扣减库存
     */
    @Override
    public void decrease(Long productId, Integer count) {
        log.info("---------------->storage-service中扣减库存开始");
        storageMapper.decrease(productId, count);
        log.info("---------------->storage-service中扣减库存结束");
    }
}
