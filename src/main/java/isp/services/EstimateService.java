package isp.services;

import isp.constants.IConstant;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

public class EstimateService {

    public BigInteger findEstimate(LinkedHashMap<String, String> data, String button) {
        if( button.equals("acoc") ) return estimateACOC(data);
        else if( button.equals("ecc") ) return estimateECC(data);
        else return estimateBCC(data);
    }
    private BigInteger estimateACOC(LinkedHashMap<String, String> data) {

        BigInteger result = BigInteger.ONE;
        for(String key: data.keySet()) {
            String characteristics = data.get(key);
            result = result.multiply(BigInteger.valueOf(characteristics.split(IConstant.blockSeparator).length));
        }
        return result;
    }

    private BigInteger estimateECC(LinkedHashMap<String, String> data) {

        ArrayList<Integer> lengths = new ArrayList<>();
        for(String key: data.keySet()) {
            String characteristics = data.get(key);
            lengths.add( characteristics.split(IConstant.blockSeparator).length );
        }
        lengths.sort(Collections.reverseOrder());

        return BigInteger.valueOf(lengths.get(0));
    }

    private BigInteger estimateBCC(LinkedHashMap<String, String> data) {
        BigInteger result = BigInteger.ONE;

        for(String key: data.keySet()) {
            String datum = data.get(key);
            result = result.add( BigInteger.valueOf((datum.trim().split(IConstant.blockSeparator).length - 1)) );
        }
        return result;
    }
}
