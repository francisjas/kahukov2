package com.capstone.kuhako.controllers.CollectorModuleController;


import com.capstone.kuhako.models.CollectorModules.CollectAllDuePayments;
import com.capstone.kuhako.services.CollectorModuleServices.CollectAllDuePaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/collectorPage")
public class CollectAllDuePaymentsController {
    @Autowired
    CollectAllDuePaymentsService collectAllDuePaymentsService;

    @RequestMapping(value="/collectAllDuePayments/{collectorId}", method = RequestMethod.POST)
    public ResponseEntity<Object> createCollectAllDuePayments(@PathVariable Long collectorId, @RequestBody CollectAllDuePayments collectAllDuePayments) {
        collectAllDuePaymentsService.createCollectAllDuePayments(collectorId, collectAllDuePayments);
        return new ResponseEntity<>("CollectAllDuePayments created successfully", HttpStatus.CREATED);
    }

    @RequestMapping(value="/collectAllDuePayments", method = RequestMethod.GET)
    public ResponseEntity<Object> getCollectAllDuePayments() {
        return new ResponseEntity<>(collectAllDuePaymentsService.getCollectAllDuePayments(), HttpStatus.OK);
    }

    @RequestMapping(value="/duePayments/client/{collectorId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getDuePaymentsByCollectorId(@PathVariable Long collectorId) {
        return new ResponseEntity<>(collectAllDuePaymentsService.getCollectAllDuePaymentsByClientId(collectorId), HttpStatus.OK);
    }

    @RequestMapping(value = "/collectAllDuePayments/{collectorId}/{collectAllDuePaymentsId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteCollectAllDuePayments(@PathVariable Long collectorId, @PathVariable Long collectAllDuePaymentsId) {
        return collectAllDuePaymentsService.deleteCollectAllDuePayments(collectorId, collectAllDuePaymentsId);
    }

    @RequestMapping(value="/collectAllDuePayments/{collectorId}/{collectAllDuePaymentsId}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateCollectAllDuePayments(@PathVariable Long collectorId, @PathVariable Long collectAllDuePaymentsId, @RequestBody CollectAllDuePayments collectAllDuePayments) {
        return collectAllDuePaymentsService.updateCollectAllDuePayments(collectorId, collectAllDuePaymentsId, collectAllDuePayments);
    }
}