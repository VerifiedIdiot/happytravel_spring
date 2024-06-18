package kr.happytravel.erp.sales.controller;

import kr.happytravel.erp.sales.model.sales.packages.*;
import kr.happytravel.erp.sales.service.PackageService;
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
public class PackageController {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final PackageService packageService;

    // Create
    @PostMapping("/package")
    public ResponseEntity<Boolean> createPackage(@RequestBody(required = true) Map<String, Object> paramMap, HttpServletRequest request,
                                                 HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to create package: " + paramMap);
            return ResponseEntity.ok(packageService.insertPackage(paramMap) == 1);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.ok(false);
        }
    }

    @GetMapping("/package-count")
    public ResponseEntity<?> getPackageCnt(@RequestParam(required = true) Map<String, Object> paramMap) {
        try {
            logger.info("Received request with parameters: " + paramMap);
            int result = packageService.getPackageCnt(paramMap);
            if (result == 0) {
                logger.warn("PackageCnt not found with parameters: " + paramMap);
                return ResponseEntity.ok(result);
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }

    }

    // Read (List)
    @GetMapping("/package-list")
    public ResponseEntity<List<PackageListDTO>> getPackageList(@RequestParam Map<String, Object> paramMap, HttpServletRequest request,
                                                               HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request with parameters: " + paramMap);
            List<PackageListDTO> packages = packageService.getPackageList(paramMap);
            logger.info("Fetched " + packages.size() + " packages.");
            return ResponseEntity.ok(packages);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Read (Single)
    @GetMapping("/package-detail")
    public ResponseEntity<PackageDTO> getPackage(@RequestParam Map<String, Object> paramMap, HttpServletRequest request,
                                                 HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to get package with parameters: " + paramMap);
            PackageDTO packageDTO = packageService.selectPackage(paramMap);
            if (packageDTO == null) {
                logger.warn("Package not found with parameters: " + paramMap);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            logger.info("Fetched package: " + packageDTO);
            return ResponseEntity.ok(packageDTO);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Update
    @PutMapping("/package")
    public ResponseEntity<Boolean> updatePackage(@RequestBody Map<String, Object> paramMap, HttpServletRequest request,
                                                 HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to update package: " + paramMap);
            return ResponseEntity.ok(packageService.updatePackage(paramMap) == 1);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.ok(false);
        }
    }

    // Y/N UPDATE
    @PutMapping("/package-yn")
    public ResponseEntity<Boolean> updatePackageYN(@RequestParam Map<String, Object> paramMap, HttpServletRequest request,
                                                   HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to Y/N package with parameters: " + paramMap);
            return ResponseEntity.ok(packageService.updatePackageYN(paramMap) == 1);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.ok(false);
        }
    }

    @PutMapping("/package-assign")
    public ResponseEntity<Boolean> assignPackage(@RequestParam Map<String, Object> paramMap, HttpServletRequest request,
                                                 HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to assingn package with parameters: " + paramMap);
            return ResponseEntity.ok(packageService.assignPackage(paramMap) == 1);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.ok(false);
        }
    }

    @GetMapping("/get-countries")
    public ResponseEntity<List<CountryDTO>> getCountries(@RequestParam Map<String, Object> paramMap) throws Exception {
        try {
            logger.info("Received request to get country informations");
            return ResponseEntity.ok(packageService.getCountries(paramMap));
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("partner-flights")
    public ResponseEntity<List<PartnerListDTO>> getFlightList(@RequestParam Map<String, Object> paramMap) throws Exception {
        try {
            logger.info("Received request to get flight list: " + paramMap );
            return ResponseEntity.ok(packageService.getFlightList(paramMap));
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("partner-flight-count")
    public ResponseEntity<?> getFlightCnt(@RequestParam Map<String, Object> paramMap) throws Exception {
        try {
            logger.info("Received request to get flight counts");
            int result = packageService.getFlightCnt(paramMap);
            if (result == 0) {
                logger.warn("FlightCnt not found with parameters: " + paramMap);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("partner-hotels")
    public ResponseEntity<List<PartnerListDTO>> getHotelList(@RequestParam Map<String, Object> paramMap) throws Exception {
        try {
            logger.info("Received request to get hotel list");
            return ResponseEntity.ok(packageService.getHotelList(paramMap));
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("partner-hotel-count")
    public ResponseEntity<?> getHotelCnt(@RequestParam Map<String, Object> paramMap) throws Exception {
        try {
            logger.info("Received request to get hotel counts");
            int result = packageService.getHotelCnt(paramMap);
            if (result == 0) {
                logger.warn("HotelCnt not found with parameters: " + paramMap);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("partner-agencies")
    public ResponseEntity<List<PartnerListDTO>> getAgencyList(@RequestParam Map<String, Object> paramMap) throws Exception {
        try {
            logger.info("Received request to get agency list");
            return ResponseEntity.ok(packageService.getAgencyList(paramMap));
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("partner-agency-count")
    public ResponseEntity<?> getAgencyCnt(@RequestParam Map<String, Object> paramMap) throws Exception {
        try {
            logger.info("Received request to get agency counts");
            int result = packageService.getAgencyCnt(paramMap);
            if (result == 0) {
                logger.warn("AgencyCnt not found with parameters: " + paramMap);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

