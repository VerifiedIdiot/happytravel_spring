package kr.happytravel.erp.attendances.service;

import kr.happytravel.erp.attendances.dao.AttendanceManagementDao;
import kr.happytravel.erp.attendances.model.AttendanceManagementModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
@RequiredArgsConstructor
public class AttendanceManagementServiceImpl implements AttendanceManagementService {


    private final AttendanceManagementDao attendanceManagementDao;

    @Override
    public List<AttendanceManagementModel> getAttendanceManagementList(Map<String, Object> paramMap) throws Exception {
        return attendanceManagementDao.getAttendanceManagementList(paramMap);
    }


    @Override
    public AttendanceManagementModel selectAttendanceManagement(Map<String, Object> paramMap) throws Exception {
        return attendanceManagementDao.selectAttendanceManagement(paramMap);
    }

    @Override
    public int insertAttendanceManagement(AttendanceManagementModel attendanceManagement) throws Exception {
        return attendanceManagementDao.insertAttendanceManagement(attendanceManagement);
    }

    @Override
    public int updateAttendanceManagement(AttendanceManagementModel attendanceManagement) throws Exception {
        return attendanceManagementDao.updateAttendanceManagement(attendanceManagement);
    }

    @Override
    public int deleteAttendanceManagement(Map<String, Object> paramMap) throws Exception {
        return attendanceManagementDao.deleteAttendanceManagement(paramMap);
    }

    @Override
    public String getMaxAttendanceTypeCode(String creationDate) throws Exception {
        return attendanceManagementDao.getMaxAttendanceTypeCode(creationDate);
    }

    @Override
    public String getManagerIdByDeptCode(String deptCode) throws Exception {
        return attendanceManagementDao.getManagerIdByDeptCode(deptCode);
    }
}
