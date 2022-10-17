package insurance;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class InsuranceController {
    private final InsurancesRepository repository;

    private final InsuranceModelAssembler assembler;


    InsuranceController(InsurancesRepository repository, InsurancedAssetRepository insurancedAssetRepository, InsuranceModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/insurance")
    List<Insurances> all() {
        return repository.findAll();
    }

    @PostMapping("/insurance")
    Insurances newInsurance(@RequestBody Insurances newInsurance) {
        return repository.save(newInsurance);
        // jogar excecao quando nao conseguir escrever item no db
    }

//    @PutMapping("/insurance/add")
//    Optional<Insurances> addInsurancedAsset(@RequestBody ObjectNode json) {
//        Optional<InsurancedAsset> assetToAdd =insurancedAssetRepository.findById(Long.valueOf(String.valueOf(json.get("insurancedAssetId"))));
//
//        if (assetToAdd != null){
//            return repository.findById(Long.valueOf(String.valueOf(json.get("policyId"))))
//                    .map(insurance -> {
//                        List<InsurancedAsset> currentList = insurance.getInsurancedAssets();
//                        currentList.add(assetToAdd);
//                        insurance.setInsurancedAssets(currentList);
//                        return repository.save(insurance);
//                    });
//        }
//    }

    @GetMapping("/insurance/{id}")
    Insurances one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new InsuranceNotFoundException(id));
    }

    @DeleteMapping("/insurance/{id}")
    void deleteInsurance(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
