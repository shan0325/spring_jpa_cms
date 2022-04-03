package com.spring.cms.repository;

import com.spring.cms.domain.BoardManager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardManagerRepository extends JpaRepository<BoardManager, Long> {
}
