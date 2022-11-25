package import_export;

import insurance.Assets;

import java.util.List;

public class AssetsExporter {
    ExporterService exporter;

    public AssetsExporter() {
        try {
            this.exporter = new ExporterService(
                    "/Users/claudio/Desktop/Backend-Renan-Jose/Insurance/assets.txt");

        } catch (Exception e) {
            System.out.println("[ERROR] - EXPORT CREATION");
            System.out.println(e.getMessage());
        }
    }

    public void execute(List<Assets> assets) {
        try {
            String content = "id, aliquot, estimated_value, item_name\n";

            for (Assets asset : assets) {
                content = content + asset.toCSV() + "\n";
            }

            this.exporter.execute(content);
        } catch (Exception e) {
            System.out.println("[ERROR] - EXPORT EXECUTE");
            System.out.println(e.getMessage());
        }
    }
}
