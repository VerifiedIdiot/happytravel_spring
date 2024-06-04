package kr.happytravel.erp.sales.model.sales;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlightModel {

    private String flightCode; // FLIGHT_CODE
    private String airline; // AIRLINE
    private String flightNumber; //FLIGHT_NUMBER
    private String phone; //PHONE
    private String departure; //DEPARTURE

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmm")
    private Date departureTime; // DEPARTURE_TIME
    private String destination; // DESTINATION

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmm")
    private Date arrivalTime; // ARRIVAL_TIME
    private int price; // PRICE
    private String isUsed; // IS_USED

}
