package gupaoedu.vip.mybatis.mymybatis.v1;

public class MethodMapper {
    private String namespace;
    private String sql;
    private String id;
    private String resultType;

    public MethodMapper(String namespace, String sql, String id, String resultType) {
        this.namespace = namespace;
        this.sql = sql;
        this.id = id;
        this.resultType = resultType;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
