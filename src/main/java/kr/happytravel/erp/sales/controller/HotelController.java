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
    // 확인
    // Create
    @PostMapping("/hotel")
    public ResponseEntity<String> createHotel(@RequestBody HotelModel hotel, HttpServletRequest request,
                                              HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to create hotel: " + hotel);
            return ResponseEntity.ok("Hotel created successfully");
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.ok("error");
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
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Update
    @PutMapping("/hotel")
    public ResponseEntity<Boolean> updateHotel(@RequestBody Map<String, Object> paramMap, HttpServletRequest request,
                                               HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to update package: " + paramMap);
            return ResponseEntity.ok(hotelService.updateHotel(paramMap) == 1);
        }  catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.ok(false);
        }
    }

    // Y/N UPDATE
    @PutMapping("/hotel-yn")
    public ResponseEntity<Boolean> updateHotelYN(@RequestParam Map<String, Object> paramMap, HttpServletRequest request,
                                                 HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to Y/N package with parameters: " + paramMap);
            return ResponseEntity.ok(hotelService.updateHotelYN(paramMap) == 1);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.ok(false);
        }
    }
}
