package kr.happytravel.erp.attendances.dao;



import kr.happytravel.erp.attendances.model.AttendanceConfirmResponse;
import kr.happytravel.erp.attendances.model.AttendanceManageResponse;
import kr.happytravel.erp.attendances.model.AttendanceManagementModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AttendanceManagementDao {
    // 전체 조회
    List<AttendanceManageResponse> getAttendanceManagementList(@Param("deptCode") String deptCode) throws Exception;

    /**
     * 근태 상태가 대기인 근태코드를 결재완료로 변경
     * @param AttendanceCode
     * @throws Exception
     */
    void updateAssignCodeToApproved(String AttendanceCode) throws Exception;

    /**
     * 근태 상태가 대기인 근태코드를 반려로 변경
     * @param AttendanceCode
     * @throws Exception
     */
    void updateAssignCodeToRejected(String AttendanceCode) throws Exception;

    List<AttendanceConfirmResponse> getAttendanceConfirmList(@Param("deptCode") String deptCode, @Param("limit") int limit, @Param("offset") int offset) throws Exception;

    int getAttendanceConfirmListTotalCount(@Param("deptCode") String deptCode) throws Exception;
    // 단건 조회
    AttendanceManagementModel selectAttendanceManagement(Map<String, Object> paramMap) throws Exception;

    // 단건 등록
    int insertAttendanceManagement(AttendanceManagementModel attendanceManagement) throws Exception;

    // 단건 수정
    int updateAttendanceManagement(AttendanceManagementModel attendanceManagement) throws Exception;

    // 단건 삭제
    int deleteAttendanceManagement(Map<String, Object> paramMap) throws Exception;

    // 작성 날짜 조회
    String getMaxAttendanceTypeCode(@Param("creationDate") String creationDate) throws Exception;

    // 부장 조회
    String getManagerIdByDeptCode(@Param("deptCode") String deptCode) throws Exception;

    int countAttendanceThisMonth(@Param("empId") String empId) throws Exception;
}
