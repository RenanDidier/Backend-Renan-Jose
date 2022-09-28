package insurance;

import com.sun.istack.NotNull;
import org.springframework.lang.NonNull;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
class Assets {

  @Id
  @NonNull
  // tirar duvida se precisa evitar sobreescricao
  private Integer id;
  @Size(max = 40)
  private String ItemName;
  @NotNull
  private Double EstimatedValue;
  @NotNull
  private Double Aliquot;

  Assets(Integer id, String ItemName, Double EstimatedValue, Double Aliquot) {
    this.id = id;
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