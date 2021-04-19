package romario.cabo.com.br.mini_gerencial_thymeleaf.exception;

public class UnauthorizedException extends RuntimeException {
    private static final long serialVersionUID = -2678225697727730660L;

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }
}
