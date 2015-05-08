package no.sands.kodeverk.domain.content;

import no.sands.kodeverk.exceptions.KodeverkInvalidContentException;
import no.sands.kodeverk.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * Self validating representation of the {@code timestamp} data type. The following are accepted timestamp formats:
 * <ul>
 * <li>yyyy-MM-dd HH:mm</li>
 * <li>dd.MM.yyyy HH:mm</li>
 * </ul>
 *
 * @author Øyvind Strømmen
 * @author Simen Søhol
 */
public class Timestamp implements Content {

    private final String content;

    private Timestamp(TimeStampBuilder builder) {
        this.content = builder.content;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getContentAsString() {
        return content;
    }

    public static class TimeStampBuilder implements ContentBuilder<Timestamp> {

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
        public Timestamp build() {
            if (!DateUtil.isTimestampValid(this.rawContent) && StringUtils.isNotBlank(this.rawContent)) {
                throw new KodeverkInvalidContentException("'" + this.rawContent + "' is not a valid timestamp");
            }
            this.content = this.rawContent;
            return new Timestamp(this);
        }
    }
}
