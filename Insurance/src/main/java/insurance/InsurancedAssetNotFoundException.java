package insurance;

public class InsurancedAssetNotFoundException extends RuntimeException {
    InsurancedAssetNotFoundException(Long id) {
        super("Could not find insurancedAsset " + id);
    }
}
