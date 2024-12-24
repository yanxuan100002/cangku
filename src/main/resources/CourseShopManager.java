package com.rainng.coursesystem.manager.admin;

import com.rainng.coursesystem.dao.CourseDAO;
import com.rainng.coursesystem.dao.CourseShopDAO;
import com.rainng.coursesystem.dao.StudentCourseDAO;
import com.rainng.coursesystem.dao.TeacherDAO;
import com.rainng.coursesystem.manager.BaseManager;
import com.rainng.coursesystem.model.bo.CourseItemBO;
import com.rainng.coursesystem.model.bo.CourseShopItemBO;
import com.rainng.coursesystem.model.entity.CourseEntity;
import com.rainng.coursesystem.model.entity.CourseShopEntity;
import com.rainng.coursesystem.model.entity.TeacherEntity;
import com.rainng.coursesystem.model.vo.response.IdNameVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseShopManager extends BaseManager {
    private final TeacherDAO teacherDAO;
    private final CourseShopDAO courseshopDAO;
    private final StudentCourseDAO studentCourseDAO;

    public CourseShopManager(TeacherDAO teacherDAO, CourseShopDAO courseshopDAO, StudentCourseDAO studentCourseDAO) {
        this.teacherDAO = teacherDAO;
        this.courseshopDAO = courseshopDAO;
        this.studentCourseDAO = studentCourseDAO;
    }

    public Integer getPageCount(String departmentName, String teacherName, String name) {
        int count = courseshopDAO.count(departmentName, teacherName, name);
        return calcPageCount(count, CourseDAO.PAGE_SIZE);
    }

    public List<CourseShopItemBO> getPage(Integer index, String departmentName, String teacherName, String name) {
        return courseshopDAO.getPage(index, departmentName, teacherName, name);
    }

    public CourseShopEntity get(Integer id) {
        return courseshopDAO.get(id);
    }

    public int create(CourseShopEntity entity) {
        return courseshopDAO.insert(entity);
    }

    public int update(CourseShopEntity entity) {
        return courseshopDAO.update(entity);
    }

    public int delete(Integer id) {
        return courseshopDAO.delete(id);
    }

    public TeacherEntity getTeacherById(Integer teacherId) {
        return teacherDAO.get(teacherId);
    }

    public boolean hasStudentCourse(Integer courseId) {
        return studentCourseDAO.countByCourseId(courseId) > 0;
    }

    public List<IdNameVO> listName() {
        List<IdNameVO> voList = new ArrayList<>();
        List<CourseShopEntity> entityList = courseshopDAO.listName();
        for (CourseShopEntity entity : entityList) {
            voList.add(new IdNameVO(entity.getId(), entity.getName()));
        }

        return voList;
    }
}
