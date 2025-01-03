package com.rainng.coursesystem.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainng.coursesystem.model.entity.StudentCourseShopEntity;
import com.rainng.coursesystem.model.vo.response.table.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCourseShopMapper extends BaseMapper<StudentCourseShopEntity> {

    Integer count(@Param("className")String className, @Param("courseName")String courseName, @Param("studentName")String studentName);

    IPage<StudentCourseItemVO> getPage(IPage<StudentCourseItemVO> page, @Param("className")String className, @Param("courseName")String courseName,@Param("studentName") String studentName);

    Integer countTeacherGrade(@Param("teacherId")Integer teacherId,@Param("courseName") String courseName,@Param("studentName") String studentName);

    IPage<TeacherGradeItemVO> getTeacherGradePage(IPage<TeacherGradeItemVO> page, @Param("teacherId")Integer teacherId, @Param("courseName")String courseName,@Param("studentName") String studentName);

    List<StudentCourseShoppedItemVO> listStudentCourseShopped(Integer studentId);

    List<StudentExamItemVO> listStudentExam(Integer studentId);

    Integer countStudentCourseShoppedByTimePart(@Param("studentId")Integer studentId,@Param("timePart") String timePart);

    List<TimetableItemVO> listStudentTimetable(Integer studentId);
}
