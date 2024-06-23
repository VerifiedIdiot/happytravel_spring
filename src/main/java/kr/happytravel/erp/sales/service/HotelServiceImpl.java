package kr.happytravel.erp.sales.service;

import kr.happytravel.erp.sales.dao.HotelDao;
import kr.happytravel.erp.sales.dto.CountryDto;
import kr.happytravel.erp.sales.dto.HotelDto;
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
    public List<HotelDto> getHotelList(Map<String, Object> paramMap) throws Exception {
        // limit와 offset를 정수형으로 변환
        paramMap.put("limit", Integer.parseInt(paramMap.get("limit").toString()));
        paramMap.put("offset", Integer.parseInt(paramMap.get("offset").toString()));

        return hotelDao.getHotelList(paramMap);
    }

    @Override
    public int getHotelCnt(Map<String, Object> paramMap) throws Exception {
        return hotelDao.getHotelCnt(paramMap);
    }

    @Override
    public String getLastHotelCode() throws Exception {
        return hotelDao.getLastHotelCode();
    }

    @Override
    public HotelDto selectHotel(Map<String, Object> paramMap) throws Exception {
        return hotelDao.selectHotel(paramMap);
    }

    @Override
    public int insertHotel(Map<String, Object> paramMap) throws Exception {
        logger.info("Starting for insert Hotel" + paramMap);
        int result = hotelDao.insertHotel(paramMap);
        logger.info("Insert result: " + result);
        return result;
    }

    @Override
    public int updateHotel(Map<String, Object> paramMap) throws Exception {
        logger.info("Starting for update Hotel");


        logger.info("Update parameters: {}", paramMap);
        int result = hotelDao.updateHotel(paramMap);
        logger.info("Update result: {}", result);

        if (result == 0) {
            logger.warn("No rows were updated. Please check the conditions and parameters.");
        }
        return result;
    }

    @Override
    public int updateHotelYN(Map<String, Object> paramMap) throws Exception {
        logger.info("Starting for updateHotel IS_USED Y/N");
        int result = hotelDao.updateHotelYN(paramMap);
        logger.info("Update Y/N result: " + result);
        return result;
    }

    @Override
    public List<CountryDto> getCountries(Map<String, Object> paramMap) throws Exception {
        logger.info("Selecting Countries from DAO");
        return hotelDao.getCountries(paramMap);
    }
}