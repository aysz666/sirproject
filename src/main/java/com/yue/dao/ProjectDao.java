package com.yue.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yue.domain.Project;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProjectDao extends BaseMapper<Project> {
}
