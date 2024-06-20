package kr.happytravel.erp.sales.service;

import kr.happytravel.erp.sales.dao.AgencyDao;
import kr.happytravel.erp.sales.dto.AgencyDto;
import kr.happytravel.erp.sales.dto.CountryDto;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AgencyServiceImpl implements AgencyService {
    private final Logger logger = LogManager.getLogger(this.getClass());

    private final AgencyDao agencyDao;

    @Override
    public List<AgencyDto> getAgencyList(Map<String, Object> paramMap) throws Exception {

        paramMap.put("limit", Integer.parseInt(paramMap.get("limit").toString()));
        paramMap.put("offset", Integer.parseInt(paramMap.get("offset").toString()));

        logger.info("Fetching agency list with parameters: " + paramMap);
        return agencyDao.getAgencyList(paramMap);
    }

    @Override
    public int getAgencyCnt(Map<String, Object> paramMap) throws Exception {
        return agencyDao.getAgencyCnt(paramMap);
    }

    @Override
    public AgencyDto selectAgency(Map<String, Object> paramMap) throws Exception {
        return agencyDao.selectAgency(paramMap);
    }

    @Override
    public int insertAgency(Map<String, Object> paramMap) throws Exception {
        logger.info("Starting for insertAgency");
        int result = agencyDao.insertAgency(paramMap);
        logger.info("Insert result: " + result);
        return result;
    }

    @Override
    public int updateAgency(Map<String, Object> paramMap) throws Exception {
        logger.info("Starting for updateAgency");
        int result = agencyDao.updateAgency(paramMap);
        logger.info("Update result: " + result);
        return result;
    }

    @Override
    public int updateAgencyYN(Map<String, Object> paramMap) throws Exception {
        logger.info("Starting for updateAgency IS_USED Y/N");
        int result = agencyDao.updateAgencyYN(paramMap);
        logger.info("Update Y/N result: " + result);
        return result;
    }

    @Override
    public List<CountryDto> getCountries(Map<String, Object> paramMap) throws Exception {
        logger.info("Selecting Countries from DAO");
        return agencyDao.getCountries(paramMap);
    }
}
