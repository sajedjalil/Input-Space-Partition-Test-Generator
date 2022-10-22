package org.swe632.controllers;

import org.springframework.web.bind.annotation.*;
import org.swe632.models.Response;
import org.swe632.services.ACOCService;
import org.swe632.services.BCCService;
import org.swe632.services.ECCService;
import org.swe632.services.EstimateService;

import java.util.List;

@RestController()
@CrossOrigin
public class TestGenerationController {

    final EstimateService estimateService;

    final ACOCService acocService;
    final ECCService eccService;

    final BCCService bccService;

    public TestGenerationController(EstimateService estimateService, ACOCService acocService, ECCService eccService, BCCService bccService) {
        this.estimateService = estimateService;
        this.acocService = acocService;
        this.eccService = eccService;
        this.bccService = bccService;
    }

    private enum Criteria{ACOC, ECC, BCC}
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
