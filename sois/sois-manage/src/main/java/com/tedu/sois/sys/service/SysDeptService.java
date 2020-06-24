package com.tedu.sois.sys.service;

import com.tedu.sois.common.vo.Node;
import com.tedu.sois.common.pojo.sys.SysDept;

import java.util.List;
import java.util.Map;

/**
 * 部门功能接口
 *
 * @author LYS
 */
public interface SysDeptService {

	void saveSysDeptInfo(SysDept entity);

	void removeSysDeptInfo(Integer deptId);

	void modifySysDeptInfo(SysDept entity);



    /**
     * 查询所有部门以及部门的上级菜单信息
     *
     * @return
     */
    List<Map<String, Object>> findDeptInfoList();

	/**
	 * 查找树节点
	 * @return
	 */
	List<Node> findZTreeDeptNodes();

	/**
	 * 根据ID查询部门信息
	 * @param deptId
	 * @return
	 */
	SysDept findSysDeptInfoByDeptId(Integer deptId);
}
