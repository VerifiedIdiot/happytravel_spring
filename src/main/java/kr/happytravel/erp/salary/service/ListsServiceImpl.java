package kr.happytravel.erp.salary.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.happytravel.erp.salary.dao.SalaryDao;
import kr.happytravel.erp.salary.model.EmploymentModel;
import kr.happytravel.erp.salary.model.SalaryDataModel;
import kr.happytravel.erp.salary.model.SalaryItemModel;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListsServiceImpl implements ListsService {
	private final SalaryDao salaryDao;

	@Override
	public List<SalaryItemModel> selectAllSalaryItem() throws Exception {
		return salaryDao.selectAllSalaryItem();
	}

	@Override
	public List<EmploymentModel> selectAllEmployment(String salaryDate) throws Exception {
		return salaryDao.selectAllEmployment(salaryDate);
	}

	@Override
	public List<SalaryDataModel> selectAllTotalSalaryData(String salaryDate) throws Exception {
		boolean totalSalaryDataExists = salaryDao.checkIfTotalSalaryDataExists(salaryDate);
		
		if (totalSalaryDataExists) {
			return salaryDao.selectAllTotalSalaryData(salaryDate);
		} else {
			return salaryDao.selectDefaultTotalSalaryData();
		}
	}
}