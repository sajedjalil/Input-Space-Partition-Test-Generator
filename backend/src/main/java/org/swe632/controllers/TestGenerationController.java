package org.swe632.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.swe632.constants.Constant.Criteria;
import org.swe632.models.Response;
import org.swe632.services.*;

import java.util.List;

@RestController()
@CrossOrigin
public class TestGenerationController {

    final EstimateService estimateService;

    final ACOCService acocService;
    final ECCService eccService;

    final BCCService bccService;

    @Autowired
    ValidityService validityService;

    public TestGenerationController(EstimateService estimateService, ACOCService acocService, ECCService eccService, BCCService bccService) {
        this.estimateService = estimateService;
        this.acocService = acocService;
        this.eccService = eccService;
        this.bccService = bccService;
    }

    @PostMapping("/checkvalidity")
    @ResponseBody
    public Response checkDataValidity(@RequestParam List<String> data, @RequestParam Criteria criteria,
                                      @RequestParam(required = false) List<String> baseBlocks) {
        return validityService.checkIfValid(data, criteria, baseBlocks);
    }

    @PostMapping("/estimate")
    @ResponseBody
    public Response estimateTestCount(@RequestParam List<String> data, @RequestParam Criteria criteria,
                                              @RequestParam(required = false) List<String> baseBlocks){

        if( !estimateService.isDataValid(data) ) return Response.builder().type(Response.Type.FAILED).
                message("No characteristic can be empty.").build();

        if(criteria.equals(Criteria.ACOC)) return Response.builder().type(Response.Type.OK).object(
                estimateService.estimateACOC(data).toString()).build();
        if(criteria.equals(Criteria.ECC)) return Response.builder().type(Response.Type.OK).object(
                estimateService.estimateECC(data).toString()).build();
        if(criteria.equals(Criteria.BCC)) {
            if( baseBlocks == null || baseBlocks.size()==0 ) return Response.builder().type(Response.Type.FAILED).message(
                    "Base blocks are required for BCC").build();
            else if( !estimateService.isDataValid(baseBlocks) || baseBlocks.size() < data.size()) return Response.builder().type(Response.Type.FAILED).message(
                    "At least one base block required for each characteristics.").build();

            return Response.builder().type(Response.Type.OK).object(
                    estimateService.estimateBCC(data, baseBlocks).toString()).build();
        }

        return Response.builder().type(Response.Type.FAILED).
                message("Could not build an estimate with provided data and criterion.").build();
    }


    @PostMapping("/generate")
    @ResponseBody
    public Response generateTests(@RequestParam List<String> data, @RequestParam Criteria criteria,
                                              @RequestParam(required = false) List<String> baseBlocks){

        if( !estimateService.isDataValid(data) ) return Response.builder().type(Response.Type.FAILED).
                message("No characteristic can be empty.").build();

        if(criteria.equals(Criteria.ACOC)) return Response.builder().type(Response.Type.OK).object(
                acocService.generateACOC(data).toString()).build();
        if(criteria.equals(Criteria.ECC)) return Response.builder().type(Response.Type.OK).object(
                eccService.generateECC(data).toString()).build();
        if(criteria.equals(Criteria.BCC)) {
            if( baseBlocks == null || baseBlocks.size()==0 ) return Response.builder().type(Response.Type.FAILED).message(
                    "Base blocks are required for BCC").build();
            else if( !estimateService.isDataValid(baseBlocks) || baseBlocks.size() < data.size()) return Response.builder().type(Response.Type.FAILED).message(
                    "At least one base block required for each characteristics.").build();

            return Response.builder().type(Response.Type.OK).object(
                    bccService.generateBCC(data, baseBlocks).toString()).build();
        }

        return Response.builder().type(Response.Type.FAILED).
                message("Could not build an estimate with provided data and criterion.").build();
    }



}
