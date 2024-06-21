package kr.happytravel.erp.sales.model.sales.packages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;


@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class InitPackageModel {
    private String packageCode;
    private int saleAmount;
    private Date saleEndDate;
    private String isUsed;
}
