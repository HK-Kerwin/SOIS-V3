package com.tedu.sois.sys.dao;

import com.tedu.sois.common.vo.Node;
import com.tedu.sois.common.pojo.sys.SysDept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 部门信息持久层接口
 * @author LYS
 */
public interface SysDeptDao {

    int insertDeptInfo(SysDept dept);

    @Delete("delete from sys_dept where dept_id=#{deptId}")
    int deleteDeptInfoById(Integer deptId);

    int updateDeptInfo(SysDept dept);

    /**
     * 查询所有部门以及部门的上级菜单信息
     *
     * @return
     */
    List<Map<String, Object>> selectDeptInfoList();

    @Select("select dept_id id, dept_name name , parent_id parentId from sys_dept")
    List<Node> findZTreeNodes();


    List<Node> findZTreeNodesIsParentId(Integer deptId);


	@Select("select count(*) from sys_dept where parent_id=#{deptId}")
	int getChildCount(Integer deptId);

    /**
     * 根据部门ID编号查询部门信息
     * @param deptId
     * @return
     */
    @Select("select * from sys_dept where dept_id=#{deptId}")
    SysDept selectDeptInfoById(Integer deptId);

    /**
     * 根据部门名称统计是否存在
     * @param deptName
     * @return
     */
    @Select("select count(*) from sys_dept where dept_name=#{deptName}")
    int selectDeptInfoByName(String deptName);

}







