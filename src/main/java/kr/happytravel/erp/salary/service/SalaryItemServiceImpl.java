package kr.happytravel.erp.salary.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.happytravel.erp.salary.dao.SalaryDao;
import kr.happytravel.erp.salary.model.SalaryItemModel;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalaryItemServiceImpl implements SalaryItemService {
	private final SalaryDao salaryDao;

	@Override
	public List<SalaryItemModel> selectAllSalaryItem() throws Exception {
		return salaryDao.selectAllSalaryItem();
	}
}