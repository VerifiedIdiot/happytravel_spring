package kr.happytravel.erp.salary.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.happytravel.erp.salary.model.EmploymentModel;
import kr.happytravel.erp.salary.model.SalaryDataModel;
import kr.happytravel.erp.salary.model.SalaryItemModel;
import kr.happytravel.erp.salary.model.SalaryPaymentDetailModel;
import kr.happytravel.erp.salary.model.SalaryPaymentModel;
import kr.happytravel.erp.salary.service.ListsService;
import kr.happytravel.erp.salary.service.SalaryDataService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/salary")
@RequiredArgsConstructor
public class SalaryController {
	private final Logger logger = LogManager.getLogger(this.getClass());
	private final ListsService salaryItemService;
	private final SalaryDataService salaryDataService;

	// 기본 정보(급여 항목, 사원정보, 급여 총계) 조회
	@GetMapping("/lists")
	public ResponseEntity<Map<String, Object>> selectAllLists(@RequestParam String salaryDate) throws Exception {
		try {
			// 로그 메시지로 요청 파라미터 기록
			logger.info("Received request to read lists with parameters: {}", salaryDate);

			// 급여 항목 - 조회
			List<SalaryItemModel> salaryItemModelList = salaryItemService.selectAllSalaryItem();
			// 급여 항목이 없는 경우 로그 메시지 기록 및 204 No Content 반환
			if (salaryItemModelList.isEmpty()) {
				logger.info("No salary item list found.");
				return ResponseEntity.noContent().build();
			}
			logger.info("Fetched {} salary item list.", salaryItemModelList.size());

			// 사원 정보 - 조회
			List<EmploymentModel> employmentModelList = salaryItemService.selectAllEmployment(salaryDate);
			// 사원 정보가 없는 경우 로그 메시지 기록 및 204 No Content 반환
			if (employmentModelList.isEmpty()) {
				logger.info("No employment list found.");
				return ResponseEntity.noContent().build();
			}
			logger.info("Fetched {} employment list.", employmentModelList.size());

			// 급여 총계 - 조회
			List<SalaryDataModel> totalSalaryDataModelList = salaryItemService.selectAllTotalSalaryData(salaryDate);
			// 급여 총계가 없는 경우 로그 메시지 기록 및 204 No Content 반환
			if (totalSalaryDataModelList.isEmpty()) {
				logger.info("No total salary list found.");
				return ResponseEntity.noContent().build();
			}
			logger.info("Fetched {} total salary list.", totalSalaryDataModelList.size());

			// 결과를 Map에 담기
			Map<String, Object> response = new HashMap<String, Object>();
			response.put("salaryItem", salaryItemModelList);
			response.put("employmentInfo", employmentModelList);
			response.put("totalSalaryData", totalSalaryDataModelList);

			return ResponseEntity.ok(response);
		} catch (IllegalArgumentException e) {
			// 잘못된 인자 예외 발생 시 로그 메시지 기록 및 400 Bad Request 반환
			logger.warn("Invalid argument: {}", e.getMessage());
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
			// 기타 예외 발생 시 로그 메시지 기록 및 500 Internal Server Error 반환
			logger.error("An error occurred while fetching total salary list: {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// 급여 내역 - 생성 - 일괄 처리 & 단건 처리
	@PostMapping("/insert/{salaryDate}")
	public ResponseEntity<Boolean> insertSalaryData(@PathVariable String salaryDate, @RequestBody EmploymentModel employmentModel) throws Exception {
		try {
			// 로그 메시지로 요청 파라미터 기록
			logger.info("Received request for salary data insert for employee ID: {} on date: {}", employmentModel.getEmpId(), salaryDate);

			return ResponseEntity.ok(salaryDataService.insertSalaryData(employmentModel.getEmpId(), salaryDate) > 0);
		} catch (IllegalArgumentException e) {
			// 잘못된 인자 예외 발생 시 로그 메시지 기록 및 400 Bad Request 반환
			logger.warn("Invalid argument: {}", e.getMessage());
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
			// 기타 예외 발생 시 로그 메시지 기록 및 500 Internal Server Error 반환
			logger.error("An error occurred while fetching salary data: {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// 월급 내역 - 조회
	@GetMapping("/select/{salaryDate}/{empId}")
	public ResponseEntity<List<SalaryDataModel>> selectAllSalaryData(@PathVariable String salaryDate, @PathVariable String empId) {
		try {
			// 로그 메시지로 요청 파라미터 기록
			logger.info("Received request for salary data select for employee ID: {} on date: {}", empId, salaryDate);

			List<SalaryDataModel> salaryDataModelList = salaryDataService.selectAllSalaryData(empId, salaryDate);
			// 급여 총계가 없는 경우 로그 메시지 기록 및 204 No Content 반환
			if (salaryDataModelList.isEmpty()) {
				logger.info("No total salary list data found.");
				return ResponseEntity.noContent().build();
			}
			logger.info("Fetched {} total salary data list.", salaryDataModelList.size());

			return ResponseEntity.ok(salaryDataModelList);
		} catch (IllegalArgumentException e) {
			// 잘못된 인자 예외 발생 시 로그 메시지 기록 및 400 Bad Request 반환
			logger.warn("Invalid argument: {}", e.getMessage());
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
			// 기타 예외 발생 시 로그 메시지 기록 및 500 Internal Server Error 반환
			logger.error("An error occurred while fetching salary data: {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// 월급 내역 - 수정
	@PatchMapping("/update/{salaryDate}/{empId}")
	public ResponseEntity<Void> updateSalaryData(@PathVariable String salaryDate, @PathVariable String empId, @RequestParam Map<String, Object> paramMap) throws Exception {
		// paramMap -> { "1100": 1000, "1200": 2000, "1300": 1500, "1400": 1200 }
		try {
			// 로그 메시지로 요청 파라미터 기록
			logger.info("Received request for salary data update for employee ID: {} on date: {} with parameters: {}", empId, salaryDate, paramMap);

			List<SalaryDataModel> salaryDataModelList = new ArrayList<SalaryDataModel>();

			// 모든 급여 항목 조회
			List<SalaryItemModel> salaryItemList = salaryItemService.selectAllSalaryItem();
			for (SalaryItemModel salaryItemModel : salaryItemList) {
				String salaryItemCode = salaryItemModel.getSalaryItemCode();
				// 급여 항목 코드로 금액 추출
				int amount = (int) paramMap.get(salaryItemCode);
				salaryDataModelList.add(new SalaryDataModel(empId, salaryDate, salaryItemCode, amount));
			}

			// 급여 데이터 업데이트
			salaryDataService.updateSalaryData(empId, salaryDate, salaryDataModelList);
			logger.info("Successfully updated salary data for employee ID: {} on date: {}", empId, salaryDate);
			return ResponseEntity.noContent().build();
		} catch (IllegalArgumentException e) {
			// 잘못된 인자 예외 발생 시 로그 메시지 기록 및 400 Bad Request 반환
			logger.warn("Invalid argument: {}", e.getMessage());
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
			// 기타 예외 발생 시 로그 메시지 기록 및 500 Internal Server Error 반환
			logger.error("An error occurred while fetching salary data: {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// 급여 지급 내역 - 조회
	@GetMapping("/payment/{salaryYear}")
	public ResponseEntity<List<SalaryPaymentModel>> selectAllSalaryPayment(@PathVariable String salaryYear) throws Exception {
		try {
			// 로그 메시지로 요청 파라미터 기록
			logger.info("Received request for salary payment on year: {}", salaryYear);

			// 급여 지급 내역 - 조회
			List<SalaryPaymentModel> salaryPaymentModelList = salaryDataService.selectAllSalaryPayment(salaryYear);

			// 급여 지급 내역이 없는 경우 로그 메시지 기록 및 204 No Content 반환
			if (salaryPaymentModelList.isEmpty()) {
				logger.info("No total salary payment list found.");
				return ResponseEntity.noContent().build();
			}
			logger.info("Fetched {} salary payment list.", salaryPaymentModelList.size());

			return ResponseEntity.ok(salaryPaymentModelList);
		} catch (IllegalArgumentException e) {
			// 잘못된 인자 예외 발생 시 로그 메시지 기록 및 400 Bad Request 반환
			logger.warn("Invalid argument: {}", e.getMessage());
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
			// 기타 예외 발생 시 로그 메시지 기록 및 500 Internal Server Error 반환
			logger.error("An error occurred while fetching salary payment list: {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// 급여 지급 상세 내역 - 조회
	@GetMapping("/payment/{salaryYear}/{empId}")
	public ResponseEntity<List<SalaryPaymentDetailModel>> selectAllSalaryPaymentDetail(@PathVariable String salaryYear, @PathVariable String empId) throws Exception {
		try {
			// 로그 메시지로 요청 파라미터 기록
			logger.info("Received request for salary payment detail for employee ID: {} on year: {}", empId, salaryYear);

			// 급여 지급 상세 내역 - 조회
			List<SalaryPaymentDetailModel> salaryPaymentDetailModelList = salaryDataService.selectAllSalaryPaymentDetail(empId, salaryYear);

			// 급여 지급 상세 내역이 없는 경우 로그 메시지 기록 및 204 No Content 반환
			if (salaryPaymentDetailModelList.isEmpty()) {
				logger.info("No total salary payment detail list found.");
				return ResponseEntity.noContent().build();
			}
			logger.info("Fetched {} salary payment detail list.", salaryPaymentDetailModelList.size());

			return ResponseEntity.ok(salaryPaymentDetailModelList);
		} catch (IllegalArgumentException e) {
			// 잘못된 인자 예외 발생 시 로그 메시지 기록 및 400 Bad Request 반환
			logger.warn("Invalid argument: {}", e.getMessage());
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
			// 기타 예외 발생 시 로그 메시지 기록 및 500 Internal Server Error 반환
			logger.error("An error occurred while fetching salary payment detail list: {}", e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// 급여 기본 데이터 관련 테스트
	@GetMapping("/test1")
	public void test1(@RequestParam Map<String, Object> paramMap) throws Exception {
		// localhost/salary/test1?emp_id=EMP30001&salary=120000000
		String empId = (String) paramMap.get("emp_id");
		int salary = (int) paramMap.get("salary");
		salaryDataService.initSalaryData(empId, salary);
	}

}