package kr.happytravel.erp.sales.dao;

import kr.happytravel.erp.sales.model.sales.PackageReqModel;
import kr.happytravel.erp.sales.model.sales.PackageResModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface PackageDao {
    // 전체 조회
    List<PackageResModel> getPackageList(Map<String, Object> paramMap) throws Exception;

    // 전체 조회 카운트
    int getPackageCnt(Map<String, Object> paramMap) throws Exception;

    // 단건 조회
    PackageResModel selectPackage(Map<String, Object> paramMap) throws Exception;

    // 단건 등록
    int insertPackage(@Param("params") PackageReqModel packageReqModel) throws Exception;

    // 단건 수정
    int updatePackage(@Param("params") PackageReqModel packageReqModel) throws Exception;

    // 단건 삭제
    int updatePackageYN(PackageReqModel packageReqModel) throws Exception;

    // 패키지 승인
    int assignPackage(PackageReqModel packageReqModel) throws Exception;
}
