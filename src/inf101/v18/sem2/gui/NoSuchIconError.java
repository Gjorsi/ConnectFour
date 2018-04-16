package inf101.v18.sem2.gui;

public class NoSuchIconError extends RuntimeException {
	private static final long serialVersionUID = 2619557059193139982L;

	public NoSuchIconError(String name) {
		super(name);
	}
}
