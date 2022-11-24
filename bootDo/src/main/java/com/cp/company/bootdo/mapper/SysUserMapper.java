package com.cp.company.bootdo.mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cp.company.bootdo.pojo.SysUser;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 根据主键ID查询一条Map集合
     * @param id 主键ID
     * @return
     */
    Map<String, Object> selectMapById(Long id);
}
