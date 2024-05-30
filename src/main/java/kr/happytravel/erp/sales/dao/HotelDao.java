package kr.happytravel.erp.sales.dao;

import kr.happytravel.erp.sales.model.sales.HotelModel;
import java.util.List;
import java.util.Map;

public interface HotelDao {
    // 호텔 전체 조회
    List<HotelModel> getHotelList(Map<String, Object> paramMap) throws Exception;

    // 호텔 전체 조회 카운트
    public int getHotelCnt( Map<String, Object> paramMap );

    // 호텔 단건 조회
    HotelModel selectHotel(Map<String, Object> paramMap);

    // 호텔 단건 저장
    public int insertHotel(Map<String, Object> paramMap);

    // 호텔 단건 수정
    public int updateHotel(Map<String, Object> paramMap);

    // 호텔 단건 삭제
    int updateHotelYN(Map<String, Object> paramMap) throws Exception;
}
