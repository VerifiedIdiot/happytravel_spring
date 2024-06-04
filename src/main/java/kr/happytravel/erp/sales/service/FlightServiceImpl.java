package kr.happytravel.erp.sales.service;

import kr.happytravel.erp.sales.dao.FlightDao;
import kr.happytravel.erp.sales.model.sales.FlightModel;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private final FlightDao flightDao;

    @Override
    public List<FlightModel> getFlightList(Map<String, Object> paramMap) throws Exception {
        logger.info("Fetching flight list with parameters: " + paramMap);
        List<FlightModel> flightList = flightDao.getFlightList(paramMap);
        logger.info("Fetched flight list: " + flightList);
        return flightList;
    }

    @Override
    public int getFlightCnt(Map<String, Object> paramMap) throws Exception {
        return flightDao.getFlightCnt(paramMap);
    }

    @Override
    public FlightModel selectFlight(Map<String, Object> paramMap) throws Exception {
        return flightDao.selectFlight(paramMap);
    }

    @Override
    public int insertFlight(Map<String, Object> paramMap) throws Exception {
        logger.info("Starting for insertFlight");
        int result = flightDao.insertFlight(paramMap);
        logger.info("Insert result: " + result);
        return result;
    }

    @Override
    public int updateFlight(Map<String, Object> paramMap) throws Exception {
        logger.info("Starting for updateFlight");
        int result = flightDao.updateFlight(paramMap);
        logger.info("Update result: " + result);
        return result;
    }
    @Override
    public int updateFlightYN(Map<String, Object> paramMap) throws Exception {
        logger.info("Starting for updateFlight IS_USED Y/N");
        int result = flightDao.updateFlightYN(paramMap);
        logger.info("Update Y/N result: " + result);
        return result;
    }
}
