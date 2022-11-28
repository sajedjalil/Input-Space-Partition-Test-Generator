package isp.services;

import isp.constants.IConstant;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static isp.constants.IConstant.dataLimit;

public class ECCService{
    public List<List<String>> generate(LinkedHashMap<String, String> data) {
        List<List<String>> result = new ArrayList<>();
        int maxLen = findMaxLen(data);

        for(int i=0; i<maxLen; i++) {
            List<String> temp = new ArrayList<>();
            for(String key: data.keySet()) {
                String characteristics = data.get(key);
                String [] blocks = characteristics.trim().split(IConstant.blockSeparator);
                if( i >= blocks.length) temp.add(blocks[0].trim());
                else temp.add(blocks[i].trim());
            }
            result.add(temp);

            if( result.size() >= dataLimit ) {
                return result.subList(0, dataLimit);
            }
        }
        return result;
    }

    private static int findMaxLen(LinkedHashMap<String, String> data) {
        int max = 0;
        for(String key: data.keySet()) {
            String characteristics = data.get(key);
            max = Math.max(max, characteristics.trim().split(IConstant.blockSeparator).length);
        }
        return max;
    }
}
