public abstract class QueryExecutorBase<T> {
    protected String connString;

    public QueryExecutorBase(String connString){
        this.connString = connString;
    }

    abstract T executeQuery(Query query);
}
