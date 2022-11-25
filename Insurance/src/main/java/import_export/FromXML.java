package import_export;

import insurance.Assets;

import java.util.List;

public class FromXML {
    AssetsImporter assetsImporter;
    DatabaseConnector databaseConnector;

    public FromXML(AssetsImporter assetsImporter, DatabaseConnector databaseConnector) {
        this.assetsImporter = assetsImporter;
        this.databaseConnector = databaseConnector;
    }

    private void importAssets() {
        List<Assets> assets = assetsImporter.execute();

        this.databaseConnector.update("DELETE FROM assets;");

        assets.forEach(asset -> {
            this.databaseConnector
                    .update(
                            String.format(
                                    "INSERT INTO assets VALUES (%s);",
                                    asset.toCSV()));
        });

    }

    public void execute() {
        this.importAssets();
    }
}
