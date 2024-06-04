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
public class SalaryItemModel {
	private String salaryItemCode; // SALARY ITEM_CODE
	private String salaryItemName; // SALARY_ITEM_NAME
	private String salaryItemtype; // SALARY_ITEM_TYPE
	private String salaryItemTax; // SALARY_ITEM_TAX
	private String isUsed; // IS_USED
}