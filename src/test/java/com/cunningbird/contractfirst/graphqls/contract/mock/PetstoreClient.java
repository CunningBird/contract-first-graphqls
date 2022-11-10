package com.cunningbird.contractfirst.graphqls.contract.mock;

import com.cunningbird.contractfirst.graphqls.contract.model.Pet;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class PetstoreClient {

    private final HttpGraphQlClient client = HttpGraphQlClient.builder(WebClient.create("http://localhost:8080/graphql")).build();

    public Mono<Pet> getPetById(Long petId) {
        String request = "query getPetById($petId: ID!) {" +
                "    pet(id: $petId) {" +
                "        id" +
                "        name" +
                "        tag" +
                "    }" +
                "}";

        return client.document(request).variable("petId", petId).retrieve("pet").toEntity(Pet.class);
    }
}
