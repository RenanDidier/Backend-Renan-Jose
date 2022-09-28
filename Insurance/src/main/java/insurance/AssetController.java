package insurance;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
class AssetController {

  private final AssetsRepository repository;
  
  private final AssetModelAssembler assembler;


  AssetController(AssetsRepository repository, AssetModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }


//   Aggregate root
//   tag::get-aggregate-root[]
  @GetMapping("/assets")
  List<Assets> all() {
    return repository.findAll();
  }
  
  
//  @GetMapping("/asset")
//  CollectionModel<EntityModel<Assets>> all() {
//    List<EntityModel<Assets>> employees = repository.findAll().stream() //
//        .map(assembler::toModel) //
//        .collect(Collectors.toList());
//    return assembler.toCollectionModel(employees);
//
//  }
  
  // end::get-aggregate-root[]

  @PostMapping("/assets")
  Assets newEmployee(@RequestBody Assets newAsset) {
    return repository.save(newAsset);
    // jogar excecao quando nao conseguir escrever item no db
  }
  
//  @PostMapping("/employees")
//  ResponseEntity<?> newEmployee(@RequestBody Assets newEmployee) {
//
//    EntityModel<Assets> entityModel = assembler.toModel(repository.save(newEmployee));
//
//    return ResponseEntity //
//        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
//        .body(entityModel);
//  }

  // Single item
  
  
  @GetMapping("/assets/{id}")
  Assets one(@PathVariable Integer id) {
    
    return repository.findById(id)
      .orElseThrow(() -> new AssetNotFoundException(id));
  }
	
  
  
//  @GetMapping("/employees/{id}")
//  EntityModel<Assets> one(@PathVariable Long id) {
//
//    Assets employee = repository.findById(id) //
//        .orElseThrow(() -> new AssetsNotFoundException(id));
//
//    return assembler.toModel(employee);
//  }
 

  @PutMapping("/assets/{id}")
  Assets replaceEmployee(@RequestBody Assets newAsset, @PathVariable Integer id) {

    return repository.findById(id)
      .map(asset -> {
        asset.setAliquot(newAsset.getAliquot());
        asset.setItemName(newAsset.getItemName());
        asset.setEstimatedValue(newAsset.getEstimatedValue());
        return repository.save(asset);
      })
      .orElseGet(() -> {
        newAsset.setId(id);
        return repository.save(newAsset);
      });
  }


//  @PutMapping("/employees/{id}")
//  ResponseEntity<?> replaceEmployee(@RequestBody Assets newAssets, @PathVariable Long id) {
//
//    Employee updatedEmployee = repository.findById(id) //
//        .map(employee -> {
//          employee.setName(newEmployee.getName());
//          employee.setRole(newEmployee.getRole());
//          return repository.save(employee);
//        }) //
//        .orElseGet(() -> {
//          newEmployee.setId(id);
//          return repository.save(newEmployee);
//        });
//
//    EntityModel<Employee> entityModel = assembler.toModel(updatedEmployee);
//
//    return ResponseEntity //
//        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
//        .body(entityModel);
//  }

  @DeleteMapping("/assets/{id}")
  void deleteEmployee(@PathVariable Integer id) {
    repository.deleteById(id);
  }
  
//  @DeleteMapping("/employees/{id}")
//  ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
//
//    repository.deleteById(id);
//
//    return ResponseEntity.noContent().build();
//  }
 
}