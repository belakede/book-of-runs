package me.belakede.bors.persistence.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Component implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, updatable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private Long componentId;

    public Component() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getComponentId() {
        return componentId;
    }

    public void setComponentId(Long componentId) {
        this.componentId = componentId;
    }


}
