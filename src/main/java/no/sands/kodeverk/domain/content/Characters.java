package no.sands.kodeverk.domain.content;

/**
 * Self validating representation of the {@code char} data type. Character colums can contain almost all kinds of data.
 *
 * @author Øyvind Strømmen
 */
public class Characters implements Content {

    private final String content;

    private Characters(CharactersBuilder builder) {
        this.content = builder.content;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getContentAsString() {
        return content;
    }

    public static class CharactersBuilder implements ContentBuilder<Characters> {

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
        public Characters build() {
            this.content = this.rawContent;
            return new Characters(this);
        }
    }
}
