package com.tefuir.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;

@Data
@TableName("exam")
public class Exam {
    @TableId
    private Integer examId;
    private String examName;
    private LocalDate examDate;
}
