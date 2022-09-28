package insurance;

class AssetNotFoundException extends RuntimeException {

  AssetNotFoundException(Integer id) {
    super("Could not find asset " + id);
  }
}