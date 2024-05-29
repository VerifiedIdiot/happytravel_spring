package kr.happytravel.erp.sales.service;

import kr.happytravel.erp.sales.model.sales.FlightModel;

import java.util.List;
import java.util.Map;

public interface FlightService {
    // 항공권 전체 조회
    List<FlightModel> getFlightList(Map<String, Object> paramMap) throws Exception;

    // 항공권 수 조회
    int getFlightCnt(Map<String, Object> paramMap) throws Exception;

    // 항공권 단건 조회
    FlightModel selectFlight(Map<String, Object> paramMap) throws Exception;

    // 항공권 단건 등록
    int insertFlight(FlightModel flight) throws Exception;

    // 항공권 단건 수정
    int updateFlight(FlightModel flight) throws Exception;

    // 항공권 단건 삭제
    int deleteFlight(Map<String, Object> paramMap) throws Exception;
}
