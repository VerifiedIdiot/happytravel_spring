package kr.happytravel.erp.attendances.controller;

import kr.happytravel.erp.attendances.model.AttendanceConfirmResponse;
import kr.happytravel.erp.attendances.model.AttendanceConfirmResponseDto;
import kr.happytravel.erp.attendances.model.AttendanceManageResponse;
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
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceManagementController {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final AttendanceManagementService attendanceManagementService;

    // Read (List)
    @GetMapping("/attendanceManagement")
    public ResponseEntity<List<AttendanceManageResponse>> getAttendanceManagementList(@RequestParam String deptCode) throws Exception {
        try {
            logger.info("Received request to get attendanceManagements: ");
            List<AttendanceManageResponse> attendanceManagements = attendanceManagementService.getAttendanceManagementList(deptCode);
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

    @PatchMapping("/attendanceManagement/{attendanceCode}")
    public ResponseEntity<String> updateAttendanceStatus(
            @PathVariable String attendanceCode,
            @RequestParam("status") String status) {
        logger.info("Received request to update attendance code " + attendanceCode + " to status: " + status);

        try {

            if ("approved".equalsIgnoreCase(status)) {
                attendanceManagementService.updateAssignCodeToApproved(attendanceCode);
                return ResponseEntity.ok("Attendance code updated to approved");
            } else if ("rejected".equalsIgnoreCase(status)) {
                attendanceManagementService.updateAssignCodeToRejected(attendanceCode);
                return ResponseEntity.ok("Attendance code updated to rejected");
            } else {
                logger.warn("Invalid status: " + status);
                return ResponseEntity.badRequest().body("Invalid status: " + status);
            }
        } catch (Exception e) {
            logger.error("An error occurred while updating attendance code:", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating attendance code");
        }
    }

    // Read (list)
    @GetMapping("/attendanceConfirm")
    public ResponseEntity<AttendanceConfirmResponseDto> getAttendanceConfirmList(@RequestParam String deptCode,
                                                                                 @RequestParam(defaultValue = "5") int limit,
                                                                                 @RequestParam(defaultValue = "0") int offset) throws Exception {
        try {
            logger.info("Received request to get attendanceConfirm: ");
            AttendanceConfirmResponseDto attendanceConfirm = attendanceManagementService.getAttendanceConfirmList(deptCode, limit, offset);
            logger.info("Fetched " + attendanceConfirm + " attendanceConfirm.");
            return ResponseEntity.ok(attendanceConfirm);
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

    @GetMapping("/countAttendanceThisMonth")
    public ResponseEntity<Integer> countAttendanceThisMonth(@RequestParam String empId) {
        try {
            int count = attendanceManagementService.countAttendanceThisMonth(empId);
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            logger.error("An error occurred while counting attendance this month:", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}
