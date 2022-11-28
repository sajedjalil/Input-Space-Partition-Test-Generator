package isp.services;

import isp.models.ResultModel;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class GenerateService {

    public static List<ResultModel> generate(LinkedHashMap<String, String> data, String button){

        List<List<String>> rows;
        if( button.equals("acoc") ) {
            ACOCService service = new ACOCService();
            rows = service.generate(data);
        }
        else if( button.equals("ecc") ) {
            ECCService service = new ECCService();
            rows = service.generate(data);
        }
        else {
            BCCService service = new BCCService();
            rows = service.generate(data);
        }

        List<ResultModel> output = new ArrayList<>();
        for(int i=0; i<rows.size(); i++) output.add( new ResultModel(rows.get(i), i+1));
        return  output;
    }


    public static List<String> getHeaders(LinkedHashMap<String, String> data) {
        return new ArrayList<>(data.keySet());
    }
}
