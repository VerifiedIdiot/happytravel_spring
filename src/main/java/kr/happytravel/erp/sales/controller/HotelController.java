package kr.happytravel.erp.sales.controller;

import kr.happytravel.erp.sales.dto.CountryDto;
import kr.happytravel.erp.sales.dto.HotelDto;
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
import java.util.*;
import com.google.firebase.cloud.StorageClient;
import com.google.cloud.storage.Blob;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class HotelController {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final HotelService hotelService;

    // Create
    @PostMapping("/hotel")
    public ResponseEntity<Boolean> insertHotel(@RequestBody Map<String, Object> paramMap, HttpServletRequest request,
                                               HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to create hotel: " + paramMap);
            return ResponseEntity.ok(hotelService.insertHotel(paramMap) == 1);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.ok(false);
        }
    }

    @GetMapping("/hotel-count")
    public ResponseEntity<?> getHotelCnt(@RequestParam(required = true) Map<String, Object> paramMap) {
        try {
            logger.info("Received request with parameters: " + paramMap);
            int result = hotelService.getHotelCnt(paramMap);
            if (result == 0) {
                logger.warn("HotelCnt not found with parameters: " + paramMap);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }

    }

    // Read (List)
    @GetMapping("/hotel-list")
    public ResponseEntity<List<HotelDto>> getHotelList(@RequestParam Map<String, Object> paramMap, HttpServletRequest request,
                                                       HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request with parameters: " + paramMap);

            List<HotelDto> hotels = hotelService.getHotelList(paramMap);
            logger.info("Fetched " + hotels.size() + " hotels.");
            return ResponseEntity.ok(hotels);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            throw e;
        }
    }

    // Read (Single)
    @GetMapping("/hotel-detail")
    public ResponseEntity<HotelDto> getHotel(@RequestParam Map<String, Object> paramMap, HttpServletRequest request,
                                             HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to get hotel with parameters: " + paramMap);
            // 파라미터 확인
            if (!paramMap.containsKey("hotelCode") || !paramMap.containsKey("empId")) {
                logger.warn("Missing required parameters: hotelCode or empId");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            HotelDto hotelDto = hotelService.selectHotel(paramMap);
            if (hotelDto == null) {
                logger.warn("Hotel not found with parameters: " + paramMap);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            logger.info("Fetched hotel: " + hotelDto);
            return ResponseEntity.ok(hotelDto);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/getImageUrl")
    public ResponseEntity<?> getImageUrl(@RequestBody Map<String, Object> paramMap, HttpServletRequest request,
                                         HttpServletResponse response, HttpSession session) throws Exception {
        try {
            String hotelCode = (String) paramMap.get("hotelCode");
            if (hotelCode == null) {
                return ResponseEntity.badRequest().body("Hotel code is missing");
            }

            String imageUrl = fetchImageUrl(hotelCode);
            if (imageUrl == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found");
            }

            return ResponseEntity.ok(imageUrl);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    private String fetchImageUrl(String hotelCode) {
        String[] extensions = {"jpg", "jpeg", "png"};
        for (String ext : extensions) {
            String filePath = "images/" + hotelCode + "." + ext;
            try {
                Blob blob = StorageClient.getInstance().bucket().get(filePath);
                if (blob != null && blob.exists()) {
                    return blob.getMediaLink();
                }
            } catch (Exception e) {
                // Log and ignore errors, continue with next extension
            }
        }
        return null;
    }

    // Update
    @PutMapping("/hotel")
    public ResponseEntity<Boolean> updateHotel(@RequestBody Map<String, Object> paramMap, HttpServletRequest request,
                                               HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to update hotel: " + paramMap);
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
            logger.info("Received request to Y/N hotel with parameters: " + paramMap);
            return ResponseEntity.ok(hotelService.updateHotelYN(paramMap) == 1);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.ok(false);
        }
    }

    @GetMapping("/hotel-countries")
    public ResponseEntity<List<CountryDto>> getCountries(@RequestParam Map<String, Object> paramMap) throws Exception {
        try {
            logger.info("Received request to get country information");
            return ResponseEntity.ok(hotelService.getCountries(paramMap));
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
