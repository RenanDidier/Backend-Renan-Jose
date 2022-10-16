package insurance;

import br.com.caelum.stella.validation.CPFValidator;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class InsurancedAsset {

    @Id
    @GeneratedValue
    private Long insurancedAssetId;

    @NotNull
    private  Integer monthExpiration;

    private Integer riskFactor;

    @NotNull
    private Double totalValue;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Assets asset;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Insurances insurance;

    public InsurancedAsset(Integer monthExpiration, Integer riskFactor, Assets asset) {
        this.monthExpiration = monthExpiration;
        this.riskFactor = riskFactor;
        this.asset = asset;

        this.totalValue = calculateTotalValue(asset);
    }

    InsurancedAsset() {
    }

    private Double calculateTotalValue(Assets asset) {
        if (this.riskFactor == 0) {
            return asset.getEstimatedValue() * asset.getAliquot() * this.monthExpiration;
        } else {
            return asset.getEstimatedValue() * (asset.getAliquot() + this.riskFactor) * this.monthExpiration;
        }
    }

    public Integer getMonthExpiration() {
        return monthExpiration;
    }

    public void setMonthExpiration(Integer monthExpiration) {
        this.monthExpiration = monthExpiration;
    }

    public Integer getRiskFactor() {
        return riskFactor;
    }

    public void setRiskFactor(Integer riskFactor) {
        this.riskFactor = riskFactor;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public Long getInsurancedAssetId() {
        return insurancedAssetId;
    }

    public void setInsurancedAssetId(Long insurancedAssetId) {
        this.insurancedAssetId = insurancedAssetId;
    }

    public Assets getAsset() {
        return asset;
    }

    public void setAsset(Assets asset) {
        this.asset = asset;
    }
}
