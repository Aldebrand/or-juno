import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public  class SqlQueryExecutor extends QueryExecutorBase<List<Map<String, Object>>> {

    public SqlQueryExecutor(String connString) {
        super(connString);
    }

    @Override
    public List<Map<String,Object>> executeQuery(Query query) {
        try(
                Connection connection = DriverManager.getConnection(connString);
                PreparedStatement statement = createPreparedStatement(connection, query);
                ResultSet resultSet = statement.executeQuery()
                )
        {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();

            while (resultSet.next()) {
                Map<String, Object> row = new HashMap<>();

                for (int i = 1; i <= columnCount; i++) {
                    row.put(metaData.getColumnLabel(i), resultSet.getObject(i));
                }

                rows.add(row);
            }

            return rows;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private PreparedStatement createPreparedStatement(Connection connection, Query query) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query.getQueryString());

        for (Map.Entry<Integer,Object> param: query.getParametersCopy().entrySet()) {
            statement.setObject(param.getKey(),param.getValue());
        }

        return statement;
    }
}
