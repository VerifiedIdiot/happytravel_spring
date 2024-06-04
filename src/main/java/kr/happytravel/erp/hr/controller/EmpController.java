package kr.happytravel.erp.hr.controller;
import kr.happytravel.erp.hr.model.EmpModel;
import kr.happytravel.erp.hr.service.EmpService;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hr")
@RequiredArgsConstructor
public class EmpController {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final EmpService empService;

    // main 페이지
//    @GetMapping("/")
//    public ResponseEntity<List<EmpModel>> main(@RequestParam Map<String, Object> paramMap,
//                                                     HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
//        return null;
//    }

    // 사원리스트 조회
    @GetMapping("/emp-list")
    public ResponseEntity<List<EmpModel>> getemplist(@RequestParam Map<String, Object> paramMap,
                                                                HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request read emp list");
            logger.info("Received request with parameters: " + paramMap);
            List<EmpModel> empList = empService.totalemplist();
            logger.info("Detched " + empList + " empList.");
            return ResponseEntity.ok(empList);
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid argument: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            throw e;
        }
    }

    // 사원 검색 조회 searchemplist
    @GetMapping("/search")
    public ResponseEntity<List<EmpModel>> searchemp(@RequestParam Map<String, Object> paramMap,
                                                     HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request read emp search list");
            logger.info("Received request with parameters: " + paramMap);
            List<EmpModel> empList = empService.searchemplist();
            logger.info("Detched " + empList + " empList.");
            return ResponseEntity.ok(empList);
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid argument: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            throw e;
        }
    }

    // 신규 사원 등록
    @PostMapping("/create")
    public ResponseEntity<List<EmpModel>> createemp(@RequestParam Map<String, Object> paramMap,
                                                    HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request create emp_info");
            logger.info("Received request with parameters: " + paramMap);
            List<EmpModel> empList = empService.insertemp();
            logger.info("Detched " + empList + " empList.");
            return ResponseEntity.ok(empList);
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid argument: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            throw e;
        }
    }

    // 사원 정보 수정
    @PutMapping("/update")
    public ResponseEntity<List<EmpModel>> updateempinfo(@RequestParam Map<String, Object> paramMap,
                                                    HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request create emp_info");
            logger.info("Received request with parameters: " + paramMap);
            List<EmpModel> empList = empService.updateemp();
            logger.info("Detched " + empList + " empList.");
            return ResponseEntity.ok(empList);

        } catch (IllegalArgumentException e) {
            logger.warn("Invalid argument: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            throw e;
        }
    }

}
