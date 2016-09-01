package me.belakede.bors.persistence.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Ticket {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(updatable = false, nullable = false)
    private Jira jira;

    @Column(updatable = false, nullable = false)
    private String ticket;

    @JoinTable
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Testcase> testcases;

    public Ticket() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Jira getJira() {
        return jira;
    }

    public void setJira(Jira jira) {
        this.jira = jira;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket1 = (Ticket) o;

        if (jira != ticket1.jira) return false;
        return ticket.equals(ticket1.ticket);

    }

    @Override
    public int hashCode() {
        int result = jira.hashCode();
        result = 31 * result + ticket.hashCode();
        return result;
    }
}
