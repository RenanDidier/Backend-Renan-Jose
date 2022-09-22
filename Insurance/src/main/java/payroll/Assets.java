package payroll;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
class Assets {

  private @Id @GeneratedValue Long id;
  private String ItemName;
  private Double EstimatedValue;
  private Double Aliquot;

  Assets(String ItemName, Double EstimatedValue, Double Aliquot) {

    this.ItemName = ItemName;
    this.EstimatedValue = EstimatedValue;
    this.Aliquot = Aliquot;
  }

  public Assets() {

  }

  public String getItemName() {
    return ItemName;
  }

  public void setItemName(String itemName) {
    ItemName = itemName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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