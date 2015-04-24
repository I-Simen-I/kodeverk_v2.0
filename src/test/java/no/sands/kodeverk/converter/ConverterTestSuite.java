package no.sands.kodeverk.converter;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Suite running tests which needs to be run in a particular order.
 *
 * @author Øyvind Strømmen
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ExcelConverterTest.class, CSVToSQLConverterTest.class})
public class ConverterTestSuite {
}
