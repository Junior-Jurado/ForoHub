package com.forohub.challengeAluraForoHub.domain.topico;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
    ABIERTO,
    ARCHIVADO,
    ELIMINADO,
    PENDIENTE,
    RESUELTO;

    @JsonCreator
    public static Status fromValue(String value) {
        for (Status status : Status.values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Estado no válido: " + value);
    }

    @JsonValue
    public String toValue() {
        return name().toLowerCase();
    }
}
