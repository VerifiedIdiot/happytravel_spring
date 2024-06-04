package kr.happytravel.erp.salary.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.happytravel.erp.salary.model.SalaryDataModel;
import kr.happytravel.erp.salary.model.SalaryPaymentDetailModel;
import kr.happytravel.erp.salary.model.SalaryPaymentModel;

public interface SalaryDataService {
	// 급여 기본 정보
	int initSalaryData(String empId, int salary) throws Exception;

	// 급여 내역 - 생성 - 일괄 처리 & 단건 처리
	int insertSalaryData(String empId, String salaryDate) throws Exception;

	// 급여 내역 - 조회
	List<SalaryDataModel> selectAllSalaryData(String empId, String salaryDate) throws Exception;

	// 급여 내역 - 수정
	int updateSalaryData(String empId, String salaryDate, List<SalaryDataModel> salaryDataModelList) throws Exception;

	// 급여 지급 내역 - 조회
	List<SalaryPaymentModel> selectAllSalaryPayment(String salaryYeㄴar) throws Exception;

	// 급여 지급 상세 내역 - 조회
	List<SalaryPaymentDetailModel> selectAllSalaryPaymentDetail(String empId, String salaryYear) throws Exception;
}