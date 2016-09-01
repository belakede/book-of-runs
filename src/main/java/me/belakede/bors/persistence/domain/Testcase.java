package me.belakede.bors.persistence.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Testcase {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, updatable = false, length = 50)
    private String component;

    @Column(nullable = false, unique = true, updatable = false)
    private Integer testCaseId;

    private String suite;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "testcases")
    private List<Ticket> tickets;

    public Testcase() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(Integer testCaseId) {
        this.testCaseId = testCaseId;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Testcase testcase = (Testcase) o;

        return testCaseId.equals(testcase.testCaseId);
    }

    @Override
    public int hashCode() {
        return testCaseId.hashCode();
    }


}
