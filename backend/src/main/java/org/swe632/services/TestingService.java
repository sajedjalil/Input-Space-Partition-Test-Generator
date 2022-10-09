package org.swe632.services;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TestingService {

    public boolean isDataValid(List<String> data) {
        if(data == null || data.size() ==0) return false;
        for(String characteristic: data) {
            if( characteristic.trim().length() == 0) return false;
        }

        return true;
    }

    public BigInteger estimateACOC(List<String> data) {

        BigInteger result = BigInteger.ONE;
        for(String characteristics: data) {
            result = result.multiply(BigInteger.valueOf(characteristics.split(";").length));
        }
        return result;
    }

    public BigInteger estimateECC(List<String> data) {

        ArrayList<Integer> lengths = new ArrayList<>();
        for(String characteristics: data) lengths.add( characteristics.split(";").length );
        lengths.sort(Collections.reverseOrder());

        BigInteger result = BigInteger.valueOf(lengths.get(0));
        if(lengths.size()>1) result = result.multiply( BigInteger.valueOf(lengths.get(1)));

        return result;
    }

    public BigInteger estimateBCC(List<String> data, List<String> baseBlocks) {
        BigInteger baseChoiceCount = estimateECC(baseBlocks);
        BigInteger result = BigInteger.valueOf(baseChoiceCount.longValue());

        for(int i=0; i< data.size(); i++){
            result = result.add(BigInteger.valueOf((long) baseBlocks.get(i).trim().split(";").length
                    * (data.get(i).trim().split(";").length-baseBlocks.get(i).trim().split(";").length)));
        }
        return result;
    }
}
