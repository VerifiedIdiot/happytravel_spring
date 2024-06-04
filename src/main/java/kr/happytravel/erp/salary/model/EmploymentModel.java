package kr.happytravel.erp.salary.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmploymentModel {
	private String empId; // EMP_ID
	private String empName; // EMP_NAME
	private String deptCode; // DEPT_CODE
	private String deptName; // DEPT_NAME
	private String posCode; // POS_CODE
	private String posName; // POS_NAME
	private boolean leaveStat; // LEAVE_STAT
}
