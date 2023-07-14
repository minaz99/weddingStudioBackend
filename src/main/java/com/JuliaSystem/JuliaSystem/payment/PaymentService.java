package com.JuliaSystem.JuliaSystem.payment;

import com.JuliaSystem.JuliaSystem.contract.Contract;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    public List<Payment> getPaymentsForContract (Integer contractID) {
        List<Payment> payments = paymentRepository.findAll();
        List<Integer> paymentsID = new ArrayList<Integer>();
        List<Payment> contractPayments = payments.stream().filter(payment -> payment.getContractID() == contractID).toList();
        return contractPayments;
    }

    public Integer getContractTotalPaid (Integer contractID) {
        List<Payment> payments = getPaymentsForContract(contractID);
        Integer totalPaid =  payments.stream().map(payment -> payment.getAmount()).reduce(0, Integer::sum);
        return totalPaid;
    }

    public void makePayment (Integer contractID, String paymentTitle, Integer amount) {
        Payment payment = new Payment();
        payment.setPaymentTitle(paymentTitle);
        payment.setContractID(contractID);
        payment.setAmount(amount);
        paymentRepository.save(payment);
    }

    public List<Integer> getPaymentIDsForContract(Integer contractID ) {

        List<Payment> paymentsForContract = getPaymentsForContract(contractID);
        List<Integer> paymentIDs = new ArrayList<Integer>();
        paymentsForContract.forEach(payment -> paymentIDs.add(payment.getId()));
        return paymentIDs;
    }
}
