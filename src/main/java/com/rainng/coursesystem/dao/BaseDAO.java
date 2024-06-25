package com.rainng.coursesystem.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
//定义了一个通用的分页查询方法selectPage，用于与MyBatis Plus的BaseMapper接口结合使用，实现分页查询功能
public class BaseDAO {
    <T> List<T> selectPage(BaseMapper<T> mapper, LambdaQueryWrapper<T> wrapper, int index, int size) {
        Page<T> page = new Page<>(index, size);
        return mapper.selectPage(page, wrapper).getRecords();
    }
}
