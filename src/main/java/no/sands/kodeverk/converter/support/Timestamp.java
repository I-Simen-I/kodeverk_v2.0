package no.sands.kodeverk.converter.support;

import no.sands.kodeverk.exceptions.KodeverkInvalidContentException;
import no.sands.kodeverk.utils.DateUtil;

/**
 * @author Øyvind Strømmen
 */
public class Timestamp implements Content {

    private final String content;

    private Timestamp(TimeStampBuilder builder) {
        this.content = builder.content;
    }

    @Override
    public String getContentAsString() {
        return content;
    }

    public static class TimeStampBuilder implements ContentBuilder<Timestamp> {

        private String rawContent;
        private String content;

        @Override
        public ContentBuilder rawContent(String rawContent) {
            this.rawContent = rawContent;
            return this;
        }

        @Override
        public Timestamp build() {
            this.content = DateUtil.convertTimestampString(this.rawContent);
            if (this.rawContent != null && this.content == null) {
                throw new KodeverkInvalidContentException("Attempt to format '" + this.rawContent + "' as a timestamp failed");
            }
            return new Timestamp(this);
        }
    }
}
