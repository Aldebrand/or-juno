import java.util.*;

public class Query {
    private String queryString;
    private Map<Integer, Object> parameters;


    public Query(String queryString) {
        this.queryString = queryString;
        parameters = new HashMap<>();
    }

    public void addParameter(Integer paramName, Object paramValue){
        parameters.put(paramName, paramValue);
    }

    public Map<Integer, Object> getParametersCopy(){
        return Collections.unmodifiableMap(new HashMap<>(parameters));
    }

    public String getQueryString(){
        return queryString;
    }
}
