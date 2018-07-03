package com.mace.cloud.service.impl;

import com.mace.cloud.api.entity.Dept;
import com.mace.cloud.dao.DeptDao;
import com.mace.cloud.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description:
 * <br />
 * Created by mace on 18:33 2018/6/30.
 */
@Service("iDeptService")
public class DeptServiceImpl implements IDeptService {

    @Autowired
    private DeptDao deptDao;

    @Override
    public boolean insertOne(Dept dept) {

        return deptDao.insertOne(dept);
    }

    @Override
    public List<Dept> findAll() {

        return deptDao.findAll();
    }
}
