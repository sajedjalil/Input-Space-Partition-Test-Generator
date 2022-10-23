package org.swe632.services;

import org.springframework.stereotype.Service;
import org.swe632.constants.Constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class BCCService {

    final EstimateService estimateService;

    final ECCService eccService;

    public BCCService(EstimateService estimateService, ECCService eccService) {
        this.estimateService = estimateService;
        this.eccService = eccService;
    }


    public List<List<String>> generateBCC(List<String> data, List<String> baseBlocks) {
        List<List<String>> result = eccService.generateECC(baseBlocks);
        List<List<String>> baseResult = new ArrayList<>(result);

        List<List<String>> normalBlocks = filterBaseBlocks(data, baseBlocks);

        for(List<String> base: baseResult) {
            for(int i=0; i<base.size(); i++) {
                for(int j=0; j<normalBlocks.get(i).size(); j++) {
                    List<String> temp = new ArrayList<>(base);
                    temp.set( i, normalBlocks.get(i).get(j));
                    result.add(temp);
                }
            }
        }

        return result;
    }


    private List<List<String>> filterBaseBlocks(List<String> data, List<String> baseBlocks) {
        List<List<String>> filtered = new ArrayList<>();

        for(int i=0; i<baseBlocks.size(); i++){
            String [] blocks = data.get(i).trim().split(Constant.separator);
            String [] baseBlock = baseBlocks.get(i).trim().split(Constant.separator);

            List<String> temp = new ArrayList<>(Arrays.asList(blocks));
            for(String base: baseBlock) temp.removeAll(Collections.singleton(base));

            filtered.add(temp);
        }

        return filtered;
    }
}
