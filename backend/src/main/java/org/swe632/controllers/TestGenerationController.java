package org.swe632.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.swe632.models.Response;
import org.swe632.services.TestingService;

import java.util.List;

@RestController()
@CrossOrigin
public class TestGenerationController {

    @Autowired
    TestingService testingService;
    private enum Criteria{ACOC, ECC, BCC}
    @PostMapping("/estimate")
    @ResponseBody
    public Response getUserByEmailAndPassword(@RequestParam List<String> data, @RequestParam Criteria criteria,
                                              @RequestParam(required = false) List<String> baseBlocks){

        if( !testingService.isDataValid(data) ) return Response.builder().type(Response.Type.FAILED).
                message("No characteristic can be empty.").build();

        if(criteria.equals(Criteria.ACOC)) return Response.builder().type(Response.Type.OK).object(
                testingService.estimateACOC(data).toString()).build();
        if(criteria.equals(Criteria.ECC)) return Response.builder().type(Response.Type.OK).object(
                testingService.estimateECC(data).toString()).build();
        if(criteria.equals(Criteria.BCC)) {
            if( baseBlocks == null || baseBlocks.size()==0 ) return Response.builder().type(Response.Type.FAILED).message(
                    "Base blocks are required for BCC").build();
            else if( !testingService.isDataValid(baseBlocks) || baseBlocks.size() < data.size()) return Response.builder().type(Response.Type.FAILED).message(
                    "At least one base block required for each characteristics.").build();

            return Response.builder().type(Response.Type.OK).object(
                    testingService.estimateBCC(data, baseBlocks).toString()).build();
        }

        return Response.builder().type(Response.Type.FAILED).
                message("Could not build an estimate with provided data and criterion.").build();
    }




}
