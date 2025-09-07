package com.example.multipledatasource.repository.postgresql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.multipledatasource.entity.postgresql.Flower;

@Repository
public interface FlowerRepository extends JpaRepository<Flower, Long> {
}
