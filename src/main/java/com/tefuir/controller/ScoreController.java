package com.tefuir.controller;

import com.tefuir.pojo.vo.StudentExamScoreVO;
import com.tefuir.service.IScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class ScoreController {
    private final IScoreService scoreService;

    @GetMapping("/score")
    public ResponseEntity<List<StudentExamScoreVO>> getStudentExamScores() {
        List<StudentExamScoreVO> scores = scoreService.getStudentExamScores();
        return ResponseEntity.ok(scores);
    }
}
