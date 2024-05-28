package kr.happytravel.erp.salary.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.happytravel.erp.salary.dao.SalaryItemDao;
import kr.happytravel.erp.salary.model.SalaryItemModel;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalaryItemServiceImpl implements SalaryItemService {
	private final SalaryItemDao salaryItemDao;

	@Override
	public List<SalaryItemModel> getSalaryItemList(Map<String, Object> paramMap) throws Exception {
		return salaryItemDao.getSalaryItemList(paramMap);
	}
}
