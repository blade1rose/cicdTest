package com.tefuir.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("score")
public class Score {
    @TableId
    private Integer scoreId;
    private Integer studentId;
    private Integer examId;
    private Double score;
    private LocalDateTime createTime;
}
