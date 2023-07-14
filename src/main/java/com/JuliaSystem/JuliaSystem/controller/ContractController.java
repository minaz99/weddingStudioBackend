package com.JuliaSystem.JuliaSystem.controller;

import com.JuliaSystem.JuliaSystem.contract.Contract;
import com.JuliaSystem.JuliaSystem.contract.ContractService;
import com.JuliaSystem.JuliaSystem.contract.ContractStage;
import com.JuliaSystem.JuliaSystem.contract.ContractStatus;
import io.jsonwebtoken.lang.Objects;
import lombok.AllArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/contract")
public class ContractController {
    private final ContractService contractService;

    @GetMapping
    public List<Contract> getAllContracts() {
        return contractService.getAllContracts();
    }
    @GetMapping("/{contractID}")
    public Contract getContractByID(@PathVariable("contractID") Integer contractID) {
        return contractService.getContractByID(contractID);
    }


    /*@GetMapping("/{brideName}")
    public List<Contract> getContractsByBrideName(@PathVariable("brideName") String brideName) {
        return contractService.getContractsByBrideName(brideName);
    }*/

    @GetMapping("/filter")
    public List<Contract> getContractsMatchingCriteria (@RequestParam(required = false) String eventType, @RequestParam(required = false) Date date,
                                                        @RequestParam(required = false) String eventLocation,
                                                        @RequestParam (required = false)ContractStage contractStage,
                                                        @RequestParam (required = false)String photographer,
                                                        @RequestParam (required = false)String video,
                                                        @RequestParam (required = false)ContractStatus status,
                                                        @RequestParam (required = false)String brideName,
                                                        @RequestParam (required = false) Integer month
    ) {

        return contractService.filterContractsByCriteria(eventType,date,eventLocation,contractStage,photographer,video,status,month,brideName);
       // return contractService.getContractsByBrideName(brideName);
    }

    @GetMapping("/multipleFilters")
    public List<Contract> getContractsMatchingCriteria (@RequestParam(required = false) String brideName,
                                                        @RequestParam(required = false) String eventType,
                                                        @RequestParam (required = false)String eventLocation,
                                                        @RequestParam (required = false) ContractStage contractStage
    ){

        return contractService.filterContractsByMultipleFilters(brideName,eventType,eventLocation,contractStage);
    }


    @PostMapping("/{contractID}")
    public void addContractStuff(@PathVariable("contractID") Integer contractID, @RequestParam String comment,
    @RequestParam String photographer, @RequestParam String video, @RequestParam ContractStage contractStage) {
        if(!comment.isEmpty())
        contractService.addCommentsToContract(contractID,comment);
        if(!photographer.isEmpty())
            contractService.setPhotographer(contractID,photographer);
        if(!video.isEmpty())
            contractService.setVideo(contractID,video);
        if(!contractStage.toString().isEmpty())
            contractService.setContractStage(contractID,contractStage);

    }

    @GetMapping("/{contractID}/paymentData")
    public List<Object> getContractPaymentsData(@PathVariable("contractID") Integer contractID){
        return contractService.getContractPaymentsData(contractID);
    }

    @PostMapping
    public void newContract(@RequestParam String secondPartyName,@RequestParam String brideName,@RequestParam String
            groomName, @RequestParam String eventType,
                            @RequestParam String eventLocation, @RequestParam Date eventDate,
                            @RequestParam String civilID,@RequestParam Integer phone1, @RequestParam Integer phone2,
                            @RequestParam Integer contractPrice, @RequestParam String componentIDs,
                            @RequestParam Integer packageID, @RequestParam String comments){
        contractService.newContract(secondPartyName, brideName, groomName, eventType, eventLocation, eventDate,
                civilID, phone1, phone2, contractPrice, componentIDs ,packageID, comments);
    }

    @GetMapping("/month")
    public List<Contract> getContractsInMonth(@RequestParam Integer month) {
         return contractService.getContractsInSameMonth(month);
    }
    @GetMapping("/search")
    public List<Contract> getContractsBySearch(@RequestParam String search) {
        return contractService.getContractsFromSearch(search);
    }

}
