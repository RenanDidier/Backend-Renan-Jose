package insurance;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;
@Entity
public class Insurances {

    @Id
    @GeneratedValue
    private Long insurancePolicyNumber;

    @Temporal(TemporalType.DATE)
    private Date date;

    @NotNull
    @Size(max = 11, min = 11, message = "Personal id needs to have 11 digits")
    private String personaId;

    Insurances(Long insurancePolicyNumber, Date date, String personaId) {
        this.insurancePolicyNumber = insurancePolicyNumber;
        this.date = date;
        this.personaId = personaId;
    }

    public Insurances() {

    }

    public Long getInsurancePolicyNumber() {
        return insurancePolicyNumber;
    }

    public void setInsurancePolicyNumber(Long insurancePolicyNumber) {
        this.insurancePolicyNumber = insurancePolicyNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPersonaId() {
        return personaId;
    }

    public void setPersonaId(String personaId) {
        this.personaId = personaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Insurances that = (Insurances) o;
        return insurancePolicyNumber.equals(that.insurancePolicyNumber) && date.equals(that.date) && personaId.equals(that.personaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(insurancePolicyNumber, date, personaId);
    }
}
