package no.sands.kodeverk.validator.client;

import java.util.List;

import no.sands.kodeverk.validator.support.KodeverkError;

/**
 * @author Øyvind Strømmen
 */
public interface Validator {

    List<KodeverkError> validate(String filePath);

}
