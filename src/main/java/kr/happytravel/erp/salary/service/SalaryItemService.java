package kr.happytravel.erp.salary.service;

import java.util.List;
import java.util.Map;

import kr.happytravel.erp.salary.model.SalaryItemModel;

public interface SalaryItemService {
	// 급여 항목, 공제 항목 조회
	List<SalaryItemModel> getSalaryItemList(Map<String, Object> paramMap) throws Exception;
}
