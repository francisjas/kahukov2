package com.capstone.kuhako.controllers.CollectorModuleController;


import com.capstone.kuhako.models.Collector;
import com.capstone.kuhako.models.CollectorModules.CollectPayments;
import com.capstone.kuhako.repositories.CollectorRepository;
import com.capstone.kuhako.services.CollectorModuleServices.CollectPaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/collector")

public class CollectPaymentsController {
    @Autowired
    CollectPaymentsService collectPaymentsService;
    @Autowired
    private CollectorRepository collectorRepository;

    @RequestMapping(value="/collectPayments/{collectorId}", method = RequestMethod.POST)
    public ResponseEntity<Object> createCollectPayments(@PathVariable Long collectorId, @RequestBody CollectPayments collectPayments) {
        Collector collector = collectorRepository.findById(collectorId).orElse(null);
        if (collector != null) {
            collectPaymentsService.createCollectPayments(collectorId, collectPayments);
            return new ResponseEntity<>("CollectPayments created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Collector does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/collectPayments", method = RequestMethod.GET)
    public ResponseEntity<Object> getCollectPayments() {
        return new ResponseEntity<>(collectPaymentsService.getCollectPayments(), HttpStatus.OK);
    }

    @RequestMapping(value="/collectPayments/collector/{collectorId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getCollectPaymentsByCollectorId(@PathVariable Long collectorId) {
        return new ResponseEntity<>(collectPaymentsService.getCollectPaymentsByCollectorId(collectorId), HttpStatus.OK);
    }

    @RequestMapping(value = "/collectPayments/{collectorId}/{collectPaymentsId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteCollectPayments(@PathVariable Long collectorId, @PathVariable Long collectPaymentsId) {
        return collectPaymentsService.deleteCollectPayments(collectorId, collectPaymentsId);
    }

    @RequestMapping(value="/collectPayments/{collectorId}/{collectPaymentsId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateCollectPayments(@PathVariable Long collectorId, @PathVariable Long collectPaymentsId, @RequestBody CollectPayments collectPayments) {
        return collectPaymentsService.updateCollectPayments(collectorId, collectPaymentsId, collectPayments);
    }
}