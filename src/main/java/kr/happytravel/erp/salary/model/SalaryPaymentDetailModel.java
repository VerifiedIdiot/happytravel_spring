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
public class SalaryPaymentDetailModel {
	private String empId; // EMP_ID
	private String salaryMonth; // SALARY_MONTH
	private String item1100; // ITEM1100 - 기본급
	private String item1200; // ITEM1200 - 식대
	private String item1300; // ITEM1300 - 연장근로수당
	private String item1400; // ITEM1400 - 야간근로수당
	private String item1500; // ITEM1500 - 휴일근로수당
	private String item1600; // ITEM1600 - 가족수당
	private String item1700; // ITEM1700 - 직책수당
	private String item2100; // ITEM2100 - 국민연금
	private String item2200; // ITEM2200 - 건강보험
	private String item2300; // ITEM2300 - 장기요양보험료
	private String item2400; // ITEM2400 - 고용보험
	private String item2500; // ITEM2500 - 소득세
	private String item2600; // ITEM2600 - 지방소득세
	private String item8100; // ITEM8100 - 과세
	private String item8200; // ITEM8200 - 비과세
	private String item8300; // ITEM8300 - 감면소득
	private String item8400; // ITEM8400 - 지급액계
	private String item9100; // ITEM9100 - 공제액계
	private String item9200; // ITEM9200 - 차인지급액
}
