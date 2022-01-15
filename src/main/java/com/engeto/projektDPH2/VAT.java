package com.engeto.projektDPH2;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Format;

public class VAT {

    private String shortName;
    private String country;
    private BigDecimal standard_rate;
    private BigDecimal reduced_rate;
    private BigDecimal reduced_rate_alt;
    private BigDecimal super_reduced_rate;
    private BigDecimal parking_rate;

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

    public BigDecimal getStandard_rate() {
        return standard_rate;
    }

    public void setStandard_rate(BigDecimal standard_rate) {
        this.standard_rate = standard_rate;
    }

    public BigDecimal getReduced_rate() {
        return reduced_rate;
    }

    public void setReduced_rate(BigDecimal reduced_rate) {
        this.reduced_rate = reduced_rate;
    }

    public BigDecimal getReduced_rate_alt() {
        return reduced_rate_alt;
    }

    public void setReduced_rate_alt(BigDecimal reduced_rate_alt) {
        this.reduced_rate_alt = reduced_rate_alt;
    }

    public BigDecimal getSuper_reduced_rate() {
        return super_reduced_rate;
    }

    public void setSuper_reduced_rate(BigDecimal super_reduced_rate) {
        this.super_reduced_rate = super_reduced_rate;
    }

    public BigDecimal getParking_rate() {
        return parking_rate;
    }

    public void setParking_rate(BigDecimal parking_rate) {
        this.parking_rate = parking_rate;
    }

    public String shortDescription () {
        Format format = new DecimalFormat("0.#");
        String info;
        info = getShortName()+ "--- country: " +getCountry()+ " -------\t standard_rate: " +format.format(getStandard_rate());
        return info;
    }

    public String longDescription () {
        Format format = new DecimalFormat("0.#");
        String info;
        info = "Země: " +getCountry()+ "\t(" +shortName+")";
        info+= "\n Zákl. sazba: " +format.format(getStandard_rate())+ "%\t 1. snížená: " +format.format(getReduced_rate())+
                "%\t2. snížená: " +format.format(getReduced_rate_alt())+ "%\tSupersnížená: "+format.format(getSuper_reduced_rate())+
                "%\tSpeciální: " +format.format(getParking_rate())+"%";
        info+= "\n...........................";
        return info;
    }
}
