package com.capstone.kuhako.controllers;

import com.capstone.kuhako.models.Collector;
import com.capstone.kuhako.services.CollectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class CollectorController {

    @Autowired
    CollectorService collectorService;

    // Create User
    @RequestMapping(value = "/createCollector",method = RequestMethod.POST)
    public ResponseEntity<Object> createCollector(@RequestBody Collector collector){
        collectorService.createCollector(collector);
        return new ResponseEntity<>("Collector Account created Successfully", HttpStatus.CREATED);
    }
    //  Get all User
    @RequestMapping(value = "/getCollector" , method = RequestMethod.GET)
    public ResponseEntity<Object> getUsername() {
        return new ResponseEntity<>(collectorService.getUsername(), HttpStatus.OK);
    }
    // Delete a U
    @RequestMapping (value = "/deleteCollector/{collectorid}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteClient(@PathVariable int collectorid){
        return collectorService.deleteCollector(collectorid);
    }
    // Update a post
    @RequestMapping (value = "/updateCollector/{collectorid}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateCollector(@PathVariable int collectorid, @RequestBody Collector collector){
        return collectorService.updateCollector(collectorid,collector);
    }
}
