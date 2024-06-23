package kr.happytravel.erp.attendances.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyAttendanceResponseModel {
    private String day;
    private Time inTime;
    private Time outTime;

}
