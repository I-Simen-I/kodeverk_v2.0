package no.sands.kodeverk.domain.content;

import no.sands.kodeverk.exceptions.KodeverkInvalidContentException;
import no.sands.kodeverk.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * Self validating representation of the {@code date} data type. The following are accepted date formats:
 * <ul>
 * <li>yyyy-MM-dd</li>
 * <li>dd.MM.yyyy</li>
 * </ul>
 *
 * @author �yvind Str�mmen
 * @author Simen S�hol
 */
public class Date implements Content {

    private final String content;

    private Date(DateBuilder builder) {
        this.content = builder.content;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getContentAsString() {
        return content;
    }

    public static class DateBuilder implements ContentBuilder<Date> {

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
        public Date build() {
            if (!DateUtil.isDateValid(this.rawContent) && StringUtils.isNotBlank(this.rawContent)) {
                throw new KodeverkInvalidContentException("'" + this.rawContent + "' is not a valid date");
            }
            this.content = this.rawContent;
            return new Date(this);
        }
    }
}
