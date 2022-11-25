package import_export;

import insurance.Assets;

public class ToCSV {
    DatabaseConnector databaseConnector;
    ExporterService assetExporter;
    String assetContent = "id, aliquot, estimated_value, item_name\n";

    public ToCSV(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
        try {
            this.assetExporter = new ExporterService(
                    "/Users/claudio/Desktop/Backend-Renan-Jose/Insurance/assets.txt");
        } catch (Exception e) {
            System.out.format("\n[ERROR]:[DATA TO CSV]: %s\n", e.getMessage());
        }
    }

    private void assets() {
        this.databaseConnector.query("SELECT * FROM assets", (result) -> {
            try {
                Integer id = result.getInt("id");
                String itemName = result.getString("item_name");
                Double aliquot = result.getDouble("aliquot");
                Double estimatedValue = result.getDouble("estimated_value");

                Assets asset = new Assets(id, itemName, estimatedValue, aliquot);

                String row = "" +  id + asset.toCSV() + "\n";

                this.assetContent = assetContent + row;
            } catch (Exception e) {
                // TODO: handle exception
            }

        });
        try {
            this.assetExporter.execute(this.assetContent);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void execute() {
        this.assets();
    }
}
