package com.tefuir.service.impl;

import com.tefuir.mapper.ExamMapper;
import com.tefuir.mapper.ScoreMapper;
import com.tefuir.mapper.StudentMapper;
import com.tefuir.pojo.entity.Exam;
import com.tefuir.pojo.entity.Score;
import com.tefuir.pojo.entity.Student;
import com.tefuir.pojo.vo.StudentExamScoreVO;
import com.tefuir.service.IScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScoreServiceImpl implements IScoreService {
    private final ScoreMapper scoreMapper;
    private final ExamMapper examMapper;
    private final StudentMapper studentMapper;

    @Override
    public List<StudentExamScoreVO> getStudentExamScores() {
        List<Student> studentList = studentMapper.selectList(null); //无条件的select查询
        List<Exam> examList = examMapper.selectList(null);
        List<Score> scoreList = scoreMapper.selectList(null);
        Map<Integer, Student> studentMap = studentList.stream()  //将list集合转为map集合
                .collect(Collectors.toMap(Student::getStudentId, s -> s));
        Map<Integer, Exam> examMap = examList.stream()
                .collect(Collectors.toMap(Exam::getExamId, e -> e));
        ArrayList<StudentExamScoreVO> result = new ArrayList<>();
        for(Score score : scoreList){
            Student student = studentMap.get(score.getStudentId());
            Exam exam = examMap.get(score.getExamId());
            if(student != null && exam != null){
                StudentExamScoreVO vo = new StudentExamScoreVO();
                vo.setStudentId(student.getStudentId());
                vo.setStudentName(student.getStudentName());
                vo.setExamName(exam.getExamName());
                vo.setScore(score.getScore());
                result.add(vo);
            }
        }

        return result;
    }
}
