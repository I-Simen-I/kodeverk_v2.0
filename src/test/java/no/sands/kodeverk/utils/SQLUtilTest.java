package no.sands.kodeverk.utils;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import static no.sands.kodeverk.utils.SQLUtil.convertCSVValuesToSQlValues;
import static no.sands.kodeverk.utils.SQLUtil.createInsertStatement;
import static no.sands.kodeverk.utils.SQLUtil.getDateFormat;
import static no.sands.kodeverk.utils.SQLUtil.getTimestampFormat;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import no.sands.kodeverk.domain.Column;
import no.sands.kodeverk.domain.DataType;
import no.sands.kodeverk.domain.DataTypes;
import no.sands.kodeverk.domain.Header;
import no.sands.kodeverk.domain.content.Characters;
import no.sands.kodeverk.domain.content.ContentFactory;
import no.sands.kodeverk.domain.content.Date;
import no.sands.kodeverk.domain.content.Index;
import no.sands.kodeverk.domain.content.Timestamp;

/**
 * @author Simen Søhol
 */
@RunWith(MockitoJUnitRunner.class)
public class SQLUtilTest {

    @Mock
    private Header header;

    @Mock
    private DataTypes dataTypes;

    static {
        ContentFactory.registerContent(DataType.CHARACTERS, new Characters.CharactersBuilder());
        ContentFactory.registerContent(DataType.DATE, new Date.DateBuilder());
        ContentFactory.registerContent(DataType.TIMESTAMP, new Timestamp.TimeStampBuilder());
        ContentFactory.registerContent(DataType.INDEX, new Index.IndexBuilder());
    }

    @Test
    public void testGetTimestampFormat() throws Exception {
        assertThat(getTimestampFormat("2015-01-10 10:00"), is("timestamp('2015-01-10','10:00')"));
    }

    @Test
    public void testGetDateFormat() throws Exception {
        assertThat(getDateFormat("1991-01-01"), is("date('1991-01-01')"));
    }

    @Test
    public void testGetInsertStatementFormat() throws Exception {
        assertThat(createInsertStatement("K_NAVN_1", "k_kode,dekode", "'kode_1','dette er en kode'"),
                is("insert into T_K_NAVN_1 (k_kode,dekode) VALUES ('kode_1','dette er en kode');\n"));
    }

    @Test
    public void testConvertColumnsToSQlValues() {
        String[] values = {"dette er en dekode", "31.12.1899", "", "1900-01-01 10:00", "1"};

        Column stringColumn = new Column.ColumnBuilder(values[0], 0, mockHeader(), mockDataTypes()).build();
        Column dateColumn = new Column.ColumnBuilder(values[1], 1, mockHeader(), mockDataTypes()).build();
        Column emptyColumn = new Column.ColumnBuilder(values[2], 2, mockHeader(), mockDataTypes()).build();
        Column timestampColumn = new Column.ColumnBuilder(values[3], 3, mockHeader(), mockDataTypes()).build();
        Column numberColumn = new Column.ColumnBuilder(values[4], 4, mockHeader(), mockDataTypes()).build();

        assertThat(convertCSVValuesToSQlValues(stringColumn), is("'dette er en dekode'"));
        assertThat(convertCSVValuesToSQlValues(dateColumn), is("date('31.12.1899')"));
        assertThat(convertCSVValuesToSQlValues(timestampColumn), is("timestamp('1900-01-01','10:00')"));
        assertThat(convertCSVValuesToSQlValues(emptyColumn), is("NULL"));
        assertThat(convertCSVValuesToSQlValues(numberColumn), is("1"));
    }

    private Header mockHeader() {
        when(header.getValues()).thenReturn(Arrays.asList("dekode", "dato_fom", "dato_tom", "dato_opprettet", "id"));
        return header;
    }

    private DataTypes mockDataTypes() {
        when(dataTypes.getValues()).thenReturn(Arrays.asList("c1", "d2", "d3", "t4", "i5"));
        return dataTypes;
    }
}