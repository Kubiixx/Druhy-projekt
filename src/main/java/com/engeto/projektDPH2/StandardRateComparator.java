package com.engeto.projektDPH2;

import java.util.Comparator;

public class StandardRateComparator implements Comparator <VAT>{

    @Override
    public int compare(VAT first, VAT second) {
        return first.getStandard_rate().compareTo(second.getStandard_rate());
    }

}
