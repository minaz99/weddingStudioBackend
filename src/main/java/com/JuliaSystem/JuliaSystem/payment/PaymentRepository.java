package com.JuliaSystem.JuliaSystem.payment;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    //@Override
    //Optional<Payment> findById(Integer integer);
}
