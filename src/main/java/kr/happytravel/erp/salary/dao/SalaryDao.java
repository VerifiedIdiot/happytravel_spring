package kr.happytravel.erp.salary.dao;

import java.util.List;

import kr.happytravel.erp.salary.model.SalaryDataModel;
import kr.happytravel.erp.salary.model.SalaryItemModel;

public interface SalaryDao {
	// 급여 항목, 공제 항목 조회
	List<SalaryItemModel> selectAllSalaryItem() throws Exception;

	// 급여 기본 데이터 추가
	int insertInitSalaryData(List<SalaryDataModel> salaryDataModelList) throws Exception;
}