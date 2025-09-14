package com.flightradarmsn.flightradar.model.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_airport")
public class Airport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String airportName;

    @Column(name = "iata")
    private String iataCode;

    @Column(name = "icao")
    private String icaoCode;
    private Double latitude;
    private Double longitude;

    @Column(name = "geoname_id")
    private Long geonameId;
    private String timezone;
    private Integer gmt;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "country_iso2")
    private String countryIso2;

    @Column(name = "city_iata_code")
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
        Airport airport = (Airport) o;
        return Objects.equals(id, airport.id) && Objects.equals(airportName, airport.airportName) && Objects.equals(iataCode, airport.iataCode) && Objects.equals(icaoCode, airport.icaoCode) && Objects.equals(latitude, airport.latitude) && Objects.equals(longitude, airport.longitude) && Objects.equals(geonameId, airport.geonameId) && Objects.equals(timezone, airport.timezone) && Objects.equals(gmt, airport.gmt) && Objects.equals(phoneNumber, airport.phoneNumber) && Objects.equals(countryName, airport.countryName) && Objects.equals(countryIso2, airport.countryIso2) && Objects.equals(cityIataCode, airport.cityIataCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, airportName, iataCode, icaoCode, latitude, longitude, geonameId, timezone, gmt, phoneNumber, countryName, countryIso2, cityIataCode);
    }
}
