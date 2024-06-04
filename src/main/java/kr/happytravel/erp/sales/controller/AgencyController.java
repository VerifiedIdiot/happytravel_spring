package kr.happytravel.erp.sales.controller;

import kr.happytravel.erp.sales.model.sales.AgencyModel;
import kr.happytravel.erp.sales.model.sales.FlightModel;
import kr.happytravel.erp.sales.service.AgencyService;
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
import java.util.Optional;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class AgencyController {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final AgencyService agencyService;

    // Create
    @PostMapping("/agency")
    public ResponseEntity<String> createAgency(@RequestBody AgencyModel agency, HttpServletRequest request,
                                               HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to create hotel: " + agency);
            return ResponseEntity.ok("Hotel created successfully");
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.ok("error");
        }
    }

    // Read (List)
    @GetMapping("/agency-list")
    public ResponseEntity<List<AgencyModel>> getAgencyList(@RequestParam Map<String, Object> paramMap, HttpServletRequest request,
                                                           HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request with parameters: " + paramMap);
            String empId = Optional.ofNullable((String) paramMap.get("empId")).orElse("EMP30002"); // 기본 empId 설정
            paramMap.put("empId", empId);
            List<AgencyModel> agencys = agencyService.getAgencyList(paramMap);
            logger.info("Fetched " + agencys.size() + " flights.");
            return ResponseEntity.ok(agencys);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            throw e;
        }
    }

    // Read (Single)
    @GetMapping("/agency")
    public ResponseEntity<AgencyModel> getAgency(@RequestParam Map<String, Object> paramMap, HttpServletRequest request,
                                                 HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to get agency with parameters: " + paramMap);
            AgencyModel agency = agencyService.selectAgency(paramMap);
            if (agency == null) {
                logger.warn("Agency not found with parameters: " + paramMap);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            logger.info("Fetched agency: " + agency);
            return ResponseEntity.ok(agency);
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid argument: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            throw e;
        }
    }

    // Update
    @PutMapping("/agency")
    public ResponseEntity<Boolean> updateAgency(@RequestBody Map<String, Object> paramMap, HttpServletRequest request,
                                               HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to update agency: " + paramMap);
            return ResponseEntity.ok(agencyService.updateAgency(paramMap) == 1);
        }  catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.ok(false);
        }
    }

    // Y/N UPDATE
    @PutMapping("/agency-yn")
    public ResponseEntity<Boolean> updateAgencyYN(@RequestParam Map<String, Object> paramMap, HttpServletRequest request,
                                                 HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to Y/N package with parameters: " + paramMap);
            return ResponseEntity.ok(agencyService.updateAgencyYN(paramMap) == 1);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.ok(false);
        }
    }
}
