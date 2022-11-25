package insurance;

import import_export.AssetsImporter;
import import_export.DatabaseConnector;
import import_export.FromXML;
import import_export.ToCSV;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InsuranceApplication {

  public static void main(String... args) {
    AssetsImporter assetsImporter = new AssetsImporter();
    assetsImporter.execute();

    DatabaseConnector databaseConnector = new DatabaseConnector("jdbc:mysql://localhost:3306/assets", "root", null);

    FromXML dataFrom = new FromXML(assetsImporter, databaseConnector);
    dataFrom.execute();

    ToCSV dataTo = new ToCSV(databaseConnector);
    dataTo.execute();

//    SpringApplication.run(InsuranceApplication.class, args);
  }
}