package com.capstone.kuhako.controllers.ClientModuleController;


import com.capstone.kuhako.models.Client;
import com.capstone.kuhako.models.ClientModules.PayDues;
import com.capstone.kuhako.models.JoinModule.Contracts;
import com.capstone.kuhako.repositories.ClientRepository;
import com.capstone.kuhako.repositories.CollectorRepository;
import com.capstone.kuhako.repositories.JoinModuleRepository.ContractsRepository;
import com.capstone.kuhako.services.ClientModuleServices.PayDuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;


@RestController
@CrossOrigin
@RequestMapping("/clientPage")
public class PayDuesController {
    @Autowired
    PayDuesService payDuesService;
    @Autowired
    private CollectorRepository collectorRepository;
    @Autowired
    private ContractsRepository contractsRepository;
    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping(value="/payDues/{clientId}", method = RequestMethod.POST)
    public ResponseEntity<Object> createPayDues(@PathVariable Long clientId, @RequestParam("contractId") Long contractId, @RequestParam("amountPayment") double amountPayment,@RequestParam("referenceNumber") String referenceNumber, @RequestParam("paymentType") String paymentType, @RequestParam("base64Data")String base64Data, @RequestParam("contentType")String contentType) {
        Client client = clientRepository.findById(clientId).orElse(null);
        Contracts contracts = contractsRepository.findById(contractId).orElse(null);
        if (client != null && contracts != null) {
            if(contracts.getClient().equals(client)){
                if(contracts.getDebtRemaining() >= amountPayment){
                    byte[] data = Base64.getDecoder().decode(base64Data);
                    PayDues payDues = new PayDues();
                    payDues.setContracts(contracts);
                    payDues.setAmountPayment(amountPayment);
                    payDues.setReferenceNumber(referenceNumber);
                    payDues.setPaymentType(paymentType);
                    payDues.setTransactionProof(data);
                    payDues.setTransactionProofContentType(contentType);
                    payDuesService.createPayDues(clientId,payDues);
                    return new ResponseEntity<>("Pay Dues created successfully", HttpStatus.CREATED);
                }else{
                    return new ResponseEntity<>("Payment exceeds remaining debt", HttpStatus.NOT_FOUND);
                }
            }else {
                return new ResponseEntity<>("Client is not in charge on this contract", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Client/Contract does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/payDues", method = RequestMethod.GET)
    public ResponseEntity<Object> getPayDues() {
        return new ResponseEntity<>(payDuesService.getPayDues(), HttpStatus.OK);
    }

    @RequestMapping(value="/payDues/client/{clientId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getPayDuesByClientId(@PathVariable Long clientId) {
        return new ResponseEntity<>(payDuesService.getPayDuesByClientId(clientId), HttpStatus.OK);
    }

    @RequestMapping(value = "/payDues/{clientId}/{payDues_id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deletePayDues(@PathVariable Long clientId,@PathVariable Long payDues_id) {
        return payDuesService.deletePayDues(clientId,payDues_id);
    }

    @RequestMapping(value="/payDues/{clientId}/{payDuesid}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updatePayDues(@PathVariable Long clientId,@PathVariable Long payDuesid, @RequestBody PayDues payDues) {
        return payDuesService.updatePayDues(clientId,payDuesid, payDues);
    }
}