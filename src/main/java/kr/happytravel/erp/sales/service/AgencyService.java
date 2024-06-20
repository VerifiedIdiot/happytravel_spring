package kr.happytravel.erp.sales.service;

import kr.happytravel.erp.sales.dto.AgencyDto;
import kr.happytravel.erp.sales.dto.CountryDto;
import java.util.List;
import java.util.Map;

public interface AgencyService {
    // 현지 전체 조회
    List<AgencyDto> getAgencyList(Map<String, Object> paramMap) throws Exception;

    // 현지 전체 조회 카운트
    int getAgencyCnt(Map<String, Object> paramMap) throws Exception;

    // 현지 단건 등록
    int insertAgency(Map<String, Object> paramMap) throws Exception;

    // 현지 단건 조회
    AgencyDto selectAgency(Map<String, Object> paramMap) throws Exception;

    // 현지 단건 수정
    int updateAgency(Map<String, Object> paramMap) throws Exception;

    // 현지 단건 삭제
    int updateAgencyYN(Map<String, Object> paramMap) throws Exception;

    // 국가별 조회
    List<CountryDto> getCountries (Map<String, Object> paramMap) throws Exception;
}
