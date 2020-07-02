package com.unit.helpers.healthcheck.enums;

import java.text.MessageFormat;

public enum HealthCheckUrlEnums {
    //------enum val for health check url
    NON_PBZ_URL ( "https://google.com" ),
    PBZ_URL ( "https://alpha.playbuzz.com" );

    private String url;

    HealthCheckUrlEnums(String inType) {
        url = inType;
    }

    public String val() {
        return url;
    }

    public String format(Object... params) {
        return MessageFormat.format(val(), params);
    }

    @Override
    public String toString() {
        return url;
    }
}