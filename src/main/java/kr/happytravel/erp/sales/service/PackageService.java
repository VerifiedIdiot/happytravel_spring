package kr.happytravel.erp.sales.service;

import kr.happytravel.erp.sales.model.sales.packages.PackageListDTO;
import kr.happytravel.erp.sales.model.sales.packages.PackageModel;

import java.util.List;
import java.util.Map;

public interface PackageService {
    // 전체 조회
    List<PackageListDTO> getPackageList(Map<String, Object> paramMap) throws Exception;

    // 전체 조회 카운트
    int getPackageCnt(Map<String, Object> paramMap) throws Exception;

    // 단건 조회
    PackageModel selectPackage(Map<String, Object> paramMap) throws Exception;

    // 단건 등록
    int insertPackage(Map<String, Object> paramMap) throws Exception;

    // 단건 수정
    int updatePackage(Map<String, Object> paramMap) throws Exception;

    // 단건 삭제
    int updatePackageYN(Map<String, Object> paramMap) throws Exception;

    // 패키지  assign 승인 or 반려, default 대기중 1000
    int assignPackage(Map<String, Object> paramMap) throws Exception;

}
