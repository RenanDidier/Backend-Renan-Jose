package insurance;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
public class InsurancedAssetController {


    private final InsurancedAssetRepository repository;

    private final InsurancedAssetModelAssembler assembler;

    private final InsurancesRepository insurancesRepository;


    InsurancedAssetController(InsurancedAssetRepository repository, InsurancedAssetModelAssembler assembler, InsurancesRepository insurancesRepository) {
        this.repository = repository;
        this.assembler = assembler;
        this.insurancesRepository = insurancesRepository;
    }

    @GetMapping("/insuranced_asset")
    List<InsurancedAsset> all() {
        return repository.findAll();
    }


    @PostMapping("/insuranced_asset")
    InsurancedAsset newInsurancedAsset(@RequestBody InsurancedAsset newInsurancedAsset) {
        return repository.save(newInsurancedAsset);
    }

//    @PostMapping("/insuranced_asset/{assetId}/{policyId}")
//    InsurancedAsset newLink(@PathVariable( name= "assetId") Long assetId, @PathVariable( name= "policyId") Long policyId) {
//        InsurancedAsset insurancedAsset = this.repository
//                .findById(assetId)
//                .map((curr) -> {
//                    System.out.println(curr.getNumber());
//                    return curr;
//                })
//                .orElseGet(() -> {
//                    return null;
//                });
//    }

    @GetMapping("/insuranced_asset/{id}")
    InsurancedAsset one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new InsurancedAssetNotFoundException(id));
    }


    @DeleteMapping("/insuranced_asset/{id}")
    void deleteInsurancedAsset(@PathVariable Long id) {
        repository.deleteById(id);
    }
}