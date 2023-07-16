package com.JuliaSystem.JuliaSystem.payment;



import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInfo {
    private List<Integer> paymentsIDs;
    private Integer dueAmount;
    private Integer total;

}
