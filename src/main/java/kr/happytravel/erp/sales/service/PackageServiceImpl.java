package kr.happytravel.erp.sales.service;

import kr.happytravel.erp.sales.dao.PackageDao;
import kr.happytravel.erp.sales.model.sales.packages.*;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PackageServiceImpl implements PackageService {
    private final Logger logger = LogManager.getLogger(this.getClass());

    private final PackageDao packageDao;
    private final Random random = new Random();

    @Override
    public List<PackageListDTO> getPackageList(Map<String, Object> paramMap) throws Exception {
        // limit와 offset를 정수형으로 변환
        paramMap.put("limit", Integer.parseInt(paramMap.get("limit").toString()));
        paramMap.put("offset", Integer.parseInt(paramMap.get("offset").toString()));

        return packageDao.getPackageList(paramMap);
    }

    @Override
    public int getPackageCnt(Map<String, Object> paramMap) throws Exception {
        return packageDao.getPackageCnt(paramMap);
    }

    @Override
    @Transactional
    public int insertPackage(Map<String, Object> paramMap) throws Exception {
        logger.info("Starting transaction for insertPackage");
        int result = packageDao.insertPackage(paramMap);
        logger.info("Insert result: " + result);
        return result;
    }

    @Override
    @Transactional
    public int updatePackage(Map<String, Object> paramMap) throws Exception {
        logger.info("Starting transaction for updatePackage");
        int result = packageDao.updatePackage(paramMap);
        logger.info("Update result: " + result);
        return result;
    }

    @Override
    public int updatePackageYN(Map<String, Object> paramMap) throws Exception {
        logger.info("Starting for updatePackage IS_USED Y/N");
        int result = packageDao.updatePackageYN(paramMap);
        logger.info("Update Y/N result: " + result);
        return result;

    }

    @Override
    public int assignPackage(Map<String, Object> paramMap) throws Exception {
        logger.info("Starting for updatePackage ASSIGN_CODE to 1000 OR 2000 OR 3000");
        int result = packageDao.assignPackage(paramMap);
        logger.info("Update assign result: " + paramMap.values());
        return result;
    }

    @Override
    @Transactional
    public PackageDTO selectPackage(Map<String, Object> paramMap) throws Exception {
        logger.info("Selecting PackagePartners from DAO");
        return packageDao.selectPackage(paramMap);
    }

    @Override
    public List<CountryDTO> getCountries(Map<String, Object> paramMap) throws Exception {
        logger.info("Selecting Countries from DAO");
        return packageDao.getCountries(paramMap);
    }

    @Override
    public List<PartnerListDTO> getFlightList(Map<String, Object> paramMap) throws Exception {
        // limit와 offset를 정수형으로 변환
        paramMap.put("limit", Integer.parseInt(paramMap.get("limit").toString()));
        paramMap.put("offset", Integer.parseInt(paramMap.get("offset").toString()));

        return packageDao.getFlightList(paramMap);
    }

    @Override
    public int getFlightCnt(Map<String, Object> paramMap) throws Exception {
        return packageDao.getFlightCnt(paramMap);
    }

    @Override
    public List<PartnerListDTO> getHotelList(Map<String, Object> paramMap) throws Exception {
        // limit와 offset를 정수형으로 변환
        paramMap.put("limit", Integer.parseInt(paramMap.get("limit").toString()));
        paramMap.put("offset", Integer.parseInt(paramMap.get("offset").toString()));

        return packageDao.getHotelList(paramMap);
    }

    @Override
    public int getHotelCnt(Map<String, Object> paramMap) throws Exception {
        return packageDao.getHotelCnt(paramMap);
    }

    @Override
    public List<PartnerListDTO> getAgencyList(Map<String, Object> paramMap) throws Exception {
        // limit와 offset를 정수형으로 변환
        paramMap.put("limit", Integer.parseInt(paramMap.get("limit").toString()));
        paramMap.put("offset", Integer.parseInt(paramMap.get("offset").toString()));

        return packageDao.getAgencyList(paramMap);
    }

    @Override
    public int getAgencyCnt(Map<String, Object> paramMap) throws Exception {
        return packageDao.getAgencyCnt(paramMap);
    }

    @Override
    @Transactional
    public void updatePackages() throws Exception {
        logger.info("Starting transaction");

        List<InitSaleAmountModel> packages = packageDao.getPackageCodeList();
        for (InitSaleAmountModel pkg : packages) {
            logger.info(pkg);
        }
        if (packages == null || packages.isEmpty()) {
            logger.error("No packages found for update.");
            return;
        }

        // 조회된 패키지들 조작
        for (InitSaleAmountModel pkg : packages) {
            int randomValue = 10 + random.nextInt(21); // 10과 30 사이의 랜덤값
            pkg.setSaleAmount(pkg.getSaleAmount() + randomValue);
        }

        // 패키지들을 업데이트 쿼리에 전달
        Map<String, Object> updateParams = new HashMap<>();
        updateParams.put("list", packages);
        int result = packageDao.updatePackageSaleAmount(updateParams);

        logger.info("Update result: " + result);
    }

    @PostConstruct
    public void init() {
        try {
            logger.info("패키지 판매량 주작중");
            updatePackages();
        } catch (Exception e) {
            logger.error("Error during initialization: ", e);
        }
    }
}