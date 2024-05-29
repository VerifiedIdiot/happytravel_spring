package kr.happytravel.erp.sales.service;

import kr.happytravel.erp.sales.model.sales.PackageModel;

import java.util.List;
import java.util.Map;

public interface PackageService {
    // 전체 조회
    List<PackageModel> getPackageList(Map<String, Object> paramMap) throws Exception;

    // 전체 조회 카운트
    int getPackageCnt(Map<String, Object> paramMap) throws Exception;

    // 단건 조회
    PackageModel selectPackage(Map<String, Object> paramMap) throws Exception;

    // 단건 등록
    int insertPackage(Map<String, Object> paramMap) throws Exception;

    // 단건 수정
    int updatePackage(Map<String, Object> paramMap) throws Exception;

    // 단건 삭제
    int deletePackage(Map<String, Object> paramMap) throws Exception;
}
