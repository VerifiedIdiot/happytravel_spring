package kr.happytravel.erp.sales.dao;



import kr.happytravel.erp.sales.model.sales.FlightModel;

import java.util.List;
import java.util.Map;

public interface FlightDao {
    //전체조회
    public List<FlightModel> getFlightList(Map<String, Object> paramMap) throws Exception;
    //전체 조회 카운트
    public int getFlightCnt(Map<String, Object> paramMap);

    /** 리스트  단건 조회 */
    public FlightModel selectFlight(Map<String, Object> paramMap);
    /** 단건 저장 */
    public int  insertFlight(FlightModel flight);
    /** 단건 수정 */
    public int updateFlight(FlightModel flight);
    /** 단건 삭제 */
    public int deleteFlight(Map<String, Object> paramMap);
}
