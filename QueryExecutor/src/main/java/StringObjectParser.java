import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StringObjectParser implements ObjectParser<String> {
    private List<String> typeNames;
    private final String SINGLE_QUOTE_PATTERN = "^\'|\'$";
    private final String EMPTY_STRING = "";

    public StringObjectParser(){
        initTypeNames();
    }

    private void initTypeNames() {
        this.typeNames = new ArrayList<>();
        typeNames.add("int");
        typeNames.add("double");
        typeNames.add("date");
        typeNames.add("bool");
    }

    @Override
    public Object parse(String object) {
        Object result;
        for(String typeName: typeNames){
            result = tryParse(typeName, object);
            if (result != null){
                return result;
            }
        }
        return removeQuotes(object);
    }

    private Object tryParse(String typeName, String value) {
        try {
            switch (typeName) {
                case "int":
                    return Integer.parseInt(value);
                case "double":
                    return Double.parseDouble(value);
                case "date":
                    value = removeQuotes(value);
                    return LocalDate.parse(value);
                case "bool":
                    if(value.equals("true") || value.equals("false")){
                        return value;
                    }
                    break;
                default:
                    return value;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private String removeQuotes(String value) {
        return value.replaceAll(SINGLE_QUOTE_PATTERN, EMPTY_STRING);
    }


}
