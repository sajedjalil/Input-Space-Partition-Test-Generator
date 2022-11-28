package isp.services;

import isp.constants.IConstant;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static isp.constants.IConstant.dataLimit;

public class BCCService{

    public List<List<String>> generate(LinkedHashMap<String, String> data) {

        List<List<String>> normalBlocks = findNonBaseBlocks(data);
        List<String> baseBlocks = findBaseBlocks(data);

        List<List<String>> result = new ArrayList<>();
        result.add(baseBlocks);

        for(int i=0; i<baseBlocks.size(); i++) {
            for(int j=0; j<normalBlocks.get(i).size(); j++) {
                List<String> temp = new ArrayList<>(baseBlocks);
                temp.set( i, normalBlocks.get(i).get(j));
                result.add(temp);
            }

            if( result.size() >= dataLimit ) {
                return result.subList(0, dataLimit);
            }
        }
        return result;
    }


    private static List<List<String>> findNonBaseBlocks(LinkedHashMap<String, String> data) {
        List<List<String>> filtered = new ArrayList<>();

        for(String key: data.keySet()) {
            String datum = data.get(key);
            String[] blocks = datum.trim().split(IConstant.blockSeparator);

            List<String> temp = new ArrayList<>();
            for( int i=1; i<blocks.length; i++) temp.add( blocks[i].trim()); // We assume first one is the base block
            filtered.add(temp);
        }

        return filtered;
    }

    private static List<String> findBaseBlocks(LinkedHashMap<String, String> data) {
        List<String> result = new ArrayList<>();

        for(String key: data.keySet()) {
            String datum = data.get(key);
            String[] blocks = datum.trim().split(IConstant.blockSeparator);
            result.add( blocks[0].trim()); // We assume first one is the base block
        }

        return result;
    }
}
