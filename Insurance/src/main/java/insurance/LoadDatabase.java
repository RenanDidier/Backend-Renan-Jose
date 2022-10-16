package insurance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.*;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(AssetsRepository assetsRepository, InsurancesRepository insurancesRepository, InsurancedAssetRepository insurancedAssetRepository) {

    return args -> {
      Assets asset1 = new Assets(1, "Iphone", 5000.0, 0.1);
      Assets asset2 = new Assets(4, "Mini Cooper", 100000.0, 0.1);
      Assets asset3 = new Assets(6, "Golf Gti", 150000.0, 0.3);

      assetsRepository.save(asset1);
      assetsRepository.save(asset2);
      assetsRepository.save(asset3);

      assetsRepository.findAll().forEach(asset -> log.info("Preloaded " + asset));

      InsurancedAsset insurancedAsset1 = new InsurancedAsset(3, 0, asset1);
      InsurancedAsset insurancedAsset2 = new InsurancedAsset(6, 2, asset2);
      InsurancedAsset insurancedAsset3 = new InsurancedAsset(3, 3, asset1);

      insurancedAssetRepository.save(insurancedAsset1);
      insurancedAssetRepository.save(insurancedAsset2);
      insurancedAssetRepository.save(insurancedAsset3);
      insurancedAssetRepository.findAll().forEach(insurancedAsset -> log.info("Preloaded " + insurancedAsset));

      Date date1 = new GregorianCalendar(2023, Calendar.FEBRUARY, 11).getTime();
      Date date2 = new GregorianCalendar(2023, Calendar.APRIL, 13).getTime();

      Insurances insurance1 = new Insurances(date1, "38798131400");
      Insurances insurance2 = new Insurances(date2, "12247030400");

      List<InsurancedAsset> currentList1 = Arrays.asList(insurancedAsset1, insurancedAsset2);
      insurance1.setInsurancedAssets(currentList1);

      List<InsurancedAsset> currentList2 = Arrays.asList(insurancedAsset3);
      insurance2.setInsurancedAssets(currentList2);

      insurancesRepository.save(insurance1);
      insurancesRepository.save(insurance2);

      insurancesRepository.findAll().forEach(order -> {
        log.info("Preloaded " + order);
      });
      
    };
  }
}