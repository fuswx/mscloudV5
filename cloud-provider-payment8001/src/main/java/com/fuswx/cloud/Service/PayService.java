package com.fuswx.cloud.Service;

import com.fuswx.cloud.Entities.Pay;

import java.util.List;

public interface PayService {
    public int add(Pay pay);
    public int delete(Integer id);
    public int update(Pay pay);

    public Pay getById(Integer id);

    public List<Pay> getAll();
}
