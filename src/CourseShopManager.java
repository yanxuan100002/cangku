package com.rainng.coursesystem.manager.student;

import com.rainng.coursesystem.dao.CourseShopDAO;
import com.rainng.coursesystem.dao.StudentCourseShopDAO;
import com.rainng.coursesystem.manager.BaseManager;
import com.rainng.coursesystem.model.entity.StudentCourseShopEntity;
import com.rainng.coursesystem.model.vo.response.table.StudentCourseShoppedItemVO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("student_CourseManager")
public class CourseShopManager extends BaseManager {
    private final StudentCourseShopDAO studentCourseshopDAO;
    private final CourseShopDAO courseshopDAO;

    public CourseShopManager(StudentCourseShopDAO studentCourseshopDAO, CourseShopDAO courseshopDAO) {
        this.studentCourseshopDAO = studentCourseshopDAO;
        this.courseshopDAO = courseshopDAO;
    }

    public StudentCourseShopEntity getStudentCourseById(Integer studentCourseId) {
        return studentCourseshopDAO.get(studentCourseId);
    }

    @Transactional
    public int deleteStudentCourse(StudentCourseShopEntity studentCourseshopEntity) {
        courseshopDAO.decreaseSelectedCount(studentCourseshopEntity.getCourseId());
        return studentCourseshopDAO.delete(studentCourseshopEntity.getId());
    }

    public List<StudentCourseShoppedItemVO> listStudentCourseShopped(Integer studentId) {
        return studentCourseshopDAO.listStudentCourseSelected(studentId);
    }
}
