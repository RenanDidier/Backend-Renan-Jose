
package insurance;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InsurancedAssetModelAssembler implements RepresentationModelAssembler<InsurancedAsset, EntityModel<InsurancedAsset>> {

    @Override
    public EntityModel<InsurancedAsset> toModel(InsurancedAsset insurancedAsset) {

        return EntityModel.of(insurancedAsset, //
                linkTo(methodOn(InsurancedAssetController.class).one(insurancedAsset.getInsurancedAssetId())).withSelfRel(),
                linkTo(methodOn(InsurancedAssetController.class).all()).withRel("insurancedAsset"));
    }
    public CollectionModel<EntityModel<InsurancedAsset>> toCollectionModel(List<EntityModel<InsurancedAsset>> insurancedAsset)  {

        return CollectionModel.of(insurancedAsset, linkTo(methodOn(InsurancedAssetController.class).all()).withSelfRel());
    }

}