package kr.happytravel.erp.sales.controller;


import kr.happytravel.erp.sales.model.sales.FlightModel;
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
    public ResponseEntity<String> createFlight(@RequestBody FlightModel flight, HttpServletRequest request,
                                               HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to create flight: " + flight);
            int result = flightService.insertFlight(flight);
            logger.info("Created flight, result: " + result);
            return ResponseEntity.ok("flight created successfully");
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid argument: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping("/flight-list")
    public ResponseEntity<List<FlightModel>> getFlightList(@RequestParam Map<String, Object> paramMap, HttpServletRequest request,
                                                         HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request with parameters: " + paramMap);
            List<FlightModel> flights = flightService.getFlightList(paramMap);
            logger.info("Fetched " + flights.size() + " flights.");
            return ResponseEntity.ok(flights);
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid argument: " + e.getMessage());
            throw e;
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
            logger.info("Received request to get hotel with parameters: " + paramMap);
            FlightModel flight = flightService.selectFlight(paramMap);
            if (flight == null) {
                logger.warn("flight not found with parameters: " + paramMap);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            logger.info("Fetched flight: " + flight);
            return ResponseEntity.ok(flight);
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid argument: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            throw e;
        }
    }
    // Update
    @PutMapping("/flight")
    public ResponseEntity<String> updateFlight(@RequestBody FlightModel flight, HttpServletRequest request,
                                              HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to update flight: " + flight);
            int result = flightService.updateFlight(flight);
            if (result == 0) {
                logger.warn("No flight updated: " + flight);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Flight not found for update");
            }
            logger.info("Updated flight, result: " + result);
            return ResponseEntity.ok("Flight updated successfully");
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid argument: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            throw e;
        }
    }

    // Delete
    @DeleteMapping("/flight")
    public ResponseEntity<String> deleteFlight(@RequestParam Map<String, Object> paramMap, HttpServletRequest request,
                                              HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to delete flight with parameters: " + paramMap);
            int result = flightService.deleteFlight(paramMap);
            if (result == 0) {
                logger.warn("No flight deleted with parameters: " + paramMap);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Flight not found for deletion");
            }
            logger.info("Deleted flight, result: " + result);
            return ResponseEntity.ok("Hotel deleted successfully");
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid argument: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            throw e;
        }
    }
}
