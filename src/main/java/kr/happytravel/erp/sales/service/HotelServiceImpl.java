package kr.happytravel.erp.sales.service;

import kr.happytravel.erp.sales.dao.HotelDao;
import kr.happytravel.erp.sales.model.sales.HotelModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {


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
    public int insertHotel(HotelModel hotel) throws Exception {
        return hotelDao.insertHotel(hotel);
    }

    @Override
    public int updateHotel(HotelModel hotel) throws Exception {
        return hotelDao.updateHotel(hotel);
    }

    @Override
    public int deleteHotel(Map<String, Object> paramMap) throws Exception {
        return hotelDao.deleteHotel(paramMap);
    }
}
