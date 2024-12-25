package com.rainng.coursesystem.service.student;

import com.rainng.coursesystem.manager.OptionManager;
import com.rainng.coursesystem.manager.student.CourseShoppedManager;
import com.rainng.coursesystem.model.bo.StudentCourseShopItemBO;
import com.rainng.coursesystem.model.entity.*;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import com.rainng.coursesystem.model.vo.response.table.StudentCourseShopItemVO;
import com.rainng.coursesystem.service.BaseService;
import com.rainng.coursesystem.util.LessonTimeConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseShoppedService extends BaseService {
    private final CourseShoppedManager manager;
    private final OptionManager optionManager;
    private final LessonTimeConverter lessonTimeConverter;

    public CourseShoppedService(CourseShoppedManager manager, OptionManager optionManager, LessonTimeConverter lessonTimeConverter) {
        this.manager = manager;
        this.optionManager = optionManager;
        this.lessonTimeConverter = lessonTimeConverter;
    }

    public ResultVO getPageCount(String courseName, String teacherName) {
        Integer studentId = getUserId();
        return result(manager.getPageCount(studentId, courseName, teacherName));
    }

    public ResultVO getPage(Integer index, String courseName, String teacherName) {
        Integer studentId = getUserId();

        List<StudentCourseShopItemBO> boList = manager.getPage(index, studentId, courseName, teacherName);
        List<StudentCourseShopItemVO> voList = new ArrayList<>(boList.size());

        for (StudentCourseShopItemBO bo : boList) {
            StudentCourseShopItemVO vo = new StudentCourseShopItemVO();
            BeanUtils.copyProperties(bo, vo);
            vo.setTime(lessonTimeConverter.covertTimePart(bo.getTime()));
            voList.add(vo);
        }

        return result(voList);
    }

    public ResultVO create(Integer courseId) {
        Integer studentId = getUserId();

        if (!optionManager.getAllowStudentSelect()) {
            return failedResult("现在不是选课时间!");
        }
        StudentEntity student = manager.getStudentById(studentId);
        CourseShopEntity course = manager.getCourseById(courseId);
        if (student == null) {
            return failedResult("学生Id:" + studentId + "不存在!");
        }
        if (course == null) {
            return failedResult("课程Id:" + courseId + "不存在!");
        }
        if (!manager.inSameDepartment(courseId, studentId)) {
            return failedResult("学生不能选择非教学系的课程!");
        }
        if (course.getSelectedCount() >= course.getMaxSize()) {
            return failedResult("课容量已满!");
        }
        if (manager.getStudentCourseByCourseIdAndStudentId(courseId, studentId) != null) {
            return failedResult("学生已选修此课程!");
        }
        if (!manager.getStudentGradeById(student.getId()).equals(course.getGrade())) {
            return failedResult("学生与课程不在同一年级");
        }
        String timePart = splitTimePart(course.getTime());
        if (manager.countStudentCourseShoppedByTimePart(studentId, timePart) > 0) {
            return failedResult("上课时间冲突!");
        }

        StudentCourseShopEntity studentCourse = new StudentCourseShopEntity();
        studentCourse.setCourseId(courseId);
        studentCourse.setStudentId(studentId);
        manager.create(studentCourse);

        return result("选课成功");
    }

    private String splitTimePart(String time) {
        String[] spilt = time.split("-");
        return spilt[0] + "-" + spilt[1];
    }
}
