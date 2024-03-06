package com.commute.controller;

import com.commute.dto.AttendanceRequest;
import com.commute.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/attendance")
@RequiredArgsConstructor
@RestController
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/start-work")
    public ResponseEntity<String> startWork(@RequestBody AttendanceRequest request) {
        String message = attendanceService.startWork(request.getEmployeeId());
        return ResponseEntity.ok(message);
    }

    @PostMapping("/end-work")
    public ResponseEntity<String> endWork(@RequestBody AttendanceRequest request) {
        String message = attendanceService.endWork(request.getEmployeeId());
        return ResponseEntity.ok(message);
    }

}
