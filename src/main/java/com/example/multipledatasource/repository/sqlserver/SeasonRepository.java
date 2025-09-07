package com.example.multipledatasource.repository.sqlserver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.multipledatasource.entity.sqlserver.Season;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Long> {
}
