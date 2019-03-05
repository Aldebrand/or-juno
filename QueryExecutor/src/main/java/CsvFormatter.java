import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;

public class CsvFormatter extends FileFormatterBase<List<Map<String,Object>>> {
    CsvFormatter(String fileExtension, String fileName ,String filePath) {
        super(fileExtension, fileName, filePath);
    }

    @Override
    public boolean format(List<Map<String, Object>> data) {
        if (data == null || data.size() == 0) {
            return false;
        }
        try (
                BufferedWriter bw = getBuffer();
                CSVPrinter csvPrinter = createCsvPrinter(bw, data)
        ) {
            for (Map<String, Object> datum : data) {
                final Collection<Object> valuesCollection = datum.values();
                Object[] valuesArray = new Object[valuesCollection.size()];

                csvPrinter.printRecord(valuesCollection.toArray(valuesArray));
            }

            csvPrinter.flush();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    private CSVPrinter createCsvPrinter(BufferedWriter bw, List<Map<String, Object>> data ) throws IOException {
        final Set<String> columnNamesSet = data.get(0).keySet();
        String[] columnNamesArray = new String[columnNamesSet.size()];
        
        return new CSVPrinter(bw, CSVFormat.DEFAULT.withHeader(columnNamesSet.toArray(columnNamesArray)));
    }
}
