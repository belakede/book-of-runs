package me.belakede.bors.persistence.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Run implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, updatable = false)
    private Integer runId;

    @Column(nullable = false, length = 100)
    private String operationSystem;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Environment environment;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private KerberosServerType kerberosServerType;

    @Column(nullable = false, updatable = false)
    private Date started;

    @Column(nullable = false, updatable = false, length = 50)
    private String releaseName;

    @Column(nullable = false, updatable = false)
    private Integer buildNumber;

    @OneToMany(mappedBy = "run", cascade = CascadeType.ALL)
    private List<RunComponent> runComponents;

    public Run() {
    }

    public Run(String operationSystem, Environment environment, KerberosServerType kerberosServerType, Date started, String releaseName, Integer buildNumber) {
        this.operationSystem = operationSystem;
        this.environment = environment;
        this.kerberosServerType = kerberosServerType;
        this.started = started;
        this.releaseName = releaseName;
        this.buildNumber = buildNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRunId() {
        return runId;
    }

    public void setRunId(Integer runId) {
        this.runId = runId;
    }

    public String getOperationSystem() {
        return operationSystem;
    }

    public void setOperationSystem(String operationSystem) {
        this.operationSystem = operationSystem;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public KerberosServerType getKerberosServerType() {
        return kerberosServerType;
    }

    public void setKerberosServerType(KerberosServerType kerberosServerType) {
        this.kerberosServerType = kerberosServerType;
    }

    public Date getStarted() {
        return started;
    }

    public void setStarted(Date started) {
        this.started = started;
    }

    public String getReleaseName() {
        return releaseName;
    }

    public void setReleaseName(String releaseName) {
        this.releaseName = releaseName;
    }

    public Integer getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(Integer buildNumber) {
        this.buildNumber = buildNumber;
    }

    public List<RunComponent> getRunComponents() {
        return runComponents;
    }

    public void setRunComponents(List<RunComponent> runComponents) {
        this.runComponents = runComponents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Run run = (Run) o;

        return operationSystem.equals(run.operationSystem) && environment == run.environment && kerberosServerType == run.kerberosServerType && releaseName.equals(run.releaseName) && buildNumber.equals(run.buildNumber);
    }

    @Override
    public int hashCode() {
        int result = operationSystem.hashCode();
        result = 31 * result + environment.hashCode();
        result = 31 * result + kerberosServerType.hashCode();
        result = 31 * result + releaseName.hashCode();
        result = 31 * result + buildNumber.hashCode();
        return result;
    }
}
