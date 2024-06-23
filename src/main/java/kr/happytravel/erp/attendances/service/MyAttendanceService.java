package kr.happytravel.erp.attendances.service;

import kr.happytravel.erp.attendances.model.AttendanceManagementModel;
import kr.happytravel.erp.attendances.model.MyAttendanceResponseModel;
import kr.happytravel.erp.attendances.model.MyVacationResponseModel;

import java.util.List;

public interface MyAttendanceService {
    List<MyAttendanceResponseModel> getMyAttendanceList(String empId);
    List<MyVacationResponseModel> getMyVacationList(String empId);
    int insertAttendanceManagement(AttendanceManagementModel attendanceManagement) throws Exception;
}
