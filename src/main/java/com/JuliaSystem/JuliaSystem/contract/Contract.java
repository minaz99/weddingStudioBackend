package com.JuliaSystem.JuliaSystem.contract;


//import com.JuliaSystem.JuliaSystem.payment.PaymentInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "Contracts")
public class Contract  {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String secondPartyName;
    private String brideName;
    private String groomName;
    private String eventType;
    private String eventLocation;
    private Date eventDate;
    private String civilID;
    private Integer phone1;
    private Integer phone2;
    @Enumerated(EnumType.STRING)
    private ContractStatus contractStatus;
    private Integer contractPrice;
    private String photographer;
    private String video;
    private int packageID;
    private /*List<Integer>*/ String componentsIDs;
    @Enumerated(EnumType.STRING)
    private ContractStage contractStage;
    private String comments;
    //private PaymentInfo paymentsInfo;
}
