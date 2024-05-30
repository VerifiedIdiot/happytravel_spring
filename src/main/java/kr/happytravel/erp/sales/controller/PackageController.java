package kr.happytravel.erp.sales.controller;

import kr.happytravel.erp.sales.model.sales.PackageReqModel;
import kr.happytravel.erp.sales.model.sales.PackageResModel;
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
    // Read (List)
    @GetMapping("/package-list")
    public ResponseEntity<List<PackageResModel>> getPackageList(@RequestParam Map<String, Object> paramMap, HttpServletRequest request,
                                                                HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request with parameters: " + paramMap);
            List<PackageResModel> packages = packageService.getPackageList(paramMap);
            logger.info("Fetched " + packages.size() + " packages.");
            return ResponseEntity.ok(packages);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    // Read (Single)
    @GetMapping("/package")
    public ResponseEntity<PackageResModel> getPackage(@RequestParam Map<String, Object> paramMap, HttpServletRequest request,
                                                      HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to get package with parameters: " + paramMap);
            PackageResModel packageResModel = packageService.selectPackage(paramMap);
            if (packageResModel == null) {
                logger.warn("Package not found with parameters: " + paramMap);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            logger.info("Fetched package: " + packageResModel);
            return ResponseEntity.ok(packageResModel);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Create
    @PostMapping("/package")
    public ResponseEntity<Boolean> createPackage(@RequestBody PackageReqModel packageReqModel, HttpServletRequest request,
                                                 HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to create package: " + packageReqModel);
            return ResponseEntity.ok(packageService.insertPackage(packageReqModel) == 1);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.ok(false);
        }
    }

    // Update
    @PutMapping("/package")
    public ResponseEntity<Boolean> updatePackage(@RequestBody PackageReqModel packageReqModel, HttpServletRequest request,
                                                 HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to update package: " + packageReqModel);
            return ResponseEntity.ok(packageService.updatePackage(packageReqModel) == 1);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.ok(false);
        }
    }

    // Y/N UPDATE
    @PutMapping("/package-yn")
    public ResponseEntity<Boolean> updatePackageYN(@RequestParam PackageReqModel packageReqModel, HttpServletRequest request,
                                             HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to Y/N package with parameters: " + packageReqModel);
            return ResponseEntity.ok(packageService.updatePackageYN(packageReqModel) == 1);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.ok(false);
        }
    }

    @PutMapping("/package-assign")
    public ResponseEntity<Boolean> assignPackage(@RequestParam PackageReqModel packageReqModel, HttpServletRequest request,
                                                   HttpServletResponse response, HttpSession session) throws Exception {
        try {
            logger.info("Received request to assingn package with parameters: " + packageReqModel);
            return ResponseEntity.ok(packageService.assignPackage(packageReqModel) == 1);
        } catch (Exception e) {
            logger.error("An error occurred: " + e.getMessage(), e);
            return ResponseEntity.ok(false);
        }
    }
}

