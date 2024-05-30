package kr.happytravel.erp.sales.service;

import kr.happytravel.erp.sales.dao.HotelDao;

import kr.happytravel.erp.sales.model.sales.HotelModel;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final Logger logger = LogManager.getLogger(this.getClass());

    private final HotelDao hotelDao;

    @Override
    public List<HotelModel> getHotelList(Map<String, Object> paramMap) throws Exception {
        return hotelDao.getHotelList(paramMap);
    }

    @Override
    public int getHotelCnt(Map<String, Object> paramMap) throws Exception {
        return hotelDao.getHotelCnt(paramMap);
    }

    @Override
    public HotelModel selectHotel(Map<String, Object> paramMap) throws Exception {
        return hotelDao.selectHotel(paramMap);
    }

    @Override
    public int insertHotel(Map<String, Object> paramMap) throws Exception {
        logger.info("Starting transaction for insertHotel");
        int result = hotelDao.insertHotel(paramMap);
        logger.info("Insert result: " + result);
        return result;
    }

    @Override
    public int updateHotel(Map<String, Object> paramMap) throws Exception {
        logger.info("Starting for updateHotel");
        int result = hotelDao.updateHotel(paramMap);
        logger.info("Update result: " + result);
        return result;
    }

    @Override
    public int updateHotelYN(Map<String, Object> paramMap) throws Exception {
        logger.info("Starting for updateHotel IS_USED Y/N");
        int result = hotelDao.updateHotelYN(paramMap);
        logger.info("Update Y/N result: " + result);
        return result;
    }
}
