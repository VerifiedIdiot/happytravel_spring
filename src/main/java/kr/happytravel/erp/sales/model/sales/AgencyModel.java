package kr.happytravel.erp.sales.model.sales;

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
public class AgencyModel {
    private String agencyCode; // AGENCY_CODE
    private String agencyNmae; // AGENCY_NAME
    private String phone; // PHONE
    private String country; // COUNTRY
    private String region; // REGION
    private String address; // ADDRESS
    private int price; // PRICE
    private String isUsed; // IS_USED
}
