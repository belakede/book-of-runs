package me.belakede.bors.persistence.domain;

public enum Environment {

    UNKNOWN("unknown"), OPENSTACK("OpenStack"), YCLOUD("YCloud"), HUMBOLDT("Humboldt");

    private final String label;

    Environment(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
