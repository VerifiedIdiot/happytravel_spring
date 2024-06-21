package kr.happytravel.erp.sales.controller;


import kr.happytravel.erp.sales.dto.CountryDto;
import kr.happytravel.erp.sales.dto.FlightDto;
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

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class FlightController {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final FlightService flightService;

    // Create
    @PostMapping("/flight")
    public ResponseEntity<Boolean> insertFlight(@RequestBody Map<String, Object> paramMap, HttpServletRequest request,
                                                HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to create flight: " + paramMap);
            return ResponseEntity.ok(flightService.insertFlight(paramMap) == 1 );
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.ok(false);
        }
    }

    // Read (List)
    @GetMapping("/flight-list")
    public ResponseEntity<List<FlightDto>> getFlightList(@RequestParam Map<String, Object> paramMap, HttpServletRequest request,
                                                         HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request with parameters: " + paramMap);
            List<FlightDto> flights = flightService.getFlightList(paramMap);
            logger.info("Fetched " + flights.size() + " flights.");
            return ResponseEntity.ok(flights);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            throw e;
        }
    }

    // Read (Single)
    @GetMapping("/flight-detail")
    public ResponseEntity<FlightDto> getFlight(@RequestParam Map<String, Object> paramMap, HttpServletRequest request,
                                               HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to get flight with parameters: " + paramMap);
            // 파라미터 확인
            if (!paramMap.containsKey("flightCode") || !paramMap.containsKey("empId")) {
                logger.warn("Missing required parameters: hotelCode or empId");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            FlightDto flightDto = flightService.selectFlight(paramMap);
            if (flightDto == null) {
                logger.warn("Flight not found with parameters: " + paramMap);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            logger.info("Fetched flight: " + flightDto);
            return ResponseEntity.ok(flightDto);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/flight-count")
    public ResponseEntity<?> getFlightCnt(@RequestParam(required = true) Map<String, Object> paramMap) {
        try {
            logger.info("Received request with parameters: " + paramMap);
            int result = flightService.getFlightCnt(paramMap);
            if (result == 0) {
                logger.warn("FlightCnt not found with parameters: " + paramMap);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
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

    @PutMapping("/flight-yn")
    public ResponseEntity<Boolean> updateFlightYN(@RequestParam Map<String, Object> paramMap, HttpServletRequest request,
                                                  HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to Y/N flight with parameters: " + paramMap);
            boolean result = flightService.updateFlightYN(paramMap) == 1;
            logger.info("Update flight Y/N result: " + result);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.ok(false);
        }
    }

    // Country list
    @GetMapping("/flight-countries")
    public ResponseEntity<List<CountryDto>> getCountries(@RequestParam Map<String, Object> paramMap) throws Exception {
        try {
            logger.info("Received request to get country information");
            return ResponseEntity.ok(flightService.getCountries(paramMap));
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
