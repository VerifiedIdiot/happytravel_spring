package kr.happytravel.erp.salary.dao;

import java.util.List;
import java.util.Map;

import kr.happytravel.erp.salary.model.SalaryItemModel;

public interface SalaryItemDao {
	// 급여 항목, 공제 항목 조회
	List<SalaryItemModel> getSalaryItemList(Map<String, Object> paramMap) throws Exception;
}