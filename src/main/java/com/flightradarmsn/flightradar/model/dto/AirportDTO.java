package com.flightradarmsn.flightradar.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

public class AirportDTO implements Serializable {

    private Long id;

    @JsonProperty(value = "airport_name")
    private String airportName;

    @JsonProperty(value = "iata_code")
    private String iataCode;

    @JsonProperty(value = "icao_code")
    private String icaoCode;

    private Double latitude;
    private Double longitude;

    @JsonProperty(value = "geoname_id")
    private Long geonameId;

    private String timezone;
    private Integer gmt;

    @JsonProperty(value = "phone_number")
    private String phoneNumber;

    @JsonProperty(value = "country_name")
    private String countryName;

    @JsonProperty(value = "country_iso2")
    private String countryIso2;

    @JsonProperty(value = "city_iata_code")
    private String cityIataCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getIcaoCode() {
        return icaoCode;
    }

    public void setIcaoCode(String icaoCode) {
        this.icaoCode = icaoCode;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Long getGeonameId() {
        return geonameId;
    }

    public void setGeonameId(Long geonameId) {
        this.geonameId = geonameId;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Integer getGmt() {
        return gmt;
    }

    public void setGmt(Integer gmt) {
        this.gmt = gmt;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryIso2() {
        return countryIso2;
    }

    public void setCountryIso2(String countryIso2) {
        this.countryIso2 = countryIso2;
    }

    public String getCityIataCode() {
        return cityIataCode;
    }

    public void setCityIataCode(String cityIataCode) {
        this.cityIataCode = cityIataCode;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AirportDTO that = (AirportDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(airportName, that.airportName) && Objects.equals(iataCode, that.iataCode) && Objects.equals(icaoCode, that.icaoCode) && Objects.equals(latitude, that.latitude) && Objects.equals(longitude, that.longitude) && Objects.equals(geonameId, that.geonameId) && Objects.equals(timezone, that.timezone) && Objects.equals(gmt, that.gmt) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(countryName, that.countryName) && Objects.equals(countryIso2, that.countryIso2) && Objects.equals(cityIataCode, that.cityIataCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, airportName, iataCode, icaoCode, latitude, longitude, geonameId, timezone, gmt, phoneNumber, countryName, countryIso2, cityIataCode);
    }
}
