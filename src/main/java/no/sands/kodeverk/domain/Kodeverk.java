package no.sands.kodeverk.domain;

import java.util.ArrayList;
import java.util.List;

import static no.sands.kodeverk.common.CommonVariables.*;
import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * @author Simen Søhol
 */
public class Kodeverk {
    private String name;
    private String[] header;
    private String[] columnTypes;
    private List<String[]> values = new ArrayList<>();

    public Kodeverk(String name) {
        setName(name);
    }

    public Kodeverk(String name, List<String[]> csvList) {
        setName(name);
        setValuesFromCSV(csvList);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String[] getHeader() {
        return header;
    }

    public void setHeader(String[] header) {
        this.header = header;
    }

    public String[] getColumnTypes() {
        return columnTypes;
    }

    public void setColumnTypes(String[] columnTypes) {
        this.columnTypes = columnTypes;
    }

    public List<String[]> getValues() {
        return values;
    }

    public void addValue(String[] value) {
        values.add(value);
    }

    /**
     * Converts a csvFile to kodeverk class
     *
     * @param csvList the csvList to convert
     */
    public void setValuesFromCSV(List<String[]> csvList) {
        setHeader(csvList.get(CSV_HEADER_ROW));
        setColumnTypes(csvList.get(CSV_COLUMN_TYPE_ROW));

        for (int csvValueIndex = FIRST_KODEVERK_ROW_WITH_VALUES; csvValueIndex < csvList.size(); csvValueIndex++) {
            addValue(csvList.get(csvValueIndex));
        }
    }

    /**
     * Returns the number of valid rows that can be used in a sql insert statement
     *
     * @return number of valid rows
     */
    public int getNumberOfValidInsertValues() {
        int validInsertValues = 0;

        for (String column : getColumnTypes()) {
            if (column != null && !isBlank(column)) {
                validInsertValues++;
            }
        }

        return validInsertValues;
    }
}
