package kr.happytravel.erp.sales.dao;

import kr.happytravel.erp.sales.model.sales.HotelModel;

import java.util.List;
import java.util.Map;

public interface HotelDao {
    //전체조회
    public List<HotelModel> getHotelList(Map<String, Object> paramMap) throws Exception;
    //전체 조회 카운트
    public int getHotelCnt(Map<String, Object> paramMap);

    /** 리스트  단건 조회 */
    public HotelModel selectHotel(Map<String, Object> paramMap);
    /** 단건 저장 */
    public int  insertHotel(HotelModel hotel);
    /** 단건 수정 */
    public int updateHotel(HotelModel hotel);
    /** 단건 삭제 */
    public int deleteHotel(Map<String, Object> paramMap);
}
