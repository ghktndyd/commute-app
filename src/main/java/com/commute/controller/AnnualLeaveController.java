package com.commute.controller;

import com.commute.dto.AnnualLeaveRequest;
import com.commute.service.AnnualLeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/annual-leave")
@RestController
public class AnnualLeaveController {

    private final AnnualLeaveService annualLeaveService;

    @PostMapping
    public ResponseEntity<String> applyForLeave(@RequestBody AnnualLeaveRequest request) {
        annualLeaveService.useAnnualLeave(request);
        return ResponseEntity.ok("연차 등록을 성공하셨습니다.");
    }

}
