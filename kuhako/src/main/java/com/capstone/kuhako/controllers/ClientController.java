package com.capstone.kuhako.controllers;

import com.capstone.kuhako.models.Client;
import com.capstone.kuhako.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ClientController {

    @Autowired
    ClientService clientService;

    // Create User
    @RequestMapping(value = "/createClient",method = RequestMethod.POST)
    public ResponseEntity<Object> createClient(@RequestBody Client client){
        clientService.createClient(client);
        return new ResponseEntity<>("Client Account created Successfully", HttpStatus.CREATED);
    }
    //  Get all User
    @RequestMapping(value = "/getClient" , method = RequestMethod.GET)
    public ResponseEntity<Object> getUsername() {
        return new ResponseEntity<>(clientService.getUsername(), HttpStatus.OK);
    }
    // Delete a U
    @RequestMapping (value = "/deleteClient/{clientid}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteClient(@PathVariable int clientid){
        return clientService.deleteClient(clientid);
    }
    // Update a post
    @RequestMapping (value = "/updateClient/{clientid}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateClient(@PathVariable int clientid, @RequestBody Client client){
        return clientService.updateClient(clientid,client);
    }
}
