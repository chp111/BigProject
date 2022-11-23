package com.cp.company.bootdo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cp.company.bootdo.mapper.SysUserMapper;
import com.cp.company.bootdo.pojo.SysUserPoJo;
import com.cp.company.bootdo.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author 陈朋
 * @description
 * @datetime 2022/11/23 17:04
 */
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserPoJo> implements SysUserService {
}
