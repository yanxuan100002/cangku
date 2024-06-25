package com.rainng.coursesystem.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rainng.coursesystem.model.entity.AdminEntity;
import org.springframework.stereotype.Repository;
//AdminMapper接口是一个MyBatis Plus的Mapper接口
// 扩展了BaseMapper接口，并指定了它所操作的实体类为AdminEntity,这个Mapper接口用于与数据库中的admin表
@Repository
public interface AdminMapper extends BaseMapper<AdminEntity> {
}
