package com.mace.cloud.controller;

import com.mace.cloud.api.annotation.SystemControllerLog;
import com.mace.cloud.api.common.RestPackResponse;
import com.mace.cloud.api.entity.Dept;
import com.mace.cloud.service.IDeptService;
import com.terran4j.commons.api2doc.annotations.Api2Doc;
import com.terran4j.commons.api2doc.annotations.ApiComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * description:
 * <br />
 * Created by mace on 18:35 2018/6/30.
 */
@RestController
@RequestMapping("/dept")
@Api2Doc(id = "dept", name = "部门接口")
@ApiComment(seeClass = Dept.class)
@SystemControllerLog(description = "部门接口")
public class DeptController {

    @Autowired
    private IDeptService iDeptService;

    @SystemControllerLog(description = "查询所有部门信息")
    @ApiComment(value = "查询所有部门信息")
    @RequestMapping(value = "/findAll.do", name = "查询所有部门信息", method = RequestMethod.GET)
    public RestPackResponse<List<Dept>> findAll(){

        List<Dept> deptList = iDeptService.findAll();

        return RestPackResponse.createBySuccess("查询部门信息成功", deptList);
    }

}
