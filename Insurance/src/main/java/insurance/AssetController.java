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

  @GetMapping("/assets")
  List<Assets> all() {
    return repository.findAll();
  }

  @PostMapping("/assets")
  Assets createNewAsset(@RequestBody Assets newAsset) {
    return repository.save(newAsset);
    // jogar excecao quando nao conseguir escrever item no db
  }
  
  @GetMapping("/assets/{id}")
  Assets one(@PathVariable Integer id) {
    
    return repository.findById(id)
      .orElseThrow(() -> new AssetNotFoundException(id));
  }

  @PutMapping("/assets/{id}")
  Assets replaceAsset(@RequestBody Assets newAsset, @PathVariable Integer id) {

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
  void deleteAsset(@PathVariable Integer id) {
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