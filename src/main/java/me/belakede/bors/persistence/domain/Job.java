package me.belakede.bors.persistence.domain;

import javax.persistence.*;

@Entity
public class Job {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Split split;

    @Enumerated(value = EnumType.STRING)
    private JobType jobType;

    @Column(nullable = false, updatable = false)
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private JobStatus status;

    public Job() {
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

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Job job = (Job) o;

        return split.equals(job.split) && jobType == job.jobType && (url != null ? url.equals(job.url) : job.url == null && status == job.status);

    }

    @Override
    public int hashCode() {
        int result = split.hashCode();
        result = 31 * result + jobType.hashCode();
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + status.hashCode();
        return result;
    }
}
