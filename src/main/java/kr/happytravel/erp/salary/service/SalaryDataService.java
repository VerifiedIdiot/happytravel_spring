package kr.happytravel.erp.salary.service;

public interface SalaryDataService {
	// 급여 기본 데이터
	int insertInitSalaryData(String empId, int salary) throws Exception;
}
