package me.belakede.bors.persistence.domain;

public enum KerberosServerType {

    AD("AD"), MIT("MIT"), NONE("-");

    private final String label;

    KerberosServerType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
