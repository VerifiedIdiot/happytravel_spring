package kr.happytravel.erp.salary.service;

import java.util.List;

import kr.happytravel.erp.salary.model.EmploymentModel;
import kr.happytravel.erp.salary.model.SalaryDataModel;
import kr.happytravel.erp.salary.model.SalaryItemModel;

public interface ListsService {
	// 급여 항목 - 조회
	List<SalaryItemModel> selectAllSalaryItem() throws Exception;

	// 사원 정보 - 조회
	List<EmploymentModel> selectAllEmployment(String salaryDate) throws Exception;

	// 급여 총계 - 조회
	List<SalaryDataModel> selectAllTotalSalaryData(String salaryDate) throws Exception;
}