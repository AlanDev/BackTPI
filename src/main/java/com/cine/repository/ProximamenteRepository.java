package com.cine.repository;

import com.cine.model.Proximamente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProximamenteRepository extends JpaRepository<Proximamente, Long> {
} 