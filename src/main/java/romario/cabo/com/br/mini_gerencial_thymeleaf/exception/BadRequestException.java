package romario.cabo.com.br.mini_gerencial_thymeleaf.exception;

public class BadRequestException extends RuntimeException {
    private static final long serialVersionUID = -3656834787334432657L;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
