package com.engeto.projektDPH2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.io.IOException;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        VATList list = new VATList();
        list.getAndSetVATList();
        list.getSortedList();
        list.printHighest3Rates();
        list.printLowest3Rates();
        list.exportSelectedRates();
        list.printCountryByKeyboardInput();

        SpringApplication.run(Main.class, args);

    }


}
