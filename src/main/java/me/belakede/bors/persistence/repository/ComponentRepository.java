package me.belakede.bors.persistence.repository;

import me.belakede.bors.persistence.domain.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComponentRepository extends JpaRepository<Component, Long> {

    Component findByName(String name);
    Component findByComponentId(Long componentId);

}
