package payroll;

class AssetNotFoundException extends RuntimeException {

  AssetNotFoundException(Long id) {
    super("Could not find asset " + id);
  }
}