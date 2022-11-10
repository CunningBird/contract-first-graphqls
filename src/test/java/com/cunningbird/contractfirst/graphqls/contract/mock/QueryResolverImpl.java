package com.cunningbird.contractfirst.graphqls.contract.mock;

import com.cunningbird.contractfirst.graphqls.contract.api.QueryResolver;
import com.cunningbird.contractfirst.graphqls.contract.model.Pet;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class QueryResolverImpl implements QueryResolver {

    @QueryMapping
    @Override
    public List<Pet> pets() throws Exception {
        Pet pet1 = new Pet();
        pet1.setId(1L);
        pet1.setName("Ricardo");
        pet1.setTag("Cat");

        Pet pet2 = new Pet();
        pet2.setId(1L);
        pet2.setName("Ricardo");
        pet2.setTag("Cat");

        List<Pet> pets = new ArrayList<>();
        pets.add(pet1);
        pets.add(pet2);

        return pets;
    }

    @QueryMapping
    @Override
    public Pet pet(@Argument Long id) throws Exception {

        Pet pet = new Pet();
        pet.setId(1L);
        pet.setName("Ricardo");
        pet.setTag("Cat");

        return pet;
    }
}
