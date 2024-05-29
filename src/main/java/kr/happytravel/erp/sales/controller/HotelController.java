package kr.happytravel.erp.sales.controller;

import kr.happytravel.erp.sales.model.sales.HotelModel;
import kr.happytravel.erp.sales.service.HotelService;
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
public class HotelController {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final HotelService hotelService;

    // Create
    @PostMapping("/hotel")
    public ResponseEntity<String> createHotel(@RequestBody HotelModel hotel, HttpServletRequest request,
                                              HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to create hotel: " + hotel);
            int result = hotelService.insertHotel(hotel);
            logger.info("Created hotel, result: " + result);
            return ResponseEntity.ok("Hotel created successfully");
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid argument: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            throw e;
        }
    }

    // Read (List)
    @GetMapping("/hotel-list")
    public ResponseEntity<List<HotelModel>> getHotelList(@RequestParam Map<String, Object> paramMap, HttpServletRequest request,
                                                         HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request with parameters: " + paramMap);
            List<HotelModel> hotels = hotelService.getHotelList(paramMap);
            logger.info("Fetched " + hotels.size() + " hotels.");
            return ResponseEntity.ok(hotels);
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid argument: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            throw e;
        }
    }

    // Read (Single)
    @GetMapping("/hotel")
    public ResponseEntity<HotelModel> getHotel(@RequestParam Map<String, Object> paramMap, HttpServletRequest request,
                                               HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to get hotel with parameters: " + paramMap);
            HotelModel hotel = hotelService.selectHotel(paramMap);
            if (hotel == null) {
                logger.warn("Hotel not found with parameters: " + paramMap);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            logger.info("Fetched hotel: " + hotel);
            return ResponseEntity.ok(hotel);
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid argument: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            throw e;
        }
    }

    // Update
    @PutMapping("/hotel")
    public ResponseEntity<String> updateHotel(@RequestBody HotelModel hotel, HttpServletRequest request,
                                              HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to update hotel: " + hotel);
            int result = hotelService.updateHotel(hotel);
            if (result == 0) {
                logger.warn("No hotel updated: " + hotel);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hotel not found for update");
            }
            logger.info("Updated hotel, result: " + result);
            return ResponseEntity.ok("Hotel updated successfully");
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid argument: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            throw e;
        }
    }

    // Delete
    @DeleteMapping("/hotel")
    public ResponseEntity<String> deleteHotel(@RequestParam Map<String, Object> paramMap, HttpServletRequest request,
                                              HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to delete hotel with parameters: " + paramMap);
            int result = hotelService.deleteHotel(paramMap);
            if (result == 0) {
                logger.warn("No hotel deleted with parameters: " + paramMap);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hotel not found for deletion");
            }
            logger.info("Deleted hotel, result: " + result);
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
