import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

public abstract class FileFormatterBase<T> implements OutputFormatter<T> {
    protected String fileExtension;
    protected String fileName;
    protected String filePath;

    FileFormatterBase(String fileExtension, String fileName, String filePath){
        setFileExtension(fileExtension);
        setFileName(fileName);
        setFilePath(filePath);
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileName(){
        return fileName;
    }

    public void setFileExtension(String fileExtension){
        if(fileExtension != null && !fileExtension.isEmpty()) {
            this.fileExtension = fileExtension;
        }
    }

    public void setFilePath(String filePath){
        if (filePath!=null && !filePath.isEmpty()){
            this.filePath = filePath + '\\' + fileName + fileExtension;
        }
    }

    public void setFileName(String fileName){
        if(fileName != null && !fileName.isEmpty()) {
            this.fileName = fileName;
        }
    }

    protected BufferedWriter getBuffer() throws IOException {
        return Files.newBufferedWriter(Paths.get(filePath));
    }
}
