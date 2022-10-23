package org.swe632.services;

import org.springframework.stereotype.Service;
import org.swe632.constants.Constant;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EstimateService {

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
            result = result.multiply(BigInteger.valueOf(characteristics.split(Constant.separator).length));
        }
        return result;
    }

    public BigInteger estimateECC(List<String> data) {

        ArrayList<Integer> lengths = new ArrayList<>();
        for(String characteristics: data) lengths.add( characteristics.split(Constant.separator).length );
        lengths.sort(Collections.reverseOrder());

        return BigInteger.valueOf(lengths.get(0));
    }

    public BigInteger estimateBCC(List<String> data, List<String> baseBlocks) {
        BigInteger baseChoiceCount = estimateECC(baseBlocks);
        BigInteger result = BigInteger.valueOf(baseChoiceCount.longValue());

        for(int i=0; i< data.size(); i++){
            result = result.add(BigInteger.valueOf((long) baseBlocks.get(i).trim().split(Constant.separator).length
                    * (data.get(i).trim().split(Constant.separator).length
                    - baseBlocks.get(i).trim().split(Constant.separator).length)));
        }
        return result;
    }

}
