package kr.happytravel.erp.sales.controller;

import kr.happytravel.erp.sales.model.sales.AgencyModel;
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
    public ResponseEntity<String> createAgency(@RequestBody AgencyModel agency, HttpServletRequest request,
                                               HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to create agency: " + agency);
            int result = agencyService.insertAgency(agency);
            logger.info("Created agency, result: " + result);
            return ResponseEntity.ok("Agency created successfully");
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid argument: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            throw e;
        }
    }

    // Read (List)
    @GetMapping("/agency-list")
    public ResponseEntity<List<AgencyModel>> getAgencyList(@RequestParam Map<String, Object> paramMap, HttpServletRequest request,
                                                           HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request with parameters: " + paramMap);
            List<AgencyModel> agencies = agencyService.getAgencyList(paramMap);
            logger.info("Fetched " + agencies.size() + " agencies.");
            return ResponseEntity.ok(agencies);
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid argument: " + e.getMessage());
            throw e;
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
    public ResponseEntity<String> updateAgency(@RequestBody AgencyModel agency, HttpServletRequest request,
                                               HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to update agency: " + agency);
            int result = agencyService.updateAgency(agency);
            if (result == 0) {
                logger.warn("No agency updated: " + agency);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agency not found for update");
            }
            logger.info("Updated agency, result: " + result);
            return ResponseEntity.ok("Agency updated successfully");
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid argument: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            throw e;
        }
    }

    // Delete
    @DeleteMapping("/agency")
    public ResponseEntity<String> deleteAgency(@RequestParam Map<String, Object> paramMap, HttpServletRequest request,
                                               HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to delete agency with parameters: " + paramMap);
            int result = agencyService.deleteAgency(paramMap);
            if (result == 0) {
                logger.warn("No agency deleted with parameters: " + paramMap);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agency not found for deletion");
            }
            logger.info("Deleted agency, result: " + result);
            return ResponseEntity.ok("Agency deleted successfully");
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid argument: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            throw e;
        }
    }
}
