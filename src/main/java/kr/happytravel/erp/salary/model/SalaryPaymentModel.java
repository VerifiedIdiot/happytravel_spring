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
public class SalaryPaymentModel {
	private String empId; // EMP_ID
	private String empName; // EMP_NAME
	private String deptCode; // DEPT_CODE
	private String deptName; // DEPT_NAME
	private String posCode; // POS_CODE
	private String posName; // POS_NAME
	private int month01; // MONTH01
	private int month02; // MONTH02
	private int month03; // MONTH03
	private int month04; // MONTH04
	private int month05; // MONTH05
	private int month06; // MONTH06
	private int month07; // MONTH07
	private int month08; // MONTH08
	private int month09; // MONTH09
	private int month10; // MONTH10
	private int month11; // MONTH11
	private int month12; // MONTH12
	private int severancePay; // SEVERANCE_PAY
}
