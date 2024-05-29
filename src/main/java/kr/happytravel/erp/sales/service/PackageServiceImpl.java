package kr.happytravel.erp.sales.service;

import kr.happytravel.erp.sales.dao.AgencyDao;
import kr.happytravel.erp.sales.dao.FlightDao;
import kr.happytravel.erp.sales.dao.HotelDao;
import kr.happytravel.erp.sales.dao.PackageDao;
import kr.happytravel.erp.sales.model.sales.*;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PackageServiceImpl implements PackageService {
    private final Logger logger = LogManager.getLogger(this.getClass());

    private final PackageDao packageDao;

    @Override
    public List<PackageModel> getPackageList(Map<String, Object> paramMap) throws Exception {
        return packageDao.getPackageList(paramMap);
    }

    @Override
    public int getPackageCnt(Map<String, Object> paramMap) throws Exception {
        return packageDao.getPackageCnt(paramMap);
    }

    @Override
    public PackageModel selectPackage(Map<String, Object> paramMap) throws Exception {
        return packageDao.selectPackage(paramMap);
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
}
