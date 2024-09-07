package DB;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

// se encarga de leer archivos CSV en la carpeta data y retorna un mapa para
public class ReaderFilesCSV {

    //esta funcion a partir de la lista obtenida por getListFilesCSV, recorre los archivos CSV y les da un formato utilizando el CSVFormat.
    //luego de darles formato, los agrega a un mapa donde cada archivo CSV tiene como key en el mapa su nombre de archivo. Ejemplo key=productos.csv
    public static Map<String, CSVParser> formatCSVMap() {
        List<Path> files = ReaderFilesCSV.getListFilesCSV();
        Map<String, CSVParser> csvMap = new HashMap<>();

        try {
            for (Path file : files) {
                FileReader read = new FileReader(file.toFile());
                CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(read);
                csvMap.put(file.getFileName().toString(), parser);
            }
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e);
        }

        return csvMap;
    }

    //esta funcion obtiene lista de todos los archivos CSV de la carpeta resources/data ->si existe la carpeta.
    private static List<Path> getListFilesCSV(){
        List<Path> filesCSV;
        //MySqlDB.class: Obtiene el objeto Class de la clase MySqlDB.
        //getClassLoader(): Devuelve el ClassLoader que cargó MySqlDB.
        //getResource("data"): Busca un recurso llamado "data" en el classpath. Si lo encuentra, devuelve un URL que representa la ubicación del recurso; si no, devuelve null.
        URL resource = MySqlDB.class.getClassLoader().getResource("data");

        if(resource == null){
            System.out.println("Error: carpeta 'data' no encontrada.");
            return null;
        }
        try{
            Path root = Paths.get(resource.toURI());
            Stream<Path> files = Files.list(root);

            filesCSV = files.filter(file ->file.toString().endsWith(".csv")).toList();


        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }

        return filesCSV;
    }
}
