package kr.happytravel.erp.hr.dao;

import kr.happytravel.erp.hr.model.EmpModel;

import java.util.List;

public interface EmpDao {

    List<EmpModel> totalemplist() throws Exception;

    List<EmpModel> searchemplist() throws Exception;

    List<EmpModel> insertemp() throws Exception;

    List<EmpModel> updateemp() throws Exception;
}
