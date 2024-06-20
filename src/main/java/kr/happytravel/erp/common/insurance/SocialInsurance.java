package kr.happytravel.erp.common.insurance;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kr.happytravel.erp.salary.model.SalaryCalculateModel;
import kr.happytravel.erp.salary.model.SalaryDataModel;
import kr.happytravel.erp.salary.model.SalaryItemModel;

public class SocialInsurance {
	private final Logger logger = LogManager.getLogger(this.getClass());

	public List<SalaryDataModel> socialInsuranceCalculate(List<SalaryItemModel> salaryItemModelList, String empId, String salaryDate, SalaryCalculateModel salaryCalculateModel) {
		// 로그 메시지로 요청 파라미터 기록
		logger.info("Received request for salary payment detail for employee ID: {} on year: {}. Item count: {}. Calculation model: {}", empId, salaryDate, salaryItemModelList.size(),
				salaryCalculateModel.toString());

		List<SalaryDataModel> salaryDataModelList = new ArrayList<SalaryDataModel>();

		int taxableIncome = 0; // 과세
		int nonTaxableIncome = 0; // 비과세
		int reducedIncome = 0; // 감면소득
		int totalPayment = 0; // 지급액 계
		int totalDeductions = 0; // 공제액 계
		int netPayment = 0; // 차인지급액

		for (SalaryItemModel salaryItemModel : salaryItemModelList) {
			int amount = 0;
			switch (salaryItemModel.getSalaryItemCode()) {
			case "1100":
				// 기본급
				amount = salaryCalculateModel.getBaseSalary();
				taxableIncome += amount;
				break;
			case "1200":
				// 식대
				amount = salaryCalculateModel.getMealAllowance();
				nonTaxableIncome += amount;
				break;
			case "1300":
				// 연장근로수당
				amount = salaryCalculateModel.getOvertimeAllowance();
				nonTaxableIncome += amount;
				break;
			case "1400":
				// 야간근로수당
				amount = salaryCalculateModel.getNightWorkAllowance();
				nonTaxableIncome += amount;
				break;
			case "1500":
				// 휴일근로수당
				amount = salaryCalculateModel.getHolidayWorkAllowance();
				nonTaxableIncome += amount;
				break;
			case "1600":
				// 가족수당
				amount = salaryCalculateModel.getFamilyAllowance();
				taxableIncome += amount;
				break;
			case "1700":
				// 직책수당
				amount = salaryCalculateModel.getPositionAllowance();
				taxableIncome += amount;
				break;
			case "2100":
				// 국민연금: 과세 * 0.045(9 * 0.01 * 0.5)
				amount = new BigDecimal(taxableIncome * 0.045).setScale(0, RoundingMode.CEILING).intValue();
				totalDeductions += amount;
				break;
			case "2200":
				// 건강보험: 과세 * 0.03545(7.09 * 0.01 * 0.5)
				amount = new BigDecimal(taxableIncome * 0.03545).setScale(0, RoundingMode.CEILING).intValue();
				totalDeductions += amount;
				break;
			case "2300":
				// 장기요양보험료: 과세 * 0.004591(0.9182(건강보험 * 2 * 12.95) * 0.01 * 0.5)
				amount = new BigDecimal(taxableIncome * 0.004591).setScale(0, RoundingMode.CEILING).intValue();
				totalDeductions += amount;
				break;
			case "2400":
				// 고용보험: 과세 * 0.009(1.8 * 0.01 * 0.5)
				amount = new BigDecimal(taxableIncome * 0.009).setScale(0, RoundingMode.CEILING).intValue();
				totalDeductions += amount;
				break;
			case "2500":
				// 소득세
				amount = 0;
				totalDeductions += amount;
				break;
			case "2600":
				// 지방소득세
				amount = 0;
				totalDeductions += amount;
				break;
			case "8100":
				// 과세: 기본급 + 가족수당 + 직책수당
				amount = taxableIncome;
				totalPayment += amount;
				break;
			case "8200":
				// 비과세: 식대 + 연장근로수당 + 야간근로수당 + 휴일근로수당
				amount = nonTaxableIncome;
				totalPayment += amount;
				break;
			case "8300":
				// 감면소득
				amount = reducedIncome;
				totalPayment += amount;
				break;
			case "8400":
				// 지급액계: 과세 + 비과세 + 감면소득
				amount = totalPayment;
				netPayment += amount;
				break;
			case "9100":
				// 공제액계: 국민연금 + 건강보험 + 장기요양보험료 + 고용보험 + 소득세 + 지방소득세
				amount = totalDeductions;
				netPayment -= amount;
				break;
			case "9200":
				// 차인지급액: 지급액계 - 공제액계
				amount = netPayment;
				break;
			default:
				break;
			}
			salaryDataModelList.add(new SalaryDataModel(empId, salaryDate, salaryItemModel.getSalaryItemCode(), amount));
		}
		return salaryDataModelList;
	}
}
