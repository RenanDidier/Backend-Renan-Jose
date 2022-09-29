package insurance;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class InsuranceModelAssembler implements RepresentationModelAssembler<Insurances, EntityModel<Insurances>> {

    @Override
    public EntityModel<Insurances> toModel(Insurances insurance) {

        return EntityModel.of(insurance, //
                linkTo(methodOn(InsuranceController.class).one(insurance.getInsurancePolicyNumber())).withSelfRel(),
                linkTo(methodOn(InsuranceController.class).all()).withRel("insurances"));
    }
    public CollectionModel<EntityModel<Insurances>> toCollectionModel(List<EntityModel<Insurances>> insurances)  {

        return CollectionModel.of(insurances, linkTo(methodOn(InsuranceController.class).all()).withSelfRel());
    }
}