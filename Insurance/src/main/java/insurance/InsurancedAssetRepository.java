package insurance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InsurancedAssetRepository extends JpaRepository<InsurancedAsset, Long> {
}