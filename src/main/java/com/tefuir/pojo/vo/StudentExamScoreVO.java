package com.tefuir.pojo.vo;

import lombok.Data;

@Data
public class StudentExamScoreVO {
    private Integer studentId;
    private String studentName;
    private String examName;
    private Double score;
}
