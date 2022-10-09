package org.swe632.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ECCService {

    public List<List<String>> generateECC(List<String> data) {
        List<List<String>> result = new ArrayList<>();
        int maxLen = findMaxLen(data);

        for(int i=0; i<maxLen; i++) {
            List<String> temp = new ArrayList<>();
            for(String characteristics: data) {
                String [] blocks = characteristics.trim().split(";");
                if( i >= blocks.length) temp.add(blocks[0]);
                else temp.add(blocks[i]);
            }
            result.add(temp);
        }
        return result;
    }

    private int findMaxLen(List<String> data) {
        int max = 0;
        for(String characteristics: data) {
            max = Math.max(max, characteristics.trim().split(";").length);
        }
        return max;
    }
}
