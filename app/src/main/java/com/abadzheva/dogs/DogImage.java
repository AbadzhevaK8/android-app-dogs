package com.abadzheva.dogs;

import androidx.annotation.NonNull;

public class DogImage {

    private String massage;
    private String status;

    public DogImage(String massage, String status) {
        this.massage = massage;
        this.status = status;
    }

    public String getMassage() {
        return massage;
    }

    public String getStatus() {
        return status;
    }

    @NonNull
    @Override
    public String toString() {
        return "DogImage{" +
                "massage='" + massage + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
