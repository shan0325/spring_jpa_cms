package com.spring.cms.repository.menu;

import com.spring.cms.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long>, MenuRepositoryCustom {
    Optional<Menu> findByParent(Menu parent);
}
