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
    public void findByOperationSystemShouldReturnAListContainsOnlyUbuntu14Runs() throws Exception {
        entityManager.persist(new Run("Ubuntu 14.04", Environment.OPENSTACK, KerberosServerType.AD, Date.from(Instant.now()), "Derg-M20", 42));
        entityManager.persist(new Run("CentOS 6", Environment.YCLOUD, KerberosServerType.MIT, Date.from(Instant.now()), "Derg-M20", 42));

        List<Run> runs = repository.findByOperationSystem("Ubuntu 14.04");
        assertThat(runs.size()).isEqualTo(1);
        assertThat(runs.get(0).getReleaseName()).isEqualTo("Derg-M20");
        assertThat(runs.get(0).getOperationSystem()).isEqualTo("Ubuntu 14.04");
        assertThat(runs.get(0).getEnvironment()).isEqualTo(Environment.OPENSTACK);
    }

}