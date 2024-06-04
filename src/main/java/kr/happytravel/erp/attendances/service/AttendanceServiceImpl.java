package kr.happytravel.erp.attendances.service;

import kr.happytravel.erp.attendances.dao.AttendanceDao;
import kr.happytravel.erp.attendances.model.AttendanceModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {


    private final AttendanceDao attendanceDao;

    @Override
    public List<AttendanceModel> getAttendanceList(Map<String, Object> paramMap) throws Exception {
        return attendanceDao.getAttendanceList(paramMap);
    }


    @Override
    public AttendanceModel selectAttendance(Map<String, Object> paramMap) throws Exception {
        return attendanceDao.selectAttendance(paramMap);
    }

    @Override
    public int insertAttendance(AttendanceModel attendance) throws Exception {
        return attendanceDao.insertAttendance(attendance);
    }

    @Override
    public int updateAttendance(AttendanceModel attendance) throws Exception {
        return attendanceDao.updateAttendance(attendance);
    }

    @Override
    public int deleteAttendance(Map<String, Object> paramMap) throws Exception {
        return attendanceDao.deleteAttendance(paramMap);
    }
}
