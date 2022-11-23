package com.cp.company.bootdo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cp.company.bootdo.pojo.SysUserPoJo;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface SysUserMapper extends BaseMapper<SysUserPoJo> {

    /**
     * 根据主键ID查询一条Map集合
     * @param id 主键ID
     * @return
     */
    Map<String, Object> selectMapById(Long id);
}
