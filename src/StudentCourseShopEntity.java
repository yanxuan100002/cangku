package com.rainng.coursesystem.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;


@TableName("rc_student_course_shop")
@Data
public class StudentCourseShopEntity {
    public static final String ID = "sc_shop_id";
    public static final String STUDENT_ID = "sc_student_shop_id";
    public static final String COURSE_ID = "sc_course_shop_id";
    public static final String DAILY_SCORE = "sc_daily_shop_score";
    public static final String EXAM_SCORE = "sc_exam_shop_score";
    public static final String SCORE = "sc_shop_score";

    @NotNull
    @TableId(value = ID, type = IdType.AUTO)
    private Integer id;

    @NotNull(message = "必须选择学生")
    @TableField(STUDENT_ID)
    private Integer studentId;

    @NotNull(message = "必须选择课程")
    @TableField(COURSE_ID)
    private Integer courseId;

    @Range(min = 0, max = 100, message = "分数格式不正确")
    @TableField(value = DAILY_SCORE, updateStrategy = FieldStrategy.IGNORED)
    private Integer dailyScore;

    @Range(min = 0, max = 100, message = "分数必须在0-100之间")
    @TableField(value = EXAM_SCORE, updateStrategy = FieldStrategy.IGNORED)
    private Integer examScore;

    @Range(min = 0, max = 100, message = "分数必须在0-100之间")
    @TableField(value = SCORE, updateStrategy = FieldStrategy.IGNORED)
    private Integer score;
}
