package com.bank.client.controller;

import com.bank.client.model.Client;
import com.bank.client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/clientBank")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/findAll")
    public Flux<Client> finAll(){
        return clientService.findAllService();
    }

    @GetMapping("/findClient/{idClient}")
    public Mono<Client> findByClient(@PathVariable("idClient") String idClient) {

        return clientService.findByClientService(idClient);

    }
    @PostMapping("/save")
    public Mono<Client> saveClient(@Validated @RequestBody Client client){
        return clientService.saveClientService(client);
    }

    @DeleteMapping("/delete/{idClient}")
    public Mono<Void> deleteClient(@PathVariable("idClient") String idClient){
        return clientService.deleteClientService(idClient);
    }

}
