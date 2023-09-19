package com.capstone.kuhako.services.ClientModuleServices;

import com.capstone.kuhako.models.User;
import com.capstone.kuhako.services.UserService;
import com.capstone.kuhako.models.ClientModules.ClientPaymentRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface ClientPaymentRecordsService {
    void createClientPaymentRecords(ClientPaymentRecords clientPaymentRecords);

    Iterable<ClientPaymentRecords> getClientPaymentRecords();

    ResponseEntity deleteClientPaymentRecords(Long id);

    ResponseEntity updateClientPaymentRecords(Long id, ClientPaymentRecords clientPaymentRecords);

}
