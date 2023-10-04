package com.capstone.kuhako.controllers;

import com.capstone.kuhako.models.Reseller;
import com.capstone.kuhako.services.ResellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class ResellerController {
    @Autowired
    ResellerService resellerService;

    // Create User
    @RequestMapping(value = "/reseller",method = RequestMethod.POST)
    public ResponseEntity<Object> createCollector(@RequestBody Reseller reseller){
        resellerService.createReseller(reseller);
        return new ResponseEntity<>("Reseller Account created Successfully", HttpStatus.CREATED);
    }
    //  Get all User
    @RequestMapping(value = "/reseller" , method = RequestMethod.GET)
    public ResponseEntity<Object> getUsername() {
        return new ResponseEntity<>(resellerService.getUsername(), HttpStatus.OK);
    }

    @RequestMapping(value="/myCollectors/{resellerId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getCollectorIdByReseller(@PathVariable Long resellerId) {
        return new ResponseEntity<>(resellerService.getCollectorIdByReseller(resellerId), HttpStatus.OK);
    }
    // Delete a U
    @RequestMapping (value = "/reseller/{resellerId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteClient(@PathVariable Long resellerId){
        return resellerService.deleteReseller(resellerId);
    }
    // Update a post
    @RequestMapping (value = "/reseller/{resellerId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateCollector(@PathVariable Long resellerId, @RequestBody Reseller reseller){
        return resellerService.updateReseller(resellerId, reseller);
    }

    @RequestMapping (value = "/reseller/{resellerId}/assign/{collectorId}/{clientId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> AssignCollectorToClient(@PathVariable Long resellerId, @PathVariable Long collectorId, @PathVariable Long clientId){
        return resellerService.assignCollectorToClient(resellerId, collectorId, clientId);
    }
}
