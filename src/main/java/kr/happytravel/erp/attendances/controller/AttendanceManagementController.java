package kr.happytravel.erp.attendances.controller;

import kr.happytravel.erp.attendances.model.AttendanceManagementModel;
import kr.happytravel.erp.attendances.service.AttendanceManagementService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/attendances")
@RequiredArgsConstructor
public class AttendanceManagementController {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final AttendanceManagementService attendanceManagementService;

    // Create
    @PostMapping("/attendanceManagement")
    public ResponseEntity<String> createAttendanceManagement(@RequestBody AttendanceManagementModel attendanceManagement, HttpServletRequest request,
                                                             HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to create attendanceManagement: " + attendanceManagement);
            int result = attendanceManagementService.insertAttendanceManagement(attendanceManagement);
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

    // Read (List)
    @GetMapping("/attendanceManagement-list")
    public ResponseEntity<List<AttendanceManagementModel>> getAttendanceManagementList(@RequestParam Map<String, Object> paramMap, HttpServletRequest request,
                                                                   HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request with parameters: " + paramMap);
            List<AttendanceManagementModel> attendanceManagements = attendanceManagementService.getAttendanceManagementList(paramMap);
            logger.info("Fetched " + attendanceManagements.size() + " attendanceManagements.");
            return ResponseEntity.ok(attendanceManagements);
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid argument: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            throw e;
        }
    }

    // Read (Single)
    @GetMapping("/attendanceManagement")
    public ResponseEntity<AttendanceManagementModel> getAttendanceManagement(@RequestParam Map<String, Object> paramMap, HttpServletRequest request,
                                                         HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to get attendanceManagements with parameters: " + paramMap);
            AttendanceManagementModel attendanceManagement = attendanceManagementService.selectAttendanceManagement(paramMap);
            if (attendanceManagement == null) {
                logger.warn("AttendanceManagement not found with parameters: " + paramMap);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            logger.info("Fetched agency: " + attendanceManagement);
            return ResponseEntity.ok(attendanceManagement);
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid argument: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            throw e;
        }
    }

    // 작성 날짜 조회
    @GetMapping("/maxAttendanceTypeCode")
    public ResponseEntity<String> getMaxAttendanceTypeCode(@RequestParam String creationDate) {
        try {
            String maxCode = attendanceManagementService.getMaxAttendanceTypeCode(creationDate);
            return ResponseEntity.ok(maxCode);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 로그인 부서아이디로 부장 조회
    @GetMapping("/managerIdByDeptCode")
    public ResponseEntity<String> getManagerIdByDeptCode(@RequestParam String deptCode) {
        try {
            String managerId = attendanceManagementService.getManagerIdByDeptCode(deptCode);
            return ResponseEntity.ok(managerId);
        } catch (Exception e) {
            logger.error("An error occurred while fetching manager ID by department code:", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Update
    @PutMapping("/attendanceManagement")
    public ResponseEntity<String> updateAttendanceManagement(@RequestBody AttendanceManagementModel attendanceManagement, HttpServletRequest request,
                                                   HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to update attendanceManagement: " + attendanceManagement);
            int result = attendanceManagementService.updateAttendanceManagement(attendanceManagement);
            if (result == 0) {
                logger.warn("No attendanceManagement updated: " + attendanceManagement);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("AttendanceManagement not found for update");
            }
            logger.info("Updated attendanceManagement, result: " + result);
            return ResponseEntity.ok("AttendanceManagement updated successfully");
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid argument: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            throw e;
        }
    }

    // Delete
    @DeleteMapping("/attendanceManagement")
    public ResponseEntity<String> deleteAttendanceManagement(@RequestParam Map<String, Object> paramMap, HttpServletRequest request,
                                                   HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to delete attendanceManagement with parameters: " + paramMap);
            int result = attendanceManagementService.deleteAttendanceManagement(paramMap);
            if (result == 0) {
                logger.warn("No attendanceManagement deleted with parameters: " + paramMap);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("AttendanceManagement not found for deletion");
            }
            logger.info("Deleted attendanceManagement, result: " + result);
            return ResponseEntity.ok("AttendanceManagement deleted successfully");
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid argument: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            throw e;
        }
    }


}
