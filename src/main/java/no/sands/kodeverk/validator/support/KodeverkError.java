package no.sands.kodeverk.validator.support;

/**
 * @author Øyvind Strømmen
 */
public class KodeverkError {

    private String message;

    private Throwable cause;

    public KodeverkError withMessage(String message) {
        this.message = message;
        return this;
    }

    public KodeverkError withCause(Throwable cause) {
        this.cause = cause;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getCause() {
        return cause;
    }
}
