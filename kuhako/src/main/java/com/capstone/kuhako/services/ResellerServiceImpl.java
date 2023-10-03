package com.capstone.kuhako.services;

import com.capstone.kuhako.models.Client;
import com.capstone.kuhako.models.Collector;
import com.capstone.kuhako.models.Reseller;
import com.capstone.kuhako.repositories.ClientRepository;
import com.capstone.kuhako.repositories.CollectorRepository;
import com.capstone.kuhako.repositories.ResellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResellerServiceImpl implements ResellerService {
    @Autowired
    private ResellerRepository resellerRepository;
    @Autowired
    private CollectorRepository collectorRepository;
    @Autowired
    private ClientRepository clientRepository;

    // Create reseller
    public void createReseller(Reseller reseller){
        resellerRepository.save(reseller);
    }

    // Get all reseller
    public Iterable<Reseller> getUsername(){
        return resellerRepository.findAll();
    }

    // Delete reseller
    public ResponseEntity deleteReseller(Long id){
        resellerRepository.deleteById(id);
        return new ResponseEntity<>("User Deleted successfully", HttpStatus.OK);
    }

    // Update a reseller
    public ResponseEntity updateReseller(Long id, Reseller reseller){
        // Find the reseller  to update
        Reseller resellerForUpdate = resellerRepository.findById(id).get();
        // Updating the reseller name and password
        resellerForUpdate.setFullName(reseller.getFullName());
        resellerForUpdate.setAddress(reseller.getAddress());
        // Saving and Updating a reseller
        resellerRepository.save(resellerForUpdate);
        return new ResponseEntity<>("User updated Successfully",HttpStatus.OK);
    }

    // AssignCollectorToClient
    public ResponseEntity assignCollectorToClient(Long resellerId, Long collectorId, Long clientId){
        Reseller reseller = resellerRepository.findById(resellerId).orElse(null);
        if(reseller != null){
            Collector collector = collectorRepository.findById(collectorId).orElse(null);
            Client client = clientRepository.findById(clientId).orElse(null);
            if (collector!=null && client!=null) {
                if (reseller.getCollectors().contains(collector)){
                    client.getContract().setCollector(collector);
                    collector.getContracts().add(client.getContract());
                    client.setCollector(collector);
                    collector.getClients().add(client);
                    collectorRepository.save(collector);
                    clientRepository.save(client);
                    return new ResponseEntity<>("Collector assigned to Client",HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Collector does not belong to Reseller",HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>("Collector/Client not found",HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Reseller not found",HttpStatus.NOT_FOUND);
        }
    }
}
