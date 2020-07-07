package com.tedu.sois.sys.controller;

import com.tedu.sois.common.vo.JsonResult;
import com.tedu.sois.common.vo.Node;
import com.tedu.sois.common.pojo.sys.SysDept;
import com.tedu.sois.sys.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 部门相关操作请求控制层
 */
@RestController
@RequestMapping("dept/")
public class SysDeptController {


    @Autowired
    private SysDeptService sysDeptService;

	@PostMapping("doSaveSysDeptInfo")
	public JsonResult doSaveSysDeptInfo(SysDept entity) {
		sysDeptService.saveSysDeptInfo(entity);
		return new JsonResult("保存部门信息成功");
	}

	@RequestMapping("doRemoveSysDeptInfo")
	public JsonResult doRemoveSysDeptInfo(Integer deptId) {
		sysDeptService.removeSysDeptInfo(deptId);
		return new JsonResult("删除部门信息成功");
	}

	@PostMapping("doModifySysDeptInfo")
	public JsonResult doModifySysDeptInfo(SysDept entity) {
		sysDeptService.modifySysDeptInfo(entity);
		return new JsonResult("修改部门信息成功");
	}

	/**
	 * 查询所有部门以及部门的上级菜单信息
	 * @return
	 */
	@RequestMapping("doFindDeptInfoList")
    public JsonResult doFindDeptInfoList() {
        return new JsonResult(sysDeptService.findDeptInfoList());
    }

	/**
	 * 查询树节点
	 * @return
	 */
	@RequestMapping("doFindZTreeDeptNodes")
    public List<Node> doFindZTreeDeptNodes() {
        return sysDeptService.findZTreeDeptNodes();
    }


}
