package kr.happytravel.erp.sales.service;

import kr.happytravel.erp.sales.dao.AgencyDao;
import kr.happytravel.erp.sales.model.sales.AgencyModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AgencyServiceImpl implements AgencyService {


    private final AgencyDao agencyDao;

    @Override
    public List<AgencyModel> getAgencyList(Map<String, Object> paramMap) throws Exception {
        return agencyDao.getAgencyList(paramMap);
    }

    @Override
    public int getAgencyCnt(Map<String, Object> paramMap) throws Exception {
        return agencyDao.getAgencyCnt(paramMap);
    }

    @Override
    public AgencyModel selectAgency(Map<String, Object> paramMap) throws Exception {
        return agencyDao.selectAgency(paramMap);
    }

    @Override
    public int insertAgency(AgencyModel agency) throws Exception {
        return agencyDao.insertAgency(agency);
    }

    @Override
    public int updateAgency(AgencyModel agency) throws Exception {
        return agencyDao.updateAgency(agency);
    }

    @Override
    public int deleteAgency(Map<String, Object> paramMap) throws Exception {
        return agencyDao.deleteAgency(paramMap);
    }
}
