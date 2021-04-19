package romario.cabo.com.br.mini_gerencial_thymeleaf.exception;

public class InternalServerErrorException extends RuntimeException {

    private static final long serialVersionUID = -8893415150658447018L;

    public InternalServerErrorException(String message) {
        super(message);
    }

    public InternalServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
