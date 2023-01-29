package com.example.coffeecom.model;

public class UploadResponse {
    private boolean error;
    private String message;
    private String image;

    public UploadResponse(boolean error, String message, String image) {
        this.error = error;
        this.message = message;
        this.image = image;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getImage() {
        return image;
    }
}
