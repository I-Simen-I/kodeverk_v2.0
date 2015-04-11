package no.sands.kodeverk.enums;

/**
 * @author Simen Søhol
 */
public enum FileType {
    CSV(".csv"),
    TXT(".txt"),
    CSV_TXT(".csv.txt"),
    XLS(".xls");

    private String filetype;

    private FileType(String filetype) {
        setFiletype(filetype);
    }

    public String getFiletype() {
        return filetype;
    }

    private void setFiletype(String filetype) {
        this.filetype = filetype;
    }
}
