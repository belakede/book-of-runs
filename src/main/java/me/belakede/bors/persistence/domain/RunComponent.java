package me.belakede.bors.persistence.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class RunComponent implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Run run;

    @JoinColumn
    @ManyToOne(fetch = FetchType.EAGER)
    private Component component;

    private Long resultId;

    @OneToMany(mappedBy = "runComponent", cascade = CascadeType.ALL)
    private List<Split> splits;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Run getRun() {
        return run;
    }

    public void setRun(Run run) {
        this.run = run;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public Long getResultId() {
        return resultId;
    }

    public void setResultId(Long resultId) {
        this.resultId = resultId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RunComponent that = (RunComponent) o;

        return run.equals(that.run) && component.equals(that.component) && resultId.equals(that.resultId);
    }

    @Override
    public int hashCode() {
        int result = run.hashCode();
        result = 31 * result + component.hashCode();
        result = 31 * result + resultId.hashCode();
        return result;
    }
}
