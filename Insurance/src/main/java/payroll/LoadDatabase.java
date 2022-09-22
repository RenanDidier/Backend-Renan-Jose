package payroll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(AssetsRepository assetsRepository, OrderRepository orderRepository) {

    return args -> {
      assetsRepository.save(new Assets("Iphone", 5000.0, 1.0));
      assetsRepository.save(new Assets("Mini Cooper", 100000.0, 0.1));

      assetsRepository.findAll().forEach(employee -> log.info("Preloaded " + employee));

      
      orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
      orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));

      orderRepository.findAll().forEach(order -> {
        log.info("Preloaded " + order);
      });
      
    };
  }
}