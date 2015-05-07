package no.sands.kodeverk.converter.support;

import org.apache.commons.lang3.StringUtils;

import no.sands.kodeverk.exceptions.KodeverkInvalidContentException;
import no.sands.kodeverk.utils.DateUtil;

/**
 * Self validating representation of the {@code date} data type. The following are accepted date formats:
 * <ul>
 * <li>yyyy-MM-dd</li>
 * <li>dd.MM.yyyy</li>
 * </ul>
 *
 * @author Øyvind Strømmen
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
            this.content = DateUtil.convertDateString(this.rawContent);
            if (StringUtils.isNotBlank(this.rawContent) && this.content == null) {
                throw new KodeverkInvalidContentException("Attempt to format '" + this.rawContent + "' as a date failed");
            }
            return new Date(this);
        }
    }
}
