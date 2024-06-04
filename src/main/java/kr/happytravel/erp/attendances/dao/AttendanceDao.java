package kr.happytravel.erp.attendances.dao;

import kr.happytravel.erp.attendances.model.AttendanceModel;

import java.util.List;
import java.util.Map;

public interface AttendanceDao {
    // 전체 조회
    List<AttendanceModel> getAttendanceList(Map<String, Object> paramMap) throws Exception;

    // 단건 조회
    AttendanceModel selectAttendance(Map<String, Object> paramMap) throws Exception;

    // 단건 등록
    int insertAttendance(AttendanceModel attendance) throws Exception;

    // 단건 수정
    int updateAttendance(AttendanceModel attendance) throws Exception;

    // 단건 삭제
    int deleteAttendance(Map<String, Object> paramMap) throws Exception;
}
