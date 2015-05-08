package no.sands.kodeverk.domain.content;

import org.apache.commons.lang3.StringUtils;

import no.sands.kodeverk.exceptions.KodeverkInvalidContentException;

/**
 * Self validating representation of the {@code index} data type. Indexes can only contain numeric values.
 *
 * @author Øyvind Strømmen
 * @author Simen Søhol
 */
public class Index implements Content {

    private final String content;

    private Index(IndexBuilder builder) {
        this.content = builder.content;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getContentAsString() {
        return content;
    }

    public static class IndexBuilder implements ContentBuilder<Index> {

        private String rawContent;
        private String content;

        /**
         * {@inheritDoc}
         */
        @Override
        public ContentBuilder rawContent(String rawContent) {
            this.rawContent = rawContent;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Index build() {
            if (StringUtils.isNumeric(this.rawContent) || StringUtils.isBlank(this.rawContent)) {
                this.content = this.rawContent;
                return new Index(this);
            }
            throw new KodeverkInvalidContentException("Expected '" + this.rawContent + "' to be a numerical");
        }
    }
}
