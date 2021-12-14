package com.nttdata.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor

@Data
@Document(collection = "client")
public class Client {
    @Id
    private String idClient;
    private String typeClient;
    private String name;
    private String lastName;
    private String dni;
    private String address;
    private String ruc;

}
