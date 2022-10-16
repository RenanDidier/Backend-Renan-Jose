package insurance;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class InsuranceController {
    private final InsurancesRepository repository;

    private final InsuranceModelAssembler assembler;


    InsuranceController(InsurancesRepository repository, InsuranceModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }


    //   Aggregate root
//   tag::get-aggregate-root[]
    @GetMapping("/insurance")
    List<Insurances> all() {
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

    @PostMapping("/insurance")
    Insurances newEmployee(@RequestBody Insurances newInsurance) {
        return repository.save(newInsurance);
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


    @GetMapping("/insurance/{id}")
    Insurances one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new InsuranceNotFoundException(id));
    }



//  @GetMapping("/employees/{id}")
//  EntityModel<Assets> one(@PathVariable Long id) {
//
//    Assets employee = repository.findById(id) //
//        .orElseThrow(() -> new AssetsNotFoundException(id));
//
//    return assembler.toModel(employee);
//  }


//    @PutMapping("/insurance/{id}")
//    Assets replaceInsurance(@RequestBody Insurances newInsurance, @PathVariable Long id) {
//
//        return repository.findById(id)
//                .map(insurance -> {
//                    insurance.setDate(newInsurance.getDate());
//                    insurance.setPersonaId(newInsurance.getPersonaId());
//                    return repository.save(insurance);
//                })
//                .orElseGet(() -> {
//                    newInsurance.setPersonaId(id);
//                    return repository.save(newInsurance);
//                });
//    }


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

    @DeleteMapping("/insurance/{id}")
    void deleteInsurance(@PathVariable Long id) {
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
