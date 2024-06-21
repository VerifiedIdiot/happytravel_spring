package kr.happytravel.erp.attendances.controller;

import kr.happytravel.erp.attendances.model.AttendanceManagementModel;
import kr.happytravel.erp.attendances.model.MyAttendanceResponseModel;
import kr.happytravel.erp.attendances.model.MyVacationResponseModel;
import kr.happytravel.erp.attendances.service.MyAttendanceService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/attendance")
@RestController
@RequiredArgsConstructor
public class MyAttendanceController {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private final MyAttendanceService myAttendanceService;


    @GetMapping("/api/attendance")
    public ResponseEntity<List<MyAttendanceResponseModel>> myAttendanceList(@RequestParam String empId) {
        try {
            logger.info("Received request read myAttendanceList");
            logger.info("Received request with parameters: " + empId);
            List<MyAttendanceResponseModel> myAttendanceList = myAttendanceService.getMyAttendanceList(empId);
            logger.info("Detched " + myAttendanceList + " empList.");
            return ResponseEntity.ok(myAttendanceList);
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid argument: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping("/api/vacation")
    public ResponseEntity<List<MyVacationResponseModel>> myVacationList(@RequestParam String empId) {
        try {
            logger.info("Received request read myVacationList");
            logger.info("Received request with parameters: " + empId);
            List<MyVacationResponseModel> myVacationList = myAttendanceService.getMyVacationList(empId);
            logger.info("Detched " + myVacationList + " empList.");
            return ResponseEntity.ok(myVacationList);
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid argument: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            throw e;
        }
    }

    // Create
    @PostMapping("/add")
    public ResponseEntity<String> insertAttendanceManagement(@RequestBody AttendanceManagementModel attendanceManagement, HttpServletRequest request,
                                                             HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to create attendanceManagement: " + attendanceManagement);
            int result = myAttendanceService.insertAttendanceManagement(attendanceManagement);
            logger.info("Created attendance, result: " + result);
            return ResponseEntity.ok("AttendanceManagement created successfully");
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid argument: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            throw e;
        }
    }
}
