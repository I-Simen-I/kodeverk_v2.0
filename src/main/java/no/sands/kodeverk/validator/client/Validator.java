package no.sands.kodeverk.validator.client;

import java.util.List;

import no.sands.kodeverk.validator.support.KodeverkError;

/**
 * @author �yvind Str�mmen
 */
public interface Validator {

    List<KodeverkError> validate(String filePath);

}
