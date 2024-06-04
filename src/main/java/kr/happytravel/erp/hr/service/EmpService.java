package kr.happytravel.erp.hr.service;


import kr.happytravel.erp.hr.model.EmpModel;

import java.util.List;

public interface EmpService {
    List<EmpModel> totalemplist() throws Exception;

    List<EmpModel> searchemplist() throws Exception;

    List<EmpModel> insertemp() throws Exception;

    List<EmpModel> updateemp() throws Exception;
}
