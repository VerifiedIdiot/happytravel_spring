package kr.happytravel.erp.attendances.controller;

import kr.happytravel.erp.attendances.model.AttendanceModel;
import kr.happytravel.erp.attendances.service.AttendanceService;
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
public class AttendanceController {
      private final Logger logger = LogManager.getLogger(this.getClass());
    private final AttendanceService attendanceService;

    // Create
    @PostMapping("/attendance")
    public ResponseEntity<String> createAttendance(@RequestBody AttendanceModel attendance, HttpServletRequest request,
                                               HttpServletResponse response, HttpSession session) throws Exception {

        try {
            logger.info("Received request to create attendance: " + attendance);
            int result = attendanceService.insertAttendance(attendance);
            logger.info("Created attendance, result: " + result);
            return ResponseEntity.ok("Attendance created successfully");
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid argument: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            throw e;
        }
    }

    // Read (List)
    @GetMapping("/attendance-list")
    public ResponseEntity<List<AttendanceModel>> getAttendanceList(@RequestParam Map<String, Object> paramMap, HttpServletRequest request,
                                                               HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request with parameters: " + paramMap);
            List<AttendanceModel> attendances = attendanceService.getAttendanceList(paramMap);
            logger.info("Fetched " + attendances.size() + " attendances.");
            return ResponseEntity.ok(attendances);
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid argument: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            throw e;
        }
    }

    // Read (Single)
    @GetMapping("/attendance")
    public ResponseEntity<AttendanceModel> getAttendance(@RequestParam Map<String, Object> paramMap, HttpServletRequest request,
                                                     HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to get attendances with parameters: " + paramMap);
            AttendanceModel attendance = attendanceService.selectAttendance(paramMap);
            if (attendance == null) {
                logger.warn("Attendance not found with parameters: " + paramMap);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            logger.info("Fetched agency: " + attendance);
            return ResponseEntity.ok(attendance);
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid argument: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            throw e;
        }
    }

    // Update
    @PutMapping("/attendance")
    public ResponseEntity<String> updateAttendance(@RequestBody AttendanceModel attendance, HttpServletRequest request,
                                               HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to update attendance: " + attendance);
            int result = attendanceService.updateAttendance(attendance);
            if (result == 0) {
                logger.warn("No attendance updated: " + attendance);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Attendance not found for update");
            }
            logger.info("Updated attendance, result: " + result);
            return ResponseEntity.ok("Attendance updated successfully");
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid argument: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            throw e;
        }
    }

    // Delete
    @DeleteMapping("/attendance")
    public ResponseEntity<String> deleteAttendance(@RequestParam Map<String, Object> paramMap, HttpServletRequest request,
                                               HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to delete attendance with parameters: " + paramMap);
            int result = attendanceService.deleteAttendance(paramMap);
            if (result == 0) {
                logger.warn("No attendance deleted with parameters: " + paramMap);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Attendance not found for deletion");
            }
            logger.info("Deleted attendance, result: " + result);
            return ResponseEntity.ok("Attendance deleted successfully");
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid argument: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            throw e;
        }
    }
}
