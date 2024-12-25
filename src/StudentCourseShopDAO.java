package com.rainng.coursesystem.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rainng.coursesystem.dao.mapper.StudentCourseShopMapper;
import com.rainng.coursesystem.model.entity.StudentCourseShopEntity;
import com.rainng.coursesystem.model.vo.response.table.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentCourseShopDAO extends BaseDAO {
    public static final int PAGE_SIZE = 20;
    private final StudentCourseShopMapper mapper;

    public StudentCourseShopDAO(StudentCourseShopMapper mapper) {
        this.mapper = mapper;
    }


    public int insert(StudentCourseShopEntity entity) {
        return mapper.insert(entity);
    }

    public int delete(Integer id) {
        return mapper.deleteById(id);
    }

    public StudentCourseShopEntity get(Integer id) {
        return mapper.selectById(id);
    }

    public int update(StudentCourseShopEntity entity) {
        return mapper.updateById(entity);
    }

    public int count(String className, String courseName, String studentName) {
        return mapper.count(className, courseName, studentName);
    }

    public List<StudentCourseItemVO> getPage(Integer index, String className, String courseName, String studentName) {
        Page<StudentCourseItemVO> page = new Page<>(index, PAGE_SIZE);

        return mapper.getPage(page, className, courseName, studentName).getRecords();
    }

    public int countByCourseId(Integer courseId) {
        LambdaQueryWrapper<StudentCourseShopEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StudentCourseShopEntity::getCourseId, courseId);

        return mapper.selectCount(wrapper);
    }

    public int countByStudentId(Integer studentId) {
        LambdaQueryWrapper<StudentCourseShopEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StudentCourseShopEntity::getStudentId, studentId);

        return mapper.selectCount(wrapper);
    }

    public StudentCourseShopEntity getByCourseIdAndStudentId(Integer courseId, Integer studentId) {
        LambdaQueryWrapper<StudentCourseShopEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(StudentCourseShopEntity::getId)
                .eq(StudentCourseShopEntity::getCourseId, courseId)
                .eq(StudentCourseShopEntity::getStudentId, studentId);

        return mapper.selectOne(wrapper);
    }

    public List<StudentCourseShoppedItemVO> listStudentCourseSelected(Integer studentId) {
        return mapper.listStudentCourseShopped(studentId);
    }

    public List<StudentExamItemVO> listStudentExam(Integer studentId) {
        return mapper.listStudentExam(studentId);
    }

    public Integer countStudentCourseShoppedByTimePart(Integer studentId, String timePart) {
        return mapper.countStudentCourseShoppedByTimePart(studentId, timePart);
    }

    public List<TimetableItemVO> listStudentTimetable(Integer studentId) {
        return mapper.listStudentTimetable(studentId);
    }

    public Integer countTeacherGrade(Integer teacherId, String courseName, String studentName) {
        return mapper.countTeacherGrade(teacherId, courseName, studentName);
    }

    public List<TeacherGradeItemVO> getTeacherGradePage(Integer index, Integer teacherId, String courseName, String studentName) {
        Page<TeacherGradeItemVO> page = new Page<>(index, PAGE_SIZE);
        return mapper.getTeacherGradePage(page, teacherId, courseName, studentName).getRecords();
    }
}
