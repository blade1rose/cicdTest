package com.tefuir.service;

import com.tefuir.pojo.vo.StudentExamScoreVO;

import java.util.List;

public interface IScoreService {
    List<StudentExamScoreVO> getStudentExamScores();
}
