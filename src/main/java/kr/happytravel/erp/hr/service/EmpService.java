package kr.happytravel.erp.hr.service;


import kr.happytravel.erp.hr.model.EmpModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmpService {
    /** 사원 전체 조회 */
    List<EmpModel> totalEmpList() throws Exception;

    /** 사원 단건 조회 */
    EmpModel getEmpInfo(String empId) throws Exception;

    /** 사원 검색 조회 */
    List<EmpModel> searchEmpList(String searchType,String searchQuery) throws Exception;

    /** 부서 리스트 조회 */
    List<EmpModel> getDeptName() throws Exception;

    /** 직급 리스트 조회 */
    List<EmpModel> getPosList() throws Exception;

    /** 재직상태 리스트 조회 */
    List<EmpModel> getEmpStatusList() throws Exception;

    /** 은행 리스트 조회 */
    List<EmpModel> getBankList() throws Exception;

    /** 비밀번호 유효성 검사 */
    boolean isValidPassword(String password);


    /** 신규 사원 등록 */
    // 마지막 사원번호 조회
    String generateNewEmpId() throws Exception;

    // 사원 정보 중복 체크
    boolean checkDuplicate(String field, String value) throws Exception;

    /** 신규 사원 등록 */
    void saveEmp(EmpModel saveEmpInfo) throws Exception;

    /** 사원 정보 수정 */
    void updateEmp(EmpModel updateEmpInfo) throws Exception;

    /** 파일 처리 */
    String handleFileUpload(MultipartFile file);
}
