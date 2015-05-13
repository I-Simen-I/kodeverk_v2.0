package no.sands.kodeverk.launcher;

import java.awt.*;
import java.io.File;

import javax.swing.*;

import org.apache.commons.lang3.StringUtils;

import no.sands.kodeverk.common.CommonVariables;
import no.sands.kodeverk.converter.CSVToSQLConverter;

/**
 * @author Simen Søhol
 */
public class Launcher extends JFrame {
    private static final long serialVersionUID = 5161693766447693961L;
    private static final short MIN_PATH_LENGTH = 5;
    private static final String FILE_TYPE = CommonVariables.SQL_FILE;
    private static final String ERROR_MESSAGE = "The file have to end with: '%s'";

    private CSVToSQLConverter converter = new CSVToSQLConverter();

    public Launcher() {
        setLayout(new FlowLayout());
    }

    public void saveFile() throws Exception {
        String path = getPath();

        if (StringUtils.isNotBlank(path) && path.length() > MIN_PATH_LENGTH) {
            if (StringUtils.endsWith(path, FILE_TYPE)) {
                converter.generateSQL(path);
            } else {
                JOptionPane.showMessageDialog(this, String.format(ERROR_MESSAGE, FILE_TYPE), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private String getPath() {
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setCurrentDirectory(new File(CommonVariables.SQL_FILE_PATH));
        fileChooser.setSelectedFile(new File(CommonVariables.DEFAULT_SQL_FILENAME));

        int rVal = fileChooser.showDialog(Launcher.this, "Generate kodeverk");

        if (rVal == JFileChooser.APPROVE_OPTION) {
            String filename = fileChooser.getSelectedFile().getName();
            String dir = fileChooser.getCurrentDirectory().toString();

            return dir + File.separator + filename;
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        Launcher launcher = new Launcher();
        launcher.saveFile();
        System.exit(EXIT_ON_CLOSE);
    }
}
