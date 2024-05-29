package kr.happytravel.erp.sales.controller;

import kr.happytravel.erp.sales.model.sales.PackageModel;
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
    public ResponseEntity<Boolean> createPackage(@RequestBody Map<String, Object> paramMap, HttpServletRequest request,
                                                 HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to create package: " + paramMap);
            return ResponseEntity.ok(packageService.insertPackage(paramMap) == 1);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.ok(false);
        }
    }

    // Read (List)
    @GetMapping("/package-list")
    public ResponseEntity<List<PackageModel>> getPackageList(@RequestParam Map<String, Object> paramMap, HttpServletRequest request,
                                                             HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request with parameters: " + paramMap);
            List<PackageModel> packages = packageService.getPackageList(paramMap);
            logger.info("Fetched " + packages.size() + " packages.");
            return ResponseEntity.ok(packages);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Read (Single)
    @GetMapping("/package")
    public ResponseEntity<PackageModel> getPackage(@RequestParam Map<String, Object> paramMap, HttpServletRequest request,
                                                   HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to get package with parameters: " + paramMap);
            PackageModel packageModel = packageService.selectPackage(paramMap);
            if (packageModel == null) {
                logger.warn("Package not found with parameters: " + paramMap);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            logger.info("Fetched package: " + packageModel);
            return ResponseEntity.ok(packageModel);
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

    // Delete
    @DeleteMapping("/package")
    public ResponseEntity<Boolean> deletePackage(@RequestParam Map<String, Object> paramMap, HttpServletRequest request,
                                                 HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to delete package with parameters: " + paramMap);
            return ResponseEntity.ok(packageService.deletePackage(paramMap) == 1);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.ok(false);
        }
    }
}

