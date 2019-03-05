import java.io.InputStream;
import java.util.Properties;

public class Program {
    public static void main(String[] args){
        Properties props = new Properties();
        //final String propertiesPath = "D:\\Workspace\\Java\\QueryExecutor\\src\\main\\resources\\config.properties";
        //InputStream stream = Program.class.getClassLoader().getResourceAsStream("config.properties");

        try(final InputStream stream = Program.class.getClassLoader().getResourceAsStream("config.properties")){
            props.load(stream);

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
