package kr.happytravel.erp.sales.service;

import kr.happytravel.erp.sales.dao.FlightDao;
import kr.happytravel.erp.sales.model.sales.FlightModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightDao flightDao;

    @Override
    public List<FlightModel> getFlightList(Map<String, Object> paramMap) throws Exception {
        return flightDao.getFlightList(paramMap);
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
    public int insertFlight(FlightModel flight) throws Exception {
        return flightDao.insertFlight(flight);
    }

    @Override
    public int updateFlight(FlightModel flight) throws Exception {
        return flightDao.updateFlight(flight);
    }

    @Override
    public int deleteFlight(Map<String, Object> paramMap) throws Exception {
        return flightDao.deleteFlight(paramMap);
    }
}
