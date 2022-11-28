package isp.services;

import isp.constants.IConstant;
import isp.models.Response;

import java.util.*;
import java.util.regex.Pattern;

public class ValidityService {


    public Response checkIfValid(String rawInput){

        Response response = checkForInvalidInput(rawInput);
        if( response.type == Response.Type.FAILED) return response;

        LinkedHashMap<String, String> data = getdata(rawInput);
        response = isDataValid(data);
        if( response.type == Response.Type.FAILED) return response;

        response = checkDuplicateData(data);
        return response;
    }

    public Response isDataValid(LinkedHashMap<String, String> data) {
        if(data == null || data.size() ==0) return new Response(Response.Type.FAILED, "Input can not be empty.");

        List<String> empty = new ArrayList<>();
        for(String key: data.keySet()) {
            String characteristic = data.get(key);
            if( characteristic.trim().length() == 0) empty.add( key+" cannot be empty.");
        }

        if( empty.size() != 0 ) return new Response(Response.Type.FAILED, String.join("\n", empty));
        return new Response(Response.Type.OK, null);
    }

    public Response checkForInvalidInput(String rawInput) {
        String [] lines = Pattern.compile("\n").splitAsStream(rawInput).map(s -> s.trim().split(IConstant.lineSeparator))
                .flatMap(Arrays::stream).toArray(String[]::new);

        boolean invalid = false;
        for(String line: lines) {
            String [] blocks = line.trim().split("=");
            if(blocks.length > 2 ) {invalid = true; break;}
            if( blocks.length == 2) {
                if( !blocks[1].trim().startsWith("[") || !blocks[1].trim().endsWith("]")) {invalid = true; break;}
            }
            if( blocks.length == 1) {
                if( !blocks[0].trim().startsWith("[") || !blocks[0].trim().endsWith("]")) {invalid = true; break;}
            }
        }
        if(invalid) return new Response(Response.Type.FAILED, IConstant.invalidDataFormatMsg);
        else return new Response(Response.Type.OK, IConstant.invalidDataFormatMsg);
    }

    public LinkedHashMap<String, String> getdata(String rawInput){

        LinkedHashMap<String, String> result = new LinkedHashMap<>();
        String [] lines = Pattern.compile("\n").splitAsStream(rawInput).map(s -> s.trim().split(";"))
                .flatMap(Arrays::stream).toArray(String[]::new);

        for(int i=0; i<lines.length; i++) {
            String line = lines[i];
            String [] blocks = line.trim().split("=");

            if( blocks.length == 2) {
                result.put(blocks[0].trim(), blocks[1].trim().substring(1, blocks[1].trim().length()-1));
            }
            if( blocks.length == 1) {
                result.put("Characteristics "+(i+1), blocks[0].trim().substring(1, blocks[0].trim().length()-1));
            }
        }

        return result;
    }

    private Response checkDuplicateData(LinkedHashMap<String, String> data) {

        HashSet<String> duplicates = new HashSet<>();
        for(String key: data.keySet()) {

            String line = data.get(key);
            String [] blocks = line.trim().split(IConstant.blockSeparator);
            HashSet<String> set = new HashSet<>();

            for(String block: blocks) {
                if( set.contains(block.trim()) ) duplicates.add(key+": "+block.trim());
                else set.add(block.trim());
            }
        }

        String message = "Please remove the following duplicates:";

        if( duplicates.size() == 0) return new Response(Response.Type.OK, null);
        return new Response(Response.Type.FAILED, message+ "\n"+ String.join("\n", duplicates));
    }
}
