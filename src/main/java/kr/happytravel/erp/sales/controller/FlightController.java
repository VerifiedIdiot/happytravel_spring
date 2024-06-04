package kr.happytravel.erp.sales.controller;


import kr.happytravel.erp.sales.model.sales.FlightModel;
import kr.happytravel.erp.sales.model.sales.HotelModel;
import kr.happytravel.erp.sales.service.FlightService;
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
public class FlightController {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final FlightService flightService;

    // Create
    @PostMapping("/flight")
    public ResponseEntity<String> createFlight(@RequestBody FlightModel flight, HttpServletRequest request,
                                               HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to create hotel: " + flight);
            return ResponseEntity.ok("Hotel created successfully");
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.ok("error");
        }
    }

    @GetMapping("/flight-list")
    public ResponseEntity<List<FlightModel>> getFlightList(@RequestParam Map<String, Object> paramMap, HttpServletRequest request,
                                                         HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request with parameters: " + paramMap);
            String empId = Optional.ofNullable((String) paramMap.get("empId")).orElse("EMP30002"); // 기본 empId 설정
            paramMap.put("empId", empId);
            List<FlightModel> flights = flightService.getFlightList(paramMap);
            logger.info("Fetched " + flights.size() + " flights.");
            return ResponseEntity.ok(flights);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            throw e;
        }
    }

    // Read (Single)
    @GetMapping("/flight")
    public ResponseEntity<FlightModel> getFlight(@RequestParam Map<String, Object> paramMap, HttpServletRequest request,
                                               HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to get flight with parameters: " + paramMap);
            FlightModel flight = flightService.selectFlight(paramMap);
            if (flight == null) {
                logger.warn("Flight not found with parameters: " + paramMap);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            logger.info("Fetched flight: " + flight);
            return ResponseEntity.ok(flight);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Update
    @PutMapping("/flight")
    public ResponseEntity<Boolean> updateFlight(@RequestBody Map<String, Object> paramMap, HttpServletRequest request,
                                               HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to update flight: " + paramMap);
            return ResponseEntity.ok(flightService.updateFlight(paramMap) == 1);
        }  catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.ok(false);
        }
    }

    // Y/N UPDATE
    @PutMapping("/flight-yn")
    public ResponseEntity<Boolean> updateFlightYN(@RequestParam Map<String, Object> paramMap, HttpServletRequest request,
                                              HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to update package: " + paramMap);
            return ResponseEntity.ok(flightService.updateFlightYN(paramMap) == 1);
        }  catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.ok(false);
        }
    }

}
