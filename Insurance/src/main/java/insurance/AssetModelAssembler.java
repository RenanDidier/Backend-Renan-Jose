package insurance;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class AssetModelAssembler implements RepresentationModelAssembler<Assets, EntityModel<Assets>> {

  @Override
  public EntityModel<Assets> toModel(Assets asset) {

    return EntityModel.of(asset, //
        linkTo(methodOn(AssetController.class).one(asset.getId() )).withSelfRel(),
        linkTo(methodOn(AssetController.class).all()).withRel("assets"));
  }
  public CollectionModel<EntityModel<Assets>> toCollectionModel(List<EntityModel<Assets>> assets)  {

	  return CollectionModel.of(assets, linkTo(methodOn(AssetController.class).all()).withSelfRel());
	}
}