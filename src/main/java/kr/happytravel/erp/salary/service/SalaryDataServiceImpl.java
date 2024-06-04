package kr.happytravel.erp.salary.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import kr.happytravel.erp.salary.dao.SalaryDao;
import kr.happytravel.erp.salary.model.SalaryDataModel;
import kr.happytravel.erp.salary.model.SalaryItemModel;
import kr.happytravel.erp.salary.model.SalaryPaymentDetailModel;
import kr.happytravel.erp.salary.model.SalaryPaymentModel;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalaryDataServiceImpl implements SalaryDataService {
	private final SalaryDao salaryDao;

	@Override
	public int initSalaryData(String empId, int salary) throws Exception {
		List<SalaryDataModel> salaryDataModelList = new ArrayList<SalaryDataModel>();

		// 급여 항목 조회
		List<SalaryItemModel> salaryItemModelList = salaryDao.selectAllSalaryItem();

		int taxable_amount = 0; // 과세
		int non_taxable_amount = 0; // 비과세
		int total_current_amount = 0; // 지급액 계
		int total_deduction_amount = 0; // 공제액 계
		int net_payment_amount = 0; // 차인지급액
		for (SalaryItemModel salaryItemModel : salaryItemModelList) {
			int amount = 0;
			if ((salaryItemModel.getSalaryItemCode()).equals("1100")) {
				// 기본급: 연봉 / 12
				amount = (int) (salary / 12);
				taxable_amount += amount;
			} else if ((salaryItemModel.getSalaryItemCode()).equals("2100")) {
				// 국민연금: 기본급 * 0.045
				amount = (int) (salary / 12 * 0.045);
				total_deduction_amount += amount;
			} else if ((salaryItemModel.getSalaryItemCode()).equals("2200")) {
				// 건강보험: 기본급 * 0.03545
				amount = (int) (salary / 12 * 0.03545);
				total_deduction_amount += amount;
			} else if ((salaryItemModel.getSalaryItemCode()).equals("2300")) {
				// 장기요양보험료: 기본급 * 0.004591
				amount = (int) (salary / 12 * 0.004591);
				total_deduction_amount += amount;
			} else if ((salaryItemModel.getSalaryItemCode()).equals("2400")) {
				// 고용보험: 기본급 * 0.009
				amount = (int) (salary / 12 * 0.009);
				total_deduction_amount += amount;
			} else if ((salaryItemModel.getSalaryItemCode()).equals("8100")) {
				// 과세: 기본급 + 가족수당 + 직책수당
				amount = taxable_amount;
				total_current_amount += amount;
			} else if ((salaryItemModel.getSalaryItemCode()).equals("8400")) {
				// 지급액 계: 과세 + 비과세 + 감면소득
				amount = total_current_amount;
			} else if ((salaryItemModel.getSalaryItemCode()).equals("9100")) {
				// 공제액 계: 국민연금 + 건강보험 + 장기요양보험료 + 고용보험 + 소득세 + 지방소득세
				amount = total_deduction_amount;
			} else if ((salaryItemModel.getSalaryItemCode()).equals("9200")) {
				// 차인지급액: 지급액 계 - 공제액 계
				amount = total_current_amount - total_deduction_amount;
			}
			salaryDataModelList.add(new SalaryDataModel(empId, "000000", salaryItemModel.getSalaryItemCode(), amount));
		}

		if (salaryDao.selectInitSalaryData(empId)) {
			return salaryDao.updateInitSalaryData(empId, salaryDataModelList);
		} else {
			return salaryDao.insertInitSalaryData(salaryDataModelList);
		}
	}

	@Override
	public int insertSalaryData(String empId, String salaryDate) throws Exception {
		return salaryDao.existSalaryData(empId, salaryDate) ? 1 : salaryDao.insertSalaryData(empId, salaryDate);
	}

	@Override
	public List<SalaryDataModel> selectAllSalaryData(String empId, String salaryDate) throws Exception {
		return salaryDao.selectAllSalaryData(empId, salaryDate);
	}

	@Override
	public int updateSalaryData(String empId, String salaryDate, List<SalaryDataModel> salaryDataModelList) throws Exception {
		return salaryDao.updateSalaryData(empId, salaryDate, salaryDataModelList);
	}

	@Override
	public List<SalaryPaymentModel> selectAllSalaryPayment(String salaryYear) throws Exception {
		return salaryDao.selectAllSalaryPayment(salaryYear);
	}

	@Override
	public List<SalaryPaymentDetailModel> selectAllSalaryPaymentDetail(String empId, String salaryYear) throws Exception {
		return salaryDao.selectAllSalaryPaymentDetail(empId, salaryYear);
	}

}
