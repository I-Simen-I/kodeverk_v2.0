package no.sands.kodeverk.converter.support;

import no.sands.kodeverk.exceptions.KodeverkInvalidContentException;
import no.sands.kodeverk.utils.DateUtil;

/**
 * @author Øyvind Strømmen
 */
public class Timestamp implements Content {

    private String content;

    @Override
    public Content withRawContent(String rawContent) throws KodeverkInvalidContentException {
        content = DateUtil.convertTimestampString(rawContent);
        if (rawContent != null && content == null) {
            throw new KodeverkInvalidContentException("Attempt to format '" + rawContent + "' as a timestamp failed");
        }
        return this;
    }

    @Override
    public String getContentAsString() {
        return content;
    }
}
