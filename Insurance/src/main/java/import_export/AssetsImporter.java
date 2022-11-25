package import_export;

import insurance.Assets;

import java.util.ArrayList;
import java.util.List;

public class AssetsImporter {
    private ImporterService importer;
    private List<String> foundAssetsStrings;

    public AssetsImporter() {
        this.foundAssetsStrings = new ArrayList<String>();
        this.importer = new ImporterService("/Users/claudio/Desktop/Backend-Renan-Jose/Insurance/xml.txt",
                (String t, String v) -> {

                    if (t.compareTo("assets") == 0) {
                        this.foundAssetsStrings.add("");
                    }

                    if (t.compareTo("assets") != 0 && !t.isEmpty() && !v.isEmpty()
                            && this.foundAssetsStrings.size() > 0) {
                        this.foundAssetsStrings.set(this.foundAssetsStrings.size() - 1,
                                this.foundAssetsStrings.get(this.foundAssetsStrings.size() - 1)
                                        + String.format("%s=%s;", t, v));
                        System.out.format("t: %s v: %s\n", t, v);
                    }
                }, java.util.Optional.empty());
    }

    String[] getKeyValue(String value) {
        return value.split("=");
    }

    Assets getAsset(String assetStr) {
        String[] attrs = assetStr.split(";");

        String itemName = "";
        Double estimatedValue = 0.0;
        Double aliquot = 0.0;
        Integer id = 1;

        for (String keyValue : attrs) {
            String[] attr = getKeyValue(keyValue);
            String key = attr[0];
            String value = attr[1];

            if (key.equals("ItemName")) {
                itemName = value;
            }

            if (key.equals("id")) {
                id = Integer.parseInt(value);
            }

            if (key.equals("Aliquot")) {
                aliquot = Double.parseDouble(value);
            }

            if (key.equals("EstimatedValue")) {
                estimatedValue = Double.parseDouble(value);
            }
        }

        return new Assets(id, itemName, estimatedValue, aliquot);
    }

    public List<Assets> execute() {
        List<Assets> assets = new ArrayList<Assets>();
        this.importer.execute();

        this.foundAssetsStrings.forEach(assetString -> {
            Assets newAsset = getAsset(assetString);
            assets.add(newAsset);
        });

        this.foundAssetsStrings.clear();

        return assets;
    }
}
