package com.JuliaSystem.JuliaSystem.contract;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {
    //@Override
    //Optional<Contract> findById(Integer integer);
}
