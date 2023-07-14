package com.JuliaSystem.JuliaSystem.payment;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "Payments")
public class Payment {
    @Id
    @GeneratedValue (strategy= GenerationType.AUTO)
    private Integer id;
    private Integer contractID;
    private String paymentTitle;
    private Integer amount;

    public Payment(Integer contractID, String paymentTitle, Integer amount) {
    }
}
