package kr.happytravel.erp.salary.service;

import java.util.List;

import kr.happytravel.erp.salary.model.SalaryItemModel;

public interface SalaryItemService {
	// 급여 항목 조회
	List<SalaryItemModel> selectAllSalaryItem() throws Exception;
}