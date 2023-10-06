package com.capstone.kuhako.controllers.ResellersControllers;

import com.capstone.kuhako.models.Client;
import com.capstone.kuhako.models.Reseller;
import com.capstone.kuhako.models.ResellerModule.AssignCollectors;
import com.capstone.kuhako.repositories.ResellerRepository;
import com.capstone.kuhako.services.ResellerService;
import com.capstone.kuhako.services.ResellerServices.AssignCollectorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/reseller")
public class AssignCollectorsController {

    @Autowired
    ResellerService resellerService;

    @RequestMapping (value = "/assignCollectorsController/{resellerId}/{collectorId}/{clientId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> AssignCollectorToClient(@PathVariable Long resellerId, @PathVariable Long collectorId, @PathVariable Long clientId){
        return resellerService.assignCollectorToClient(resellerId, collectorId, clientId);
    }


   /* @Autowired
    AssignCollectorsService assignCollectorsService;
    
    @Autowired
    private ResellerRepository resellerRepository;

    @RequestMapping(value="/assignCollectors/{resellerId}", method = RequestMethod.POST)
    public ResponseEntity<Object> createAssignCollectors(@PathVariable Long resellerId ,@RequestBody AssignCollectors assignCollectors) {
        Reseller reseller = resellerRepository.findById(resellerId).orElse(null);
        if (reseller != null) {
            assignCollectorsService.createAssignCollectors(resellerId,assignCollectors);
            return new ResponseEntity<>("Assign Collectors created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Assign Collectors Records does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/assignCollectors", method = RequestMethod.GET)
    public ResponseEntity<Object> getAssignCollectors() {
        return new ResponseEntity<>(assignCollectorsService.getAssignCollectors(), HttpStatus.OK);
    }

    @RequestMapping(value="/assignCollectors/reseller/{resellerId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getAssignCollectorsByResellerId(@PathVariable Long resellerId) {
        return new ResponseEntity<>(assignCollectorsService.getAssignCollectorsByResellerId(resellerId), HttpStatus.OK);
    }

    @RequestMapping(value = "/assignCollectors/{resellerId}/{assignCollectors_id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteAssignCollectors(@PathVariable Long resellerId,@PathVariable Long assignCollectors_id) {
        return assignCollectorsService.deleteAssignCollectors(resellerId,assignCollectors_id);
    }
    @RequestMapping(value="/assignCollectors/{resellerId}/{assignCollectors_id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateAssignCollectors(@PathVariable Long resellerId,@PathVariable Long assignCollectors_id, @RequestBody AssignCollectors assignCollectors) {
        return assignCollectorsService.updateAssignCollectors(resellerId,assignCollectors_id, assignCollectors);
    }*/
}
