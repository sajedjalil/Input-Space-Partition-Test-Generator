package isp.services;

import isp.constants.IConstant;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static isp.constants.IConstant.dataLimit;

public class ACOCService{

    private static List<List<String>> result;

    public List<List<String>> generate(LinkedHashMap<String, String> data) {
        result = new ArrayList<>();
        dfs(data, List.of(), 0);
        return result;
    }

    private static void dfs(LinkedHashMap<String, String> data, List<String> taken, int layer){

        if( result.size() >= dataLimit ) {
            result = result.subList(0, dataLimit);
            return;
        }
        if( layer == data.size()) {
            result.add( new ArrayList<>(taken));
            return;
        }

        for(String block: ((String)data.values().toArray()[layer]).trim().split(IConstant.blockSeparator)) {
            List<String> temp = new ArrayList<>(taken);
            temp.add(block.trim());
            dfs(data, temp, layer+1);
        }
    }
}
