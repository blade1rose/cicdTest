package com.tefuir.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;

@Data
@TableName("student")
public class Student {
    @TableId
    private Integer studentId;
    private String studentName;
    private String grade;
    private Gender gender;
    private LocalDate birthDate;
    private String phone;

    public enum Gender {
        male, female
    }
}

