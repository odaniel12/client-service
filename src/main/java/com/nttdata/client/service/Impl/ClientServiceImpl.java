package com.nttdata.client.service.Impl;

import com.nttdata.client.model.Client;
import com.nttdata.client.repository.ClientRepository;
import com.nttdata.client.service.ClientService;
import com.nttdata.client.utils.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Flux<Client> findAllService() {
        return clientRepository.findAll();
    }

    @Override
    public Mono<Client> findByClientService(String idClient) {
        return clientRepository.findById(idClient);
    }

    @Override
    public Mono<Client> saveClientService(Client client) {
        Mono<Boolean> validate = clientRepository.existsById(client.getIdClient());
        return validate.flatMap(x-> {
            if(!x) {
                if( client.getTypeClient().equals(Constants.TYPE_CLIENT_EMPRESARIAL)
                        || client.getTypeClient().equals(Constants.TYPE_CLIENT_PERSONAL)) {
                    return clientRepository.save(client);
                } else {
                    return Mono.error(new Exception(Constants.MSG_FAIL_TYPE_CLIENT));
                }

            } else {
                return Mono.error(new Exception(Constants.MSG_FAIL_VALIDATE_SAVE));
            }
        });
    }

    @Override
    public Mono<Client> updateClientService(Client client) {
        Mono<Boolean> validate = clientRepository.existsById(client.getIdClient());
        return validate.flatMap(x->{
            if(x) {
                return clientRepository.save(client);
            } else {
                return Mono.error(new Exception(Constants.MSG_FAIL_VALIDATE_EXISTS));
            }
        });
    }

    @Override
    public Mono<Void> deleteClientService(String idClient) {
        Mono<Boolean> validate = clientRepository.existsById(idClient);
        return validate.flatMap(x->{
            if(x) {
                return clientRepository.deleteById(idClient);
            } else {
                return Mono.error(new Exception(Constants.MSG_FAIL_VALIDATE_EXISTS));
            }
        });

    }

}
