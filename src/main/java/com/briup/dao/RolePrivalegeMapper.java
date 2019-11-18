package com.briup.dao;

import com.briup.bean.RolePrivalege;
import com.briup.bean.RolePrivalegeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RolePrivalegeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_role_privilege
     *
     * @mbg.generated Wed Nov 13 20:57:48 CST 2019
     */
    long countByExample(RolePrivalegeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_role_privilege
     *
     * @mbg.generated Wed Nov 13 20:57:48 CST 2019
     */
    int deleteByExample(RolePrivalegeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_role_privilege
     *
     * @mbg.generated Wed Nov 13 20:57:48 CST 2019
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_role_privilege
     *
     * @mbg.generated Wed Nov 13 20:57:48 CST 2019
     */
    int insert(RolePrivalege record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_role_privilege
     *
     * @mbg.generated Wed Nov 13 20:57:48 CST 2019
     */
    int insertSelective(RolePrivalege record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_role_privilege
     *
     * @mbg.generated Wed Nov 13 20:57:48 CST 2019
     */
    List<RolePrivalege> selectByExample(RolePrivalegeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_role_privilege
     *
     * @mbg.generated Wed Nov 13 20:57:48 CST 2019
     */
    RolePrivalege selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_role_privilege
     *
     * @mbg.generated Wed Nov 13 20:57:48 CST 2019
     */
    int updateByExampleSelective(@Param("record") RolePrivalege record, @Param("example") RolePrivalegeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_role_privilege
     *
     * @mbg.generated Wed Nov 13 20:57:48 CST 2019
     */
    int updateByExample(@Param("record") RolePrivalege record, @Param("example") RolePrivalegeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_role_privilege
     *
     * @mbg.generated Wed Nov 13 20:57:48 CST 2019
     */
    int updateByPrimaryKeySelective(RolePrivalege record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_role_privilege
     *
     * @mbg.generated Wed Nov 13 20:57:48 CST 2019
     */
    int updateByPrimaryKey(RolePrivalege record);
}