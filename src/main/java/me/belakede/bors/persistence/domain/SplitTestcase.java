package me.belakede.bors.persistence.domain;

import javax.persistence.*;

@Entity
public class SplitTestcase {

    @Id
    private Long id;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Split split;

    @JoinColumn
    @ManyToOne(fetch = FetchType.EAGER)
    private Testcase testcase;

    @Enumerated(value = EnumType.STRING)
    private TestcaseStatus status;

    public SplitTestcase() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Split getSplit() {
        return split;
    }

    public void setSplit(Split split) {
        this.split = split;
    }

    public Testcase getTestcase() {
        return testcase;
    }

    public void setTestcase(Testcase testcase) {
        this.testcase = testcase;
    }

    public TestcaseStatus getStatus() {
        return status;
    }

    public void setStatus(TestcaseStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SplitTestcase that = (SplitTestcase) o;

        if (!testcase.equals(that.testcase)) return false;
        return status == that.status;

    }

    @Override
    public int hashCode() {
        int result = testcase.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }
}
