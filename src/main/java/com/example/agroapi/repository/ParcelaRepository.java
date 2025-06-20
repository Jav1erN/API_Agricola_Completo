package com.example.agroapi.repository;

import com.example.agroapi.entity.Parcela;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParcelaRepository extends JpaRepository<Parcela, Long> {
}
