package me.belakede.bors.persistence.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "runs")
public class Run implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 100)
    private String operationSystem;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Environment environment;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private KerberosServerType serverType;

    @Column(nullable = false, updatable = false)
    private Date started;

    @Column(nullable = false, updatable = false, length = 50)
    private String releaseName;

    @Column(nullable = false, updatable = false)
    private Integer buildNumber;

    @OneToMany(mappedBy = "run")
    private List<Component> components;

    public Run() {
    }

    public Run(String operationSystem, Environment environment, KerberosServerType serverType, Date started, String releaseName, Integer buildNumber) {
        this.operationSystem = operationSystem;
        this.environment = environment;
        this.serverType = serverType;
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

    public KerberosServerType getServerType() {
        return serverType;
    }

    public void setServerType(KerberosServerType serverType) {
        this.serverType = serverType;
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

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Run run = (Run) o;

        return operationSystem.equals(run.operationSystem) && environment == run.environment && serverType == run.serverType && releaseName.equals(run.releaseName) && buildNumber.equals(run.buildNumber);
    }

    @Override
    public int hashCode() {
        int result = operationSystem.hashCode();
        result = 31 * result + environment.hashCode();
        result = 31 * result + serverType.hashCode();
        result = 31 * result + releaseName.hashCode();
        result = 31 * result + buildNumber.hashCode();
        return result;
    }
}
