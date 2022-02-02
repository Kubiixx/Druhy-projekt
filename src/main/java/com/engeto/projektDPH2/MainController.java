package com.engeto.projektDPH2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;

@RestController
public class MainController {

    @GetMapping(value = "/hello")
    public String hello () {
        return "Zdravím";
    }

    @GetMapping ("/api/country")
    public VAT getSelectedCountryVAT (@RequestParam(required = true) String shortName) throws IOException, InterruptedException {
        VATList list = new VATList();
        list.getAndSetVATList();
        VAT description = new VAT();
        for (VAT vat: list.vatList){
            if (vat.getShortName().equals(shortName)) {
                description = vat;
                break;
            }
        }
        return description;
    }

    @GetMapping ("/api/country-rate")
    public BigDecimal getSelectedCountryStandardOrReducedRate (@RequestParam(required = true) String shortName, @RequestParam (required = true) String value) throws IOException, InterruptedException {
        VATList list = new VATList();
        list.getAndSetVATList();
        BigDecimal description = BigDecimal.ZERO;
        for (VAT vat: list.vatList){
            if (vat.getShortName().equals(shortName)) {
                if (value.equals("standard")) {description = vat.getStandardRate();}
                if (value.equals("reduced")) {description = vat.getReducedRate();}
                else description = BigDecimal.valueOf(9999);
                break;
            }
        }
        return description;
    }

    @GetMapping ("/api/country/all")
    public String getAllCountries () throws IOException, InterruptedException {
        return VATList.getAllCountries();
    }

}
