package me.belakede.bors.persistence.repository;

import me.belakede.bors.persistence.domain.Environment;
import me.belakede.bors.persistence.domain.KerberosServerType;
import me.belakede.bors.persistence.domain.Run;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;


@DataJpaTest
@RunWith(SpringRunner.class)
public class RunRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RunRepository repository;

    @Test
    public void testFindByOperationSystemShouldReturnAListContainsOnlyUbuntu14Runs() throws Exception {
        entityManager.persist(new Run("Ubuntu 14.04", Environment.OPENSTACK, KerberosServerType.AD, Date.from(Instant.now()), "Derg-M20", 42));
        entityManager.persist(new Run("CentOS 6", Environment.YCLOUD, KerberosServerType.MIT, Date.from(Instant.now()), "Derg-M20", 42));

        List<Run> runs = repository.findByOperationSystem("Ubuntu 14.04");
        assertThat(runs.size()).isEqualTo(1);
        assertThat(runs.get(0).getReleaseName()).isEqualTo("Derg-M20");
        assertThat(runs.get(0).getOperationSystem()).isEqualTo("Ubuntu 14.04");
        assertThat(runs.get(0).getEnvironment()).isEqualTo(Environment.OPENSTACK);
    }

    @Test
    public void testFindByEnvironmentShouldReturnAListContainsRunsRanOnTheSpecifiedEnvironment() throws Exception {
        entityManager.persist(new Run("SLES 11.3", Environment.OPENSTACK, KerberosServerType.AD, Date.from(Instant.now()), "Derg-M20", 42));
        entityManager.persist(new Run("CentOS 6", Environment.YCLOUD, KerberosServerType.MIT, Date.from(Instant.now()), "Derg-M20", 42));
        entityManager.persist(new Run("Debian", Environment.OPENSTACK, KerberosServerType.NONE, Date.from(Instant.now()), "Derg-M20", 45));

        List<Run> runs = repository.findByEnvironment(Environment.OPENSTACK);
        assertThat(runs.size()).isEqualTo(2);
        assertThat(runs.get(0).getReleaseName()).isEqualTo("Derg-M20");
        assertThat(runs.get(0).getOperationSystem()).isEqualTo("SLES 11.3");
        assertThat(runs.get(0).getEnvironment()).isEqualTo(Environment.OPENSTACK);
        assertThat(runs.get(1).getOperationSystem()).isEqualTo("Debian");
    }

    @Test
    public void testFindByKerberosServerTypeShouldReturnAListContainsOnlyADRuns() throws Exception {
        entityManager.persist(new Run("SLES 11.3", Environment.OPENSTACK, KerberosServerType.AD, Date.from(Instant.now()), "Derg-M20", 42));
        entityManager.persist(new Run("Debian", Environment.OPENSTACK, KerberosServerType.NONE, Date.from(Instant.now()), "Derg-M20", 45));
        entityManager.persist(new Run("Ubuntu 14.04", Environment.OPENSTACK, KerberosServerType.AD, Date.from(Instant.now()), "Derg-M20", 42));
        entityManager.persist(new Run("CentOS 6", Environment.YCLOUD, KerberosServerType.MIT, Date.from(Instant.now()), "Derg-M20", 41));

        List<Run> runs = repository.findByKerberosServerType(KerberosServerType.AD);
        assertThat(runs.size()).isEqualTo(2);
        assertThat(runs.get(0).getReleaseName()).isEqualTo("Derg-M20");
        assertThat(runs.get(0).getOperationSystem()).isEqualTo("SLES 11.3");
        assertThat(runs.get(0).getEnvironment()).isEqualTo(Environment.OPENSTACK);
        assertThat(runs.get(1).getOperationSystem()).isEqualTo("Ubuntu 14.04");
    }

    @Test
    public void testFindByReleaseNameShouldReturnOnlyTheDergM20Runs() throws Exception {
        entityManager.persist(new Run("SLES 11.3", Environment.OPENSTACK, KerberosServerType.AD, Date.from(Instant.now()), "Derg-M20", 42));
        entityManager.persist(new Run("CentOS 6", Environment.YCLOUD, KerberosServerType.MIT, Date.from(Instant.now()), "Erie-M10", 41));
        entityManager.persist(new Run("Debian 7", Environment.OPENSTACK, KerberosServerType.NONE, Date.from(Instant.now()), "Derg-M20", 45));
        entityManager.persist(new Run("Ubuntu 14.04", Environment.OPENSTACK, KerberosServerType.AD, Date.from(Instant.now()), "Dal-M40", 42));

        List<Run> runs = repository.findByReleaseName("Derg-M20");
        assertThat(runs.size()).isEqualTo(2);
        assertThat(runs.get(0).getReleaseName()).isEqualTo("Derg-M20");
        assertThat(runs.get(0).getOperationSystem()).isEqualTo("SLES 11.3");
        assertThat(runs.get(0).getEnvironment()).isEqualTo(Environment.OPENSTACK);
        assertThat(runs.get(1).getOperationSystem()).isEqualTo("Debian 7");
    }

    @Test
    public void testFindByBuildNumber() throws Exception {
        entityManager.persist(new Run("Debian 7", Environment.OPENSTACK, KerberosServerType.NONE, Date.from(Instant.now()), "Derg-M20", 45));
        entityManager.persist(new Run("Ubuntu 14.04", Environment.HUMBOLDT, KerberosServerType.AD, Date.from(Instant.now()), "Derg-M20", 42));
        entityManager.persist(new Run("SLES 11.3", Environment.OPENSTACK, KerberosServerType.MIT, Date.from(Instant.now()), "Derg-M20", 42));
        entityManager.persist(new Run("CentOS 6", Environment.YCLOUD, KerberosServerType.MIT, Date.from(Instant.now()), "Derg-M20", 41));

        List<Run> runs = repository.findByBuildNumber(42);
        assertThat(runs.size()).isEqualTo(2);
        assertThat(runs.get(0).getBuildNumber()).isEqualTo(42);
        assertThat(runs.get(0).getOperationSystem()).isEqualTo("Ubuntu 14.04");
        assertThat(runs.get(0).getEnvironment()).isEqualTo(Environment.HUMBOLDT);
        assertThat(runs.get(1).getOperationSystem()).isEqualTo("SLES 11.3");
    }

    @Test
    public void testFindByReleaseNameAndBuildNumber() throws Exception {
        entityManager.persist(new Run("Debian 7", Environment.OPENSTACK, KerberosServerType.NONE, Date.from(Instant.now()), "Derg-M20", 45));
        entityManager.persist(new Run("CentOS 6", Environment.YCLOUD, KerberosServerType.MIT, Date.from(Instant.now()), "Erie-M10", 41));
        entityManager.persist(new Run("Ubuntu 14.04", Environment.OPENSTACK, KerberosServerType.AD, Date.from(Instant.now()), "Dal-M40", 42));
        entityManager.persist(new Run("SLES 11.3", Environment.HUMBOLDT, KerberosServerType.AD, Date.from(Instant.now()), "Derg-M20", 42));

        List<Run> runs = repository.findByReleaseNameAndBuildNumber("Derg-M20", 42);
        assertThat(runs.size()).isEqualTo(1);
        assertThat(runs.get(0).getReleaseName()).isEqualTo("Derg-M20");
        assertThat(runs.get(0).getOperationSystem()).isEqualTo("SLES 11.3");
        assertThat(runs.get(0).getEnvironment()).isEqualTo(Environment.HUMBOLDT);
    }

    @Test
    public void testFindByOperationSystemAndEnvironment() throws Exception {
        entityManager.persist(new Run("CentOS 6", Environment.OPENSTACK, KerberosServerType.MIT, Date.from(Instant.now()), "Derg-M20", 41));
        entityManager.persist(new Run("Debian 7", Environment.OPENSTACK, KerberosServerType.NONE, Date.from(Instant.now()), "Derg-M20", 45));
        entityManager.persist(new Run("Ubuntu 14.04", Environment.HUMBOLDT, KerberosServerType.AD, Date.from(Instant.now()), "Derg-M20", 42));
        entityManager.persist(new Run("CentOS 6", Environment.YCLOUD, KerberosServerType.MIT, Date.from(Instant.now()), "Derg-M20", 42));

        List<Run> runs = repository.findByOperationSystemAndEnvironment("CentOS 6", Environment.OPENSTACK);
        assertThat(runs.size()).isEqualTo(1);
        assertThat(runs.get(0).getBuildNumber()).isEqualTo(41);
        assertThat(runs.get(0).getOperationSystem()).isEqualTo("CentOS 6");
        assertThat(runs.get(0).getEnvironment()).isEqualTo(Environment.OPENSTACK);
    }

    @Test
    public void testFindByOperationSystemAndKerberosServerType() throws Exception {
        entityManager.persist(new Run("Ubuntu 14.04", Environment.HUMBOLDT, KerberosServerType.AD, Date.from(Instant.now()), "Derg-M20", 42));
        entityManager.persist(new Run("Debian 7", Environment.OPENSTACK, KerberosServerType.MIT, Date.from(Instant.now()), "Derg-M20", 41));
        entityManager.persist(new Run("Debian 7", Environment.OPENSTACK, KerberosServerType.NONE, Date.from(Instant.now()), "Derg-M20", 45));
        entityManager.persist(new Run("CentOS 6", Environment.YCLOUD, KerberosServerType.MIT, Date.from(Instant.now()), "Derg-M20", 42));

        List<Run> runs = repository.findByOperationSystemAndKerberosServerType("Debian 7", KerberosServerType.MIT);
        assertThat(runs.size()).isEqualTo(1);
        assertThat(runs.get(0).getBuildNumber()).isEqualTo(41);
        assertThat(runs.get(0).getOperationSystem()).isEqualTo("Debian 7");
        assertThat(runs.get(0).getEnvironment()).isEqualTo(Environment.OPENSTACK);
    }

    @Test
    public void findByOperationSystemAndEnvironmentAndKerberosServerType() throws Exception {
        entityManager.persist(new Run("Ubuntu 14.04", Environment.HUMBOLDT, KerberosServerType.NONE, Date.from(Instant.now()), "Derg-M20", 45));
        entityManager.persist(new Run("Ubuntu 14.04", Environment.YCLOUD, KerberosServerType.AD, Date.from(Instant.now()), "Derg-M20", 42));
        entityManager.persist(new Run("Ubuntu 14.04", Environment.OPENSTACK, KerberosServerType.MIT, Date.from(Instant.now()), "Derg-M20", 42));
        entityManager.persist(new Run("Ubuntu 14.04", Environment.YCLOUD, KerberosServerType.MIT, Date.from(Instant.now()), "Derg-M20", 41));

        List<Run> runs = repository.findByOperationSystemAndEnvironmentAndKerberosServerType("Ubuntu 14.04", Environment.YCLOUD, KerberosServerType.AD);
        assertThat(runs.size()).isEqualTo(1);
        assertThat(runs.get(0).getBuildNumber()).isEqualTo(42);
        assertThat(runs.get(0).getOperationSystem()).isEqualTo("Ubuntu 14.04");
        assertThat(runs.get(0).getEnvironment()).isEqualTo(Environment.YCLOUD);
    }
}