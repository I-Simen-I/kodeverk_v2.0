package no.sands.kodeverk.exceptions;

/**
 * An unchecked urecoverable exception. Should only be thrown in situations when the application cannot recover and the only sensible option
 * is to terminate the whole program.
 *
 * @author Øyvind Strømmen
 */
public class KodeverkUnrecoverableException extends RuntimeException {

    /**
     * Construct a new unchecked exception with a given detail message.
     *
     * @param message the detail message
     */
    public KodeverkUnrecoverableException(String message) {
        super(message);
    }
}
