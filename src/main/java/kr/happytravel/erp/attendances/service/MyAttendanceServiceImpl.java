package kr.happytravel.erp.attendances.service;

import kr.happytravel.erp.attendances.dao.MyAttendanceDao;
import kr.happytravel.erp.attendances.model.AttendanceManagementModel;
import kr.happytravel.erp.attendances.model.MyAttendanceResponseModel;
import kr.happytravel.erp.attendances.model.MyVacationResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyAttendanceServiceImpl implements MyAttendanceService {
    private final MyAttendanceDao myAttendanceDao;

    @Override
    public List<MyAttendanceResponseModel> getMyAttendanceList(String empId) {
        return myAttendanceDao.getMyAttendanceList(empId);
    }

    @Override
    public List<MyVacationResponseModel> getMyVacationList(String empId) {
        return myAttendanceDao.getMyVacationList(empId);
    }

    @Override
    public int insertAttendanceManagement(AttendanceManagementModel attendanceManagement) throws Exception {
        return myAttendanceDao.insertAttendanceManagement(attendanceManagement);
    }

}
