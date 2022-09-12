package payroll;

public class OrderNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public OrderNotFoundException(Long id) {
		super("Pedido não encontrado: " + id);
	}
}