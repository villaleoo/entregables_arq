package integrador_1.helpers;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.FileReader;
import java.io.IOException;

public class CSV {
    public CSV() {}
    public CSVParser transformar(String path) {
        CSVParser parser = null;
        {
            try {
                parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return parser;
    }


}
