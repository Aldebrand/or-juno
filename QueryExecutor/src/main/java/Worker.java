import java.lang.reflect.Parameter;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.Objects;

public class Worker {
    protected Query query;
    private final String paramterPattern = ":[a-zA-Z]+";

    public Worker(String[] userInput, ObjectParser inputParser){
        init(userInput, inputParser );
    }

    protected void init(String[] userInput, ObjectParser inputParser){
        buildQuery(userInput, inputParser);
    }

    protected void run(QueryExecutorBase executor, OutputFormatter outputFormatter){
        final Object result = executor.executeQuery(query);
        outputFormatter.format(result);
    }

    protected void buildQuery(String[] userInput, ObjectParser inputParser){
        final int inputLength = userInput.length;
        if (inputLength == 0) {
            throw new InvalidParameterException("Both query and parameters are missing");
        }

        String statemen = userInput[0].replaceAll(paramterPattern,"?");
        query = new Query(statemen);

        if(inputLength>1){
            for (int i=1; i<inputLength; i++){
                String[] keyValue = userInput[i].split("=");
                if (keyValue.length<2){
                    throw new InvalidParameterException("Wrong parameter format");
                }
                Object parsingResult = inputParser.parse(keyValue[1]);
                query.addParameter(i,parsingResult);
            }
        }
    }

}
