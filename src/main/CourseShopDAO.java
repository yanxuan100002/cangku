package com.rainng.coursesystem.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rainng.coursesystem.dao.mapper.CourseMapper;
import com.rainng.coursesystem.dao.mapper.CourseShopMapper;
import com.rainng.coursesystem.model.bo.CourseShopItemBO;
import com.rainng.coursesystem.model.bo.StudentCourseSelectItemBO;
import com.rainng.coursesystem.model.entity.CourseEntity;
import com.rainng.coursesystem.model.entity.CourseShopEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseShopDAO extends BaseDAO {
    public static final int PAGE_SIZE = 20;

    private final CourseShopMapper mapper;

    public CourseShopDAO(CourseShopMapper mapper) {
        this.mapper = mapper;
    }

    public int insert(CourseShopEntity entity) {
        return mapper.insert(entity);
    }

    public int delete(Integer id) {
        return mapper.deleteById(id);
    }

    public CourseShopEntity get(Integer id) {
        return mapper.selectById(id);
    }

    public int update(CourseShopEntity entity) {
        return mapper.updateById(entity);
    }

    public int count(String departmentName, String teacherName, String name) {
        return mapper.count(departmentName, teacherName, name);
    }

    public List<CourseShopItemBO> getPage(Integer index, String departmentName, String teacherName, String name) {
        Page<CourseShopItemBO> page = new Page<>(index, PAGE_SIZE);

        return mapper.getPage(page, departmentName, teacherName, name).getRecords();
    }

    public Integer countByTeacherId(Integer teacherId) {
        LambdaQueryWrapper<CourseShopEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CourseShopEntity::getTeacherId, teacherId);

        return mapper.selectCount(wrapper);
    }

    public List<CourseShopEntity> listName() {
        LambdaQueryWrapper<CourseShopEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(CourseShopEntity::getId, CourseShopEntity::getName);

        return mapper.selectList(wrapper);
    }

    public int increaseSelectedCount(Integer courseId) {
        CourseShopEntity courseshop = mapper.selectById(courseId);
        courseshop.setSelectedCount(courseshop.getSelectedCount() + 1);

        return mapper.updateById(courseshop);
    }

    public int decreaseSelectedCount(Integer courseId) {
        CourseShopEntity  courseshop = mapper.selectById(courseId);
        courseshop.setSelectedCount(courseshop.getSelectedCount() - 1);

        return mapper.updateById(courseshop);
    }

    public Integer countStudentCanSelect(Integer studentId, Integer departmentId, Integer grade, String courseName, String teacherName) {
        return mapper.countStudentCanSelect(studentId, departmentId, grade, courseName, teacherName);
    }

    public List<StudentCourseSelectItemBO> getStudentCanSelectPage(Integer index, Integer studentId, Integer departmentId, Integer grade, String courseName, String teacherName) {
        Page<StudentCourseSelectItemBO> page = new Page<>(index, PAGE_SIZE);

        return mapper.getStudentCanSelectPage(page, studentId, departmentId, grade, courseName, teacherName).getRecords();
    }

    public Integer getDepartmentIdById(Integer courseId) {
        return mapper.getDepartmentIdById(courseId);
    }
}

