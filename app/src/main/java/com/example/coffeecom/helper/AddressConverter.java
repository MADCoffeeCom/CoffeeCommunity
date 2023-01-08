package com.example.coffeecom.helper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class AddressConverter {
    private static final String URL = "https://maps.googleapis.com/maps/api/geocode/json";

    /*
     * Here the fullAddress String is in format like
     * "address,city,state,zipcode". Here address means "street number + route"
     * .
     */
    public GoogleResponse convertToLatLong(String fullAddress) throws IOException {

        /*
         * Create an java.net.URL object by passing the request URL in
         * constructor. Here you can see I am converting the fullAddress String
         * in UTF-8 format. You will get Exception if you don't convert your
         * address in UTF-8 format. Perhaps google loves UTF-8 format. :) In
         * parameter we also need to pass "sensor" parameter. sensor (required
         * parameter) — Indicates whether or not the geocoding request comes
         * from a device with a location sensor. This value must be either true
         * or false.
         */
        URL url = new URL(URL + "?address="
                + URLEncoder.encode(fullAddress, "UTF-8") + "&&key=AIzaSyB9BbhJgnjr8SBiu9bPbwk1aR8prt-5cFM");
        // Open the Connection
        URLConnection conn = url.openConnection();

        InputStream in = conn.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        GoogleResponse response = (GoogleResponse) mapper.readValue(in, GoogleResponse.class);
        in.close();
        return response;


    }

    public GoogleResponse convertFromLatLong(String latlongString) throws IOException {

        /*
         * Create an java.net.URL object by passing the request URL in
         * constructor. Here you can see I am converting the fullAddress String
         * in UTF-8 format. You will get Exception if you don't convert your
         * address in UTF-8 format. Perhaps google loves UTF-8 format. :) In
         * parameter we also need to pass "sensor" parameter. sensor (required
         * parameter) — Indicates whether or not the geocoding request comes
         * from a device with a location sensor. This value must be either true
         * or false.
         */
        URL url = new URL(URL + "?latlng="
                + URLEncoder.encode(latlongString, "UTF-8") + "&sensor=false");
        // Open the Connection
        URLConnection conn = url.openConnection();

        InputStream in = conn.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        GoogleResponse response = (GoogleResponse) mapper.readValue(in, GoogleResponse.class);
        in.close();
        return response;


    }
}
