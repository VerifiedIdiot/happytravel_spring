package kr.happytravel.erp.hr.service;

import kr.happytravel.erp.common.comnUtils.FileUtil;
import kr.happytravel.erp.hr.dao.EmpDao;
import kr.happytravel.erp.hr.model.EmpModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Year;
import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class EmpServiceImpl implements EmpService {
private final EmpDao empDao;

    @Autowired
    private FileUtil fileUtil;

    /** 전체사원조회 */
    @Override
    public List<EmpModel> totalEmpList() throws Exception {
        return empDao.totalEmpList();
    }

    /** 사원 단건 조회 */
    @Override
    public EmpModel getEmpInfo(String empId) throws Exception {
        return empDao.getEmpInfo(empId);
    }

    /** 사원 검색 조회 */
    @Override
    public List<EmpModel> searchEmpList(String searchType, String searchQuery){
        return empDao.searchEmpList(searchType, searchQuery);
    }

    /** 부서 리스트 조회 */
    @Override
    public List<EmpModel> getDeptName() throws Exception {
        return empDao.getDeptName();
    }

    /** 직급 리스트 조회 */
    @Override
    public List<EmpModel> getPosList() throws Exception {
        return empDao.getPosList();
    }

    /** 재직상태 리스트 조회 */
    @Override
    public List<EmpModel> getEmpStatusList() throws Exception {
        return empDao.getEmpStatusList();
    }

    /** 은행 리스트 조회 */
    @Override
    public List<EmpModel> getBankList() throws Exception {
        return empDao.getBankList();
    }

    /** 비밀번호 수정 유효성 검사 */
    @Override
    public boolean isValidPassword(String password) {
        String regex = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(password).matches();
    }



    /** 신규 사원 등록 */

        // 마지막 사원번호 조회
    @Override
    public String generateNewEmpId() throws Exception {
        String currentYear = String.valueOf(Year.now().getValue());
        String lastEmpId =  empDao.findLastEmpIdByYear(currentYear);

        //마지막 사원번호가 null인경우 (초기상태)
        if(lastEmpId == null) {
            return currentYear+"0001";
        }

        int newEmpId = Integer.parseInt(lastEmpId.substring(4)) +1;
        return currentYear + String.format("%04d", newEmpId);
    }

    /** 사원 정보 중복 체크 */
    @Override
    public boolean checkDuplicate(String field, String value) throws Exception {
        try {
            int count = empDao.checkDuplicate(field, value);
            return count > 0;
        } catch (Exception e) {
            throw new RuntimeException("Error checking duplicate for field: " + field + " with value: " + value, e);
        }
    }

    @Override
    public void saveEmp(EmpModel saveEmpInfo) throws Exception {
        empDao.saveEmp(saveEmpInfo);
    }

    /** 사원 정보 수정 */

    @Override
    public void updateEmp(EmpModel updateEmpInfo) throws Exception {
        empDao.updateEmp(updateEmpInfo);
    }

    /** 파일 처리 */
    @Override
    public String handleFileUpload(MultipartFile file) {
        if(file == null || file.isEmpty()) {
            return null;
        }

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path uploadPath = fileUtil.getUploadPath();

        try{
            if(!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try(InputStream inputStream = file.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new RuntimeException("파일 저장 중 오류 발생: " + fileName, e);
        }
        return fileName;
    }
}
