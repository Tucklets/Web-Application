package com.tucklets.app.entities.enums;

import java.util.List;

public enum SponsorInfoStatus {
    SUCCESS(200), ERROR(400);

    private int statusCode;

    SponsorInfoStatus(int statusCode) {
        this.statusCode = statusCode;
    }

}
