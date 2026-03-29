package com.ai.resume_analyzer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ai.resume_analyzer.Entity.Analysis;
import com.ai.resume_analyzer.Entity.User;

public interface AnalysisRepository extends JpaRepository<Analysis, Long> {

    List<Analysis> findByUser(User user);
}