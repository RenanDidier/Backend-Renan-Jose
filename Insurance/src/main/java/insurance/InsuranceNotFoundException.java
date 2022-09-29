package insurance;

class InsuranceNotFoundException extends RuntimeException {

    InsuranceNotFoundException(Long id) {
        super("Could not find insurance " + id);
    }
}
