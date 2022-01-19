package com.engeto.projektDPH2;

import org.json.JSONObject;

import java.io.*;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.Format;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.text.DecimalFormat;


public class VATList {
    List <VAT> vatList = new ArrayList<>();


    public static BigDecimal getValue (String value, String i, JSONObject json) {
        return json.getJSONObject(i).getBigDecimal(value);
    }
    public static String getAllCountries() throws IOException, InterruptedException {
        String getVATFromWeb = "https://euvatrates.com/rates.json";
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(getVATFromWeb)).GET().build();
        HttpResponse httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        String allCountries = httpResponse.body().toString();
        return allCountries;
    }

    public void getAndSetVATList() throws IOException, InterruptedException {
        String allCountries = getAllCountries();

        String editedData = allCountries.replaceAll("false", "0");
        JSONObject jsonObject = new JSONObject(editedData);
        JSONObject jsonRates = jsonObject.getJSONObject("rates");

        jsonRates.keySet().forEach(i -> {
            if (i.equals("GB")|| i.equals("EL")){
                return;
            }
            VAT vat = new VAT();
            vat.setShortName(i);
            vat.setCountry(jsonRates.getJSONObject(i).getString("country"));
            vat.setStandard_rate(getValue("standard_rate", i, jsonRates));
            vat.setReduced_rate(getValue("reduced_rate", i, jsonRates));
            vat.setReduced_rate_alt(getValue("reduced_rate_alt", i, jsonRates));
            vat.setSuper_reduced_rate(getValue("super_reduced_rate", i, jsonRates));
            vat.setParking_rate(getValue("parking_rate", i, jsonRates));
            vatList.add(vat);
        });
    }

    public void printWholeVATList(){
        Format format = new DecimalFormat("0.#");
        vatList.forEach(i -> System.out.println(i.getShortName()+ "--- country: " +i.getCountry()+
                " -------\t standard_rate: " +format.format(i.getStandard_rate())));
    }

    public void getSortedList () {
        Collections.sort(vatList, new StandardRateComparator().reversed());
    }

    public void printHighest3Rates () {
        getSortedList();
        System.out.println("##################################\n3 státy s nejvyšší základní sazbou DPH");
        for (int i = 0; i < 3; i++) {
            System.out.println(vatList.get(i).shortDescription());
        }
        System.out.println("##################################");
    }

    public void printLowest3Rates () {
        getSortedList();
        Collections.reverse(vatList);
        System.out.println("##################################\n3 státy s nejnižší základní sazbou DPH");
        for (int i = 0; i < 3; i++) {
            System.out.println(vatList.get(i).shortDescription());
        }
        System.out.println("##################################");
    }

    public void exportSelectedRates () {
        try (PrintWriter writer = new PrintWriter(new File("Vybrané_země.txt"))) {
            getSortedList();
            writer.println("##################################\n3 státy s nejvyšší základní sazbou DPH");
            for (int i = 0; i < 3; i++) {
                writer.println(vatList.get(i).longDescription());
            }
            writer.println("##################################");
            Collections.reverse(vatList);
            writer.println("##################################\n3 státy s nejnižší základní sazbou DPH");
            for (int i = 0; i < 3; i++) {
                writer.println(vatList.get(i).longDescription());
            }
            writer.println("##################################");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void printCountryByKeyboardInput () throws IOException {
        System.out.println("**************************\nVybraná země podle zkratky.\nProsím, vložte zkratku požadované země:");
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader input= new BufferedReader(r);
        String inputShortName;
        String description = "";
        do {
            inputShortName = input.readLine().toUpperCase();
            for (VAT vat : vatList) {
                if (vat.getShortName().equals(inputShortName)) {
                    description = vat.shortDescription();
                    break;
                } else description = "";
            }
            if (description.isEmpty()){
                System.out.println("Špatně zadáná zkratka země, zkuste to znovu: ");
            } else System.out.println(description);
        }while (inputShortName.length()!=0);
        System.out.println("**************************");
    }

}