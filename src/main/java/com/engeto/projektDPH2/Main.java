package com.engeto.projektDPH2;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException, InterruptedException {
        VATList list = new VATList();
        list.getAndSetVATList();
        list.getSortedList();
        list.printHighest3Rates();
        list.printLowest3Rates();
        list.exportSelectedRates();
        list.printCountryByKeyboardInput();
    }


}
