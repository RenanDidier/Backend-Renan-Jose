package insurance;


import br.com.caelum.stella.validation.CPFValidator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Entity
public class Insurances {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long insurancePolicyNumber;

    @Temporal(TemporalType.DATE)
    @NotNull
    private Date date;

    @NotNull
    @Size(max = 11, min = 11, message = "Personal id needs to have 11 digits")
    private String personalId;

    @OneToMany
    private List<InsurancedAsset> insurancedAssets;

    Insurances(Date date, String personalId) {
        this.date = date;
        this.personalId = personalId;
        this.insurancedAssets =  new ArrayList<InsurancedAsset>();
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
        if (validDate(date)) {
            this.date = date;
        } else {
            System.out.println("DATA_INVALIDA");
        }
    }

    public String getPersonalId() {
        return personalId;
    }

    private static boolean validCPF(String cpf) {
        CPFValidator cpfValidator = new CPFValidator();
        try{ cpfValidator.assertValid(cpf);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private static boolean validDate(Date date){
        if (date.toInstant().isBefore(Instant.now())){
            return false;
        } else {
            return true;
        }
    }

    public void setPersonalId(String personaId) {
        if (validCPF(personaId)) {
            this.personalId = personaId;
        } else {
            System.out.println("CPF_INVALIDO");
        }
    }

    public List<InsurancedAsset> getInsurancedAssets() {
        return insurancedAssets;
    }

    public void setInsurancedAssets(List<InsurancedAsset> insurancedAssets) {
        this.insurancedAssets = insurancedAssets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Insurances that = (Insurances) o;
        return insurancePolicyNumber.equals(that.insurancePolicyNumber) && date.equals(that.date) && personalId.equals(that.personalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(insurancePolicyNumber, date, personalId);
    }
}
