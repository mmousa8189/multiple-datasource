package com.example.multipledatasource.repository.sqlserver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.multipledatasource.entity.sqlserver.Bug;

@Repository
public interface BugRepository extends JpaRepository<Bug, Long> {
}
