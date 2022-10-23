package org.swe632.services;

import org.springframework.stereotype.Service;
import org.swe632.constants.Constant;

import java.util.ArrayList;
import java.util.List;

@Service
public class ACOCService {
    private List<List<String>> result;

    public List<List<String>> generateACOC(List<String> data) {
        result = new ArrayList<>();
        dfs(data, List.of(), 0);
        return result;
    }

    private void dfs(List<String> data, List<String> taken, int layer){

        if( layer == data.size()) {
            result.add( new ArrayList<>(taken));
            return;
        }

        for(String block: data.get(layer).trim().split(Constant.separator)) {
            List<String> temp = new ArrayList<>(taken);
            temp.add(block);
            dfs(data, temp, layer+1);
        }
    }
}
