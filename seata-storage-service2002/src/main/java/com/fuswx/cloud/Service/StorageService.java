package com.fuswx.cloud.Service;

public interface StorageService {
    /*
    扣减库存
     */
    void decrease(Long productId, Integer count);
}
