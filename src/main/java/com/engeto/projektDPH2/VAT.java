package com.engeto.projektDPH2;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Format;

public class VAT {

    private String shortName;
    private String country;
    @JsonProperty("standard_rate")
    private BigDecimal standardRate;
    @JsonProperty("reduced_rate")
    private BigDecimal reducedRate;
    @JsonProperty("reduced_rate_alt")
    private BigDecimal reducedRateAlt;
    @JsonProperty("super_reduced_rate")
    private BigDecimal superReducedRate;
    @JsonProperty("parking_rate")
    private BigDecimal parkingRate;

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public BigDecimal getStandardRate() {
        return standardRate;
    }

    public void setStandardRate(BigDecimal standardRate) {
        this.standardRate = standardRate;
    }

    public BigDecimal getReducedRate() {
        return reducedRate;
    }

    public void setReducedRate(BigDecimal reducedRate) {
        this.reducedRate = reducedRate;
    }

    public BigDecimal getReducedRateAlt() {
        return reducedRateAlt;
    }

    public void setReducedRateAlt(BigDecimal reducedRateAlt) {
        this.reducedRateAlt = reducedRateAlt;
    }

    public BigDecimal getSuperReducedRate() {
        return superReducedRate;
    }

    public void setSuperReducedRate(BigDecimal superReducedRate) {
        this.superReducedRate = superReducedRate;
    }

    public BigDecimal getParkingRate() {
        return parkingRate;
    }

    public void setParkingRate(BigDecimal parkingRate) {
        this.parkingRate = parkingRate;
    }

    public String shortDescription () {
        Format format = new DecimalFormat("0.#");
        String info;
        info = getShortName()+ "--- country: " +getCountry()+ " -------\t standard_rate: " +format.format(getStandardRate());
        return info;
    }

    public String longDescription () {
        Format format = new DecimalFormat("0.#");
        String info;
        info = "Země: " +getCountry()+ "\t(" +shortName+")";
        info+= "\n Zákl. sazba: " +format.format(getStandardRate())+ "%\t 1. snížená: " +format.format(getReducedRate())+
                "%\t2. snížená: " +format.format(getReducedRateAlt())+ "%\tSupersnížená: "+format.format(getSuperReducedRate())+
                "%\tSpeciální: " +format.format(getParkingRate())+"%";
        info+= "\n...........................";
        return info;
    }
}
