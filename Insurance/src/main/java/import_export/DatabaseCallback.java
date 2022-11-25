package import_export;

import java.sql.ResultSet;

public interface DatabaseCallback {
    public void call(ResultSet result);
}
