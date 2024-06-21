package kr.happytravel.erp.attendances.service;

import kr.happytravel.erp.attendances.model.AttendanceConfirmResponse;
import kr.happytravel.erp.attendances.model.AttendanceConfirmResponseDto;
import kr.happytravel.erp.attendances.model.AttendanceManageResponse;
import kr.happytravel.erp.attendances.model.AttendanceManagementModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AttendanceManagementService {
    // 전체 조회
    List<AttendanceManageResponse> getAttendanceManagementList(String deptCode) throws Exception;

    // 단건 조회
    AttendanceManagementModel selectAttendanceManagement(Map<String, Object> paramMap) throws Exception;

    void updateAssignCodeToApproved(String AttendanceCode) throws Exception;

    void updateAssignCodeToRejected(String AttendanceCode) throws Exception;

    AttendanceConfirmResponseDto getAttendanceConfirmList(String deptCode, int limit, int offset) throws Exception;

    // 단건 수정
    int updateAttendanceManagement(AttendanceManagementModel attendanceManagement) throws Exception;

    // 단건 삭제
    int deleteAttendanceManagement(Map<String, Object> paramMap) throws Exception;

    // 날짜 조회
    String getMaxAttendanceTypeCode(String creationDate) throws Exception;

    // 부장 조회
    String getManagerIdByDeptCode(String deptCode) throws Exception;

    int countAttendanceThisMonth(String empId) throws Exception;
}
