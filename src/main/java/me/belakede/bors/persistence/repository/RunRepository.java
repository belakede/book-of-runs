package me.belakede.bors.persistence.repository;

import me.belakede.bors.persistence.domain.Environment;
import me.belakede.bors.persistence.domain.KerberosServerType;
import me.belakede.bors.persistence.domain.Run;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RunRepository extends JpaRepository<Run, Long> {

    List<Run> findByOperationSystem(String operationSystem);

    List<Run> findByEnvironment(Environment environment);

    List<Run> findByKerberosServerType(KerberosServerType serverType);

    List<Run> findByReleaseName(String releaseName);

    List<Run> findByBuildNumber(Integer buildNumber);

    List<Run> findByReleaseNameAndBuildNumber(String releaseName, Integer buildNumber);

    List<Run> findByOperationSystemAndEnvironment(String operationSystem, Environment environment);

    List<Run> findByOperationSystemAndKerberosServerType(String operationSystem, KerberosServerType kerberosServerType);

    List<Run> findByOperationSystemAndEnvironmentAndKerberosServerType(String operationSystem, Environment environment, KerberosServerType kerberosServerType);
}
