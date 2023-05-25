package com.capstone.kuhako.controllers;

import com.capstone.kuhako.models.Reseller;
import com.capstone.kuhako.services.ResellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ResellerController {
    @Autowired

    ResellerService resellerService;

    // Create User
    @RequestMapping(value = "/registerReseller",method = RequestMethod.POST)
    public ResponseEntity<Object> createCollector(@RequestBody Reseller reseller){
        resellerService.createReseller(reseller);
        return new ResponseEntity<>("Reseller Account created Successfully", HttpStatus.CREATED);
    }
    //  Get all User
    @RequestMapping(value = "/getReseller" , method = RequestMethod.GET)
    public ResponseEntity<Object> getUsername() {
        return new ResponseEntity<>(resellerService.getUsername(), HttpStatus.OK);
    }
    // Delete a U
    @RequestMapping (value = "/deleteReseller/{resellerid}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteClient(@PathVariable int resellerid){
        return resellerService.deleteReseller(resellerid);
    }
    // Update a post
    @RequestMapping (value = "/updateReseller/{resellerid}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateCollector(@PathVariable int resellerid, @RequestBody Reseller reseller){
        return resellerService.updateReseller(resellerid,reseller);
    }
}
