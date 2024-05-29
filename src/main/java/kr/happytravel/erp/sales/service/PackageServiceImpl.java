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
    public int updatePackage(Map<String, Object> paramMap) throws Exception {
        return packageDao.updatePackage(paramMap);
    }

    @Override
    public int deletePackage(Map<String, Object> paramMap) throws Exception {
        return packageDao.deletePackage(paramMap);
    }
}
