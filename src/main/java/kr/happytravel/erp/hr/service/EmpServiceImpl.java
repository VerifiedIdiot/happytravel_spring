package kr.happytravel.erp.hr.service;

import kr.happytravel.erp.hr.dao.EmpDao;
import kr.happytravel.erp.hr.model.EmpModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpServiceImpl implements EmpService {
private final EmpDao empDao;

    @Override
    public List<EmpModel> totalemplist() throws Exception {
        return empDao.totalemplist();
    }
    @Override
    public List<EmpModel> searchemplist() throws Exception {
        return empDao.searchemplist();
    }
    @Override
    public List<EmpModel> insertemp() throws Exception {
        return empDao.insertemp();
    }
    @Override
    public List<EmpModel> updateemp() throws Exception {
        return empDao.updateemp();
    }
}
