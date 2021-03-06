package me.belakede.bors.persistence.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Split implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Integer splitNumber;

    @JoinColumn
    @ManyToOne(fetch = FetchType.EAGER)
    private RunComponent runComponent;

    @OneToMany(mappedBy = "split", cascade = CascadeType.ALL)
    private List<Job> jobs;

    @OneToMany(mappedBy = "split", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<SplitTestcase> splitTestcases;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    private String logsUrl;

    private Integer numberOfTests;

    private Integer numberOfFailedTests;

    private Integer numberOfAbortedTests;

    private Integer numberOfPassedTests;

    public Split() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSplitNumber() {
        return splitNumber;
    }

    public void setSplitNumber(Integer splitNumber) {
        this.splitNumber = splitNumber;
    }

    public RunComponent getRunComponent() {
        return runComponent;
    }

    public void setRunComponent(RunComponent runComponent) {
        this.runComponent = runComponent;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public List<SplitTestcase> getSplitTestcases() {
        return splitTestcases;
    }

    public void setSplitTestcases(List<SplitTestcase> splitTestcases) {
        this.splitTestcases = splitTestcases;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getLogsUrl() {
        return logsUrl;
    }

    public void setLogsUrl(String logsUrl) {
        this.logsUrl = logsUrl;
    }

    public Integer getNumberOfTests() {
        return numberOfTests;
    }

    public void setNumberOfTests(Integer numberOfTests) {
        this.numberOfTests = numberOfTests;
    }

    public Integer getNumberOfFailedTests() {
        return numberOfFailedTests;
    }

    public void setNumberOfFailedTests(Integer numberOfFailedTests) {
        this.numberOfFailedTests = numberOfFailedTests;
    }

    public Integer getNumberOfAbortedTests() {
        return numberOfAbortedTests;
    }

    public void setNumberOfAbortedTests(Integer numberOfAbortedTests) {
        this.numberOfAbortedTests = numberOfAbortedTests;
    }

    public Integer getNumberOfPassedTests() {
        return numberOfPassedTests;
    }

    public void setNumberOfPassedTests(Integer numberOfPassedTests) {
        this.numberOfPassedTests = numberOfPassedTests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Split split = (Split) o;

        return splitNumber.equals(split.splitNumber) && runComponent.equals(split.runComponent);

    }

    @Override
    public int hashCode() {
        int result = splitNumber.hashCode();
        result = 31 * result + runComponent.hashCode();
        return result;
    }
}
