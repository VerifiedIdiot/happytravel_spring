package kr.happytravel.erp.sales.model.sales.packages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class InitSaleAmountModel {
    private String packageCode;
    private int saleAmount;
}
