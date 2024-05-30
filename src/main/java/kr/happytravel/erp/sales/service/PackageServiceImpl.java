package kr.happytravel.erp.sales.service;

import kr.happytravel.erp.sales.dao.PackageDao;
import kr.happytravel.erp.sales.model.sales.PackageReqModel;
import kr.happytravel.erp.sales.model.sales.PackageResModel;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PackageServiceImpl implements PackageService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final PackageDao packageDao;

    @Override
    public List<PackageResModel> getPackageList(Map<String, Object> paramMap) throws Exception {
        return packageDao.getPackageList(paramMap);
    }

    @Override
    public int getPackageCnt(Map<String, Object> paramMap) throws Exception {
        return packageDao.getPackageCnt(paramMap);
    }

    @Override
    public PackageResModel selectPackage(Map<String, Object> paramMap) throws Exception {
        return packageDao.selectPackage(paramMap);
    }

    @Override
    @Transactional
    public int insertPackage(PackageReqModel packageReqModel) throws Exception {
        logger.info("Starting transaction for insertPackage");
        int result = packageDao.insertPackage(packageReqModel);
        logger.info("Insert result: " + result);
        return result;
    }

    @Override
    @Transactional
    public int updatePackage(PackageReqModel packageReqModel) throws Exception {
        logger.info("Starting transaction for updatePackage");
        int result = packageDao.updatePackage(packageReqModel);
        logger.info("Update result: " + result);
        return result;
    }

    @Override
    public int updatePackageYN(PackageReqModel packageReqModel) throws Exception {
        logger.info("Starting for updatePackage IS_USED Y/N");
        int result = packageDao.updatePackageYN(packageReqModel);
        logger.info("Update Y/N result: " + result);
        return result;
    }

    @Override
    public int assignPackage(PackageReqModel packageReqModel) throws Exception {
        logger.info("Starting for updatePackage ASSIGN_CODE to 1000 OR 2000 OR 3000");
        int result = packageDao.assignPackage(packageReqModel);
        logger.info("Update assign result: " + packageReqModel.toString());
        return result;
    }
}
