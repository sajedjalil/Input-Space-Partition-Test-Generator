package org.swe632.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.swe632.constants.Constant;
import org.swe632.constants.Constant.*;
import org.swe632.models.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class ValidityService {

    public Response checkIfValid(List<String> data, Criteria criteria,
                                  @RequestParam(required = false) List<String> baseBlocks){

        Response response = checkDuplicateData(data, false);
        if( response.getType() == Response.Type.FAILED) return response;

        response = checkDuplicateData(baseBlocks, true);
        if( response.getType() == Response.Type.FAILED) return response;

        if( criteria == Criteria.BCC) response = checkIfBaseBlockContainsValidItems(data, baseBlocks);
        return response;
    }


    private Response checkDuplicateData(List<String> data, boolean isBaseBlock) {

        HashSet<String> duplicates = new HashSet<>();
        for(String line: data) {
            String [] blocks = line.trim().split(Constant.separator);
            HashSet<String> set = new HashSet<>();

            for(String block: blocks) {
                if( set.contains(block) ) duplicates.add(block);
                else set.add(block);
            }
        }

        String message = "Please remove duplicate ";
        if(isBaseBlock) message += "base ";

        if( duplicates.size() == 0) return Response.builder().type(Response.Type.OK).build();
        else if( duplicates.size() == 1) return Response.builder().type(Response.Type.FAILED).object(message+"block: "+ String.join(", ", duplicates)).build();
        else return Response.builder().type(Response.Type.FAILED).object(message+"blocks: "+ String.join(", ", duplicates)).build();
    }

    private Response checkIfBaseBlockContainsValidItems(List<String> data, List<String> baseBlocks) {

        StringBuilder message = new StringBuilder();
        for(int i=0; i<baseBlocks.size(); i++) {
            List<String> blocks = Arrays.asList(data.get(i).trim().split(Constant.separator));
            String [] baseBlock = baseBlocks.get(i).trim().split(Constant.separator);

            ArrayList<String> notFound = new ArrayList<>();
            for(String block: baseBlock) {
                if( !blocks.contains( block)) notFound.add(block);
            }

            if( notFound.size() > 0)
                message.append("Base block: ").append(String.join(", ", notFound))
                        .append(" not found in relevant characteristic.").append("\n");
        }

        if( message.isEmpty() ) return Response.builder().type(Response.Type.OK).build();
        else return Response.builder().type(Response.Type.FAILED).object(message).build();
    }
}
