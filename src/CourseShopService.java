package com.rainng.coursesystem.service.student;

import com.rainng.coursesystem.manager.OptionManager;
import com.rainng.coursesystem.manager.student.CourseShopManager;
import com.rainng.coursesystem.model.entity.StudentCourseShopEntity;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import com.rainng.coursesystem.service.BaseService;
import org.springframework.stereotype.Service;

@Service("student_courseService")
public class CourseShopService extends BaseService {
    private final CourseShopManager manager;
    private final OptionManager optionManager;

    public CourseShopService(CourseShopManager manager, OptionManager optionManager) {
        this.manager = manager;
        this.optionManager = optionManager;
    }

    public ResultVO list() {
        Integer studentId = getUserId();
        return result(manager.listStudentCourseShopped(studentId));
    }

    public ResultVO delete(Integer studentCourseId) {
        Integer studentId = getUserId();
        if (!optionManager.getAllowStudentSelect()) {
            return failedResult("现在不是选课退课时间!");
        }
        StudentCourseShopEntity studentCourse = manager.getStudentCourseById(studentCourseId);
        if (studentCourse == null) {
            return failedResult("学生选课Id:" + studentCourseId + "不存在");
        }
        if (!studentCourse.getStudentId().equals(studentId)) {
            return failedResult("此课程非此学生所选!");
        }
        if (studentCourse.getDailyScore() != null || studentCourse.getExamScore() != null || studentCourse.getScore() != null) {
            return failedResult("学生已获得成绩, 不能退选");
        }

        manager.deleteStudentCourse(studentCourse);
        return result("退选成功");
    }
}
