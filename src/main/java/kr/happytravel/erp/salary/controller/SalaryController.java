
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.happytravel.erp.salary.model.SalaryItemModel;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/salary")
@RequiredArgsConstructor
public class SalaryController {
	private final Logger logger = LogManager.getLogger(this.getClass());
	private final SalaryItemService salaryItemService;

	// 급여 항목, 공제 항목 조회
	@GetMapping("/items")
	public ResponseEntity<List<SalaryItemModel>> SalaryItemList(@RequestParam Map<String, Object> paramMap,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		try {
			logger.info("Received request read salary items");
			logger.info("Received request with parameters: " + paramMap);
			List<SalaryItemModel> salaryitemList = salaryItemService.getSalaryItemList(paramMap);
			logger.info("Detched " + salaryitemList.size() + " SalaryItemList.");
			return ResponseEntity.ok(salaryitemList);
		} catch (IllegalArgumentException e) {
			logger.warn("Invalid argument: " + e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.error("An error occurred: " + e.getMessage(), e);
			throw e;
		}
	}
}