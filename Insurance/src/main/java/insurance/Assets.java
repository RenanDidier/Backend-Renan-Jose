package insurance;

import com.sun.istack.NotNull;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

@Entity
@Table(name="assets")
class Assets {

  @Id
  @NonNull
  private Integer id;
  @Size(max = 40, message = "Name range neeeds to be between 0-40 chars")
  private String ItemName;
  @NotNull
  private Double EstimatedValue;
  @NotNull
  @DecimalMin(value = "0.0", message = "Aliquot can't be lower than 0")
  @DecimalMax(value = "1.0", message = "Aliquot can't be greater than 1")
  private Double Aliquot;

  @OneToMany(cascade = CascadeType.MERGE)
  private List<InsurancedAsset> insurancedAssetList;

  Assets(Integer id, String ItemName, Double EstimatedValue, Double Aliquot) {
    this.id = id;
    this.ItemName = ItemName;
    this.EstimatedValue = EstimatedValue;
    this.Aliquot = Aliquot;
    this.insurancedAssetList = new ArrayList<InsurancedAsset>();
  }

  public Assets() {

  }

  public String getItemName() {
    return ItemName;
  }

  public void setItemName(String itemName) {
    ItemName = itemName;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Double getEstimatedValue() {
    return EstimatedValue;
  }

  public void setEstimatedValue(Double estimatedValue) {
    EstimatedValue = estimatedValue;
  }

  public Double getAliquot() {
    return Aliquot;
  }

  public void setAliquot(Double aliquot) {
    Aliquot = aliquot;
  }

  public List<InsurancedAsset> getInsurancedAssetList() {
    return insurancedAssetList;
  }

  public void setInsurancedAssetList(List<InsurancedAsset> insurancedAssetList) {
    this.insurancedAssetList = insurancedAssetList;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Assets assets = (Assets) o;
    return Objects.equals(id, assets.id) && Objects.equals(ItemName, assets.ItemName) && Objects.equals(EstimatedValue, assets.EstimatedValue) && Objects.equals(Aliquot, assets.Aliquot);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, ItemName, EstimatedValue, Aliquot);
  }
}