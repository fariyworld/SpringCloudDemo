package com.mace.cloud.service;

import com.mace.cloud.api.entity.Dept;

import java.util.List;

/**
 * description:
 * <br />
 * Created by mace on 18:32 2018/6/30.
 */
public interface IDeptService {

    boolean insertOne(Dept dept);

    List<Dept> findAll();
}
