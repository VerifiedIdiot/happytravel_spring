package kr.happytravel.erp.sales.controller;

import kr.happytravel.erp.sales.dto.AgencyDto;
import kr.happytravel.erp.sales.dto.CountryDto;
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

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class AgencyController {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final AgencyService agencyService;

    // Create
    @PostMapping("/agency")
    public ResponseEntity<Boolean> insertAgency(@RequestBody Map<String, Object> paramMap, HttpServletRequest request,
                                                HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to create agency: " + paramMap);
            return ResponseEntity.ok(agencyService.insertAgency(paramMap)==1);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.ok(false);
        }
    }

    // Read (List)
    @GetMapping("/agency-list")
    public ResponseEntity<List<AgencyDto>> getAgencyList(@RequestParam Map<String, Object> paramMap, HttpServletRequest request,
                                                         HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request with parameters: " + paramMap);
            List<AgencyDto> agencies = agencyService.getAgencyList(paramMap);
            logger.info("Fetched " + agencies.size() + " agencies.");
            return ResponseEntity.ok(agencies);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            throw e;
        }
    }

    // Read (Single)
    @GetMapping("/agency-detail")
    public ResponseEntity<AgencyDto> getAgency(@RequestParam Map<String, Object> paramMap, HttpServletRequest request,
                                               HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to get agency with parameters: " + paramMap);
            // 파라미터 확인
            if (!paramMap.containsKey("agencyCode") || !paramMap.containsKey("empId")) {
                logger.warn("Missing required parameters: agencyCode or empId");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            AgencyDto agencyDto = agencyService.selectAgency(paramMap);
            if (agencyDto == null) {
                logger.warn("agency not found with parameters: " + paramMap);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            logger.info("Fetched agency: " + agencyDto);
            return ResponseEntity.ok(agencyDto);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @GetMapping("/agency-count")
    public ResponseEntity<?> getAgencyCnt(@RequestParam(required = true) Map<String, Object> paramMap) {
        try {
            logger.info("Received request with parameters: " + paramMap);
            int result = agencyService.getAgencyCnt(paramMap);
            if (result == 0) {
                logger.warn("AgencyCnt not found with parameters: " + paramMap);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
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
            logger.info("Received request to Y/N with parameters: " + paramMap);
            return ResponseEntity.ok(agencyService.updateAgencyYN(paramMap) == 1);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.ok(false);
        }
    }

    @GetMapping("/agency-countries")
    public ResponseEntity<List<CountryDto>> getCountries(@RequestParam Map<String, Object> paramMap) throws Exception {
        try {
            logger.info("Received request to get country information");
            return ResponseEntity.ok(agencyService.getCountries(paramMap));
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
