package kr.happytravel.erp.sales.dao;

import kr.happytravel.erp.sales.model.sales.AgencyModel;
import java.util.List;
import java.util.Map;

public interface AgencyDao {

    // 에이전시 전체 조회
    List<AgencyModel> getAgencyList(Map<String, Object> paramMap) throws Exception;

    // 에이전시 전체 조회 카운트
    int getAgencyCnt(Map<String, Object> paramMap) throws Exception;

    // 에이전시 단건 조회
    AgencyModel selectAgency(Map<String, Object> paramMap) throws Exception;

    // 에이전시 단건 등록
    int insertAgency(Map<String, Object> paramMap) throws Exception;

    // 에이전시 단건 수정
    int updateAgency(Map<String, Object> paramMap) throws Exception;

    // 에이전시 단건 삭제
    int updateAgencyYN(Map<String, Object> paramMap) throws Exception;

}
