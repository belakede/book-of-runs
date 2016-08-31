package me.belakede.bors.persistence.repository;

import me.belakede.bors.persistence.domain.Run;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RunRepository extends JpaRepository<Run, Long> {

    List<Run> findByOperationSystem(String operationSystem);

}
