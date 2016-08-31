package me.belakede.bors.persistence.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "components")
public class Component implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn
    @ManyToOne(fetch = FetchType.EAGER)
    private Run run;

    @Column(nullable = false, updatable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private Integer resultId;

    public Component() {
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getResultId() {
        return resultId;
    }

    public void setResultId(Integer resultId) {
        this.resultId = resultId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Component component = (Component) o;

        return run != null ? run.equals(component.run) : component.run == null && (name != null ? name.equals(component.name) : component.name == null && (resultId != null ? resultId.equals(component.resultId) : component.resultId == null));

    }

    @Override
    public int hashCode() {
        int result = run != null ? run.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (resultId != null ? resultId.hashCode() : 0);
        return result;
    }
}
