package kr.happytravel.erp.hr.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmpModel {
    private String empId;
    private String password;
    private String empName;
    private String deptCode;
    private String deptName;
    private String posCode;
    private String posName;
    private String joinDate;
    private String ssn;
    private String zipCode;
    private String address;
    private String addressDetail;
    private String phone;
    private String mobile;
    private String statusCode;
    private String leaveDate;
    private String bankCode;
    private String accountNo;
    private String salary;
    private String remaerks;
}
