package com.rainng.coursesystem.manager.student;

import com.rainng.coursesystem.dao.*;
import com.rainng.coursesystem.manager.BaseManager;
import com.rainng.coursesystem.model.bo.StudentCourseSelectItemBO;
import com.rainng.coursesystem.model.bo.StudentCourseShopItemBO;
import com.rainng.coursesystem.model.entity.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CourseShoppedManager extends BaseManager {
    private final CourseShopDAO courseshopDAO;
    private final StudentDAO studentDAO;
    private final StudentCourseShopDAO studentCourseshopDAO;

    public CourseShoppedManager(CourseShopDAO courseshopDAO, StudentDAO studentDAO, StudentCourseShopDAO studentCourseshopDAO) {
        this.courseshopDAO = courseshopDAO;
        this.studentDAO = studentDAO;
        this.studentCourseshopDAO = studentCourseshopDAO;
    }

    public Integer getPageCount(Integer studentId, String courseName, String teacherName) {
        Integer departmentId = studentDAO.getDepartmentIdById(studentId);
        Integer grade = studentDAO.getGradeById(studentId);
        return calcPageCount(courseshopDAO.countStudentCanShop(departmentId, studentId, grade, courseName, teacherName), StudentCourseDAO.PAGE_SIZE);
    }

    public List<StudentCourseShopItemBO> getPage(Integer index, Integer studentId, String courseName, String teacherName) {
        Integer departmentId = studentDAO.getDepartmentIdById(studentId);
        Integer grade = studentDAO.getGradeById(studentId);
        return courseshopDAO.getStudentCanShopPage(index,  studentId,departmentId, grade, courseName, teacherName);
    }

    public CourseShopEntity getCourseById(Integer courseId) {
        return courseshopDAO.get(courseId);
    }

    public StudentEntity getStudentById(Integer studentId) {
        return studentDAO.get(studentId);
    }

    public boolean inSameDepartment(Integer courseId, Integer studentId) {
        return courseshopDAO.getDepartmentIdById(courseId)
                .equals(studentDAO.getDepartmentIdById(studentId));
    }

    public StudentCourseShopEntity getStudentCourseByCourseIdAndStudentId(Integer courseId, Integer studentId) {
        return studentCourseshopDAO.getByCourseIdAndStudentId(courseId, studentId);
    }

    public Integer getStudentGradeById(Integer studentId) {
        return studentDAO.getGradeById(studentId);
    }

    @Transactional
    public int create(StudentCourseShopEntity entity) {
        courseshopDAO.increaseSelectedCount(entity.getCourseId());
        return studentCourseshopDAO.insert(entity);
    }

    public int countStudentCourseShoppedByTimePart(Integer studentId, String timePart) {
        return studentCourseshopDAO.countStudentCourseShoppedByTimePart(studentId, timePart);
    }
}
