package com.cunningbird.contractfirst.graphqls.contract;

import com.cunningbird.contractfirst.graphqls.contract.mock.Application;
import com.cunningbird.contractfirst.graphqls.contract.mock.PetstoreClient;
import com.cunningbird.contractfirst.graphqls.contract.model.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = Application.class,
        properties = { "server.port=8080" }
)
public class CapabilityTest {

    @Autowired
    private PetstoreClient client;

    @Test
    public void testListPets() {
        Pet expected = new Pet();
        expected.setId(1L);
        expected.setName("Ricardo");
        expected.setTag("Cat");

        Pet actual = client.getPetById(1L).block();

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getName(), actual.getName());
        Assertions.assertEquals(expected.getTag(), actual.getTag());
    }

//    @Test
//    public void testShowPetById() {
//
//    }
//
//    @Test
//    public void testCreatePet() {
//
//    }
}
