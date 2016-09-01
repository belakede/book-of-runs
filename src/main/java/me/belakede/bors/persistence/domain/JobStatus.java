package me.belakede.bors.persistence.domain;

public enum JobStatus {

    NA("n/a"), STARTED("started"), FAILED("failed"), SUCCESSFUL("successful");

    private final String message;

    JobStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
