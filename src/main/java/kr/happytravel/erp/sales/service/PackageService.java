package kr.happytravel.erp.sales.service;

import kr.happytravel.erp.sales.model.sales.PackageReqModel;
import kr.happytravel.erp.sales.model.sales.PackageResModel;

import java.util.List;
import java.util.Map;

public interface PackageService {
    // 전체 조회
    List<PackageResModel> getPackageList(Map<String, Object> paramMap) throws Exception;

    // 전체 조회 카운트
    int getPackageCnt(Map<String, Object> paramMap) throws Exception;

    // 단건 조회
    PackageResModel selectPackage(Map<String, Object> paramMap) throws Exception;

    // 단건 등록
    int insertPackage(PackageReqModel packageReqModel) throws Exception;

    // 단건 수정
    int updatePackage(PackageReqModel packageReqModel) throws Exception;

    // 단건 삭제
    int updatePackageYN(PackageReqModel packageReqModel) throws Exception;

    // 패키지  assign 승인 or 반려, default 대기중 1000
    int assignPackage(PackageReqModel packageReqModel) throws Exception;

}
