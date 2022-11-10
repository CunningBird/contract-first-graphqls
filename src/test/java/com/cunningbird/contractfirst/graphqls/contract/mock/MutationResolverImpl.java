package com.cunningbird.contractfirst.graphqls.contract.mock;

import com.cunningbird.contractfirst.graphqls.contract.api.MutationResolver;
import com.cunningbird.contractfirst.graphqls.contract.model.InputPet;
import com.cunningbird.contractfirst.graphqls.contract.model.Pet;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MutationResolverImpl implements MutationResolver {

    @MutationMapping
    @Override
    public Pet addPet(@Argument InputPet pet) throws Exception {
        Pet response = new Pet();
        response.setId(pet.getId());
        response.setName(pet.getName());
        response.setTag(pet.getTag());

        return response;
    }
}
