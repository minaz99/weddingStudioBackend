package com.JuliaSystem.JuliaSystem.controller;

import com.JuliaSystem.JuliaSystem.payment.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/{contractID}")
    public void makePayment(@PathVariable("contractID") Integer contractID, @RequestParam String paymentTitle,
                            @RequestParam Integer amount){
        paymentService.makePayment(contractID,paymentTitle,amount);
    }


}
