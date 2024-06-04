package kr.happytravel.erp.salary.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.happytravel.erp.salary.model.EmploymentModel;
import kr.happytravel.erp.salary.model.SalaryDataModel;
import kr.happytravel.erp.salary.model.SalaryItemModel;
import kr.happytravel.erp.salary.model.SalaryPaymentDetailModel;
import kr.happytravel.erp.salary.model.SalaryPaymentModel;

public interface SalaryDao {
	// 급여 항목 - 조회
	List<SalaryItemModel> selectAllSalaryItem() throws Exception;

	// 사원 정보 - 조회
	List<EmploymentModel> selectAllEmployment(@Param("salaryDate") String salaryDate) throws Exception;

	// 급여 총계 - 조회 - 체크
	boolean checkIfTotalSalaryDataExists(@Param("salaryDate") String salaryDate) throws Exception;

	// 급여 총계 - 조회 - True
	List<SalaryDataModel> selectAllTotalSalaryData(@Param("salaryDate") String salaryDate) throws Exception;

	// 급여 총계 - 조회 - False
	List<SalaryDataModel> selectDefaultTotalSalaryData() throws Exception;

	// 급여 기본 정보 - 체크
	boolean selectInitSalaryData(@Param("empId") String empId) throws Exception;

	// 급여 기본 정보 - 추가
	int insertInitSalaryData(@Param("salaryDataModelList") List<SalaryDataModel> salaryDataModelList) throws Exception;

	// 급여 기본 정보 - 수정
	int updateInitSalaryData(@Param("empId") String empId, @Param("salaryDataModelList") List<SalaryDataModel> salaryDataModelList) throws Exception;

	// 급여 내역 - 체크 - 일괄 처리 & 단건 처리
	boolean existSalaryData(@Param("empId") String empId, @Param("salaryDate") String salaryDate) throws Exception;

	// 급여 내역 - 생성 - 일괄 처리 & 단건 처리
	int insertSalaryData(@Param("empId") String empId, @Param("salaryDate") String salaryDate) throws Exception;

	// 급여 내역 - 조회
	List<SalaryDataModel> selectAllSalaryData(@Param("empId") String empId, @Param("salaryDate") String salaryDate) throws Exception;

	// 급여 내역 - 수정
	int updateSalaryData(@Param("empId") String empId, @Param("salaryDate") String salaryDate, @Param("salaryDataModelList") List<SalaryDataModel> salaryDataModelList) throws Exception;

	// 급여 지급 내역 - 조회
	List<SalaryPaymentModel> selectAllSalaryPayment(@Param("salaryYear") String salaryYear) throws Exception;

	// 급여 지급 상세 내역 - 조회
	List<SalaryPaymentDetailModel> selectAllSalaryPaymentDetail(@Param("empId") String empId, @Param("salaryYear") String salaryYear) throws Exception;
}