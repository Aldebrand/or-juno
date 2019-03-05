import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Program {
    public static void main(String[] args){
        Properties props = new Properties();
        final String propertiesPath = "\\or-juno\\QueryExecutor\\src\\main\\java";

        try(FileInputStream fileInputStream = new FileInputStream(propertiesPath)){
            props.load(fileInputStream);

            final String filePath = props.getProperty("File_Path");
            final String fileName = props.getProperty("File_Name");
            final String fileExtension = props.getProperty("File_Extension");
            final String connectionString = props.getProperty("Connection_String");

            Worker appWorker = new Worker(args, new StringObjectParser());
            appWorker.run(new SqlQueryExecutor(connectionString), new CsvFormatter(fileExtension,fileName, filePath));

        }catch (Exception e){
            e.printStackTrace();
        }




    }
}
