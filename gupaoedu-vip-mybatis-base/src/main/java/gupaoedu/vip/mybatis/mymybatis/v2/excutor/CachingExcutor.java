package gupaoedu.vip.mybatis.mymybatis.v2.excutor;

import gupaoedu.vip.mybatis.mymybatis.v2.mapperstatement.MappedStatement;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CachingExcutor implements Excutor {
    private Excutor excutor;
    private Map<Object, Object> cache = new HashMap<Object, Object>();
    public CachingExcutor(Excutor excutor) {
        this.excutor = excutor;
    }

    public <E> List<E> query(MappedStatement ms, Object parameter) throws SQLException, ClassNotFoundException {
        Object cacheQuery = cache.get(ms);
        if (cacheQuery==null) {
            cacheQuery= excutor.query(ms, parameter);
            cache.put(ms,cacheQuery);
        }
        return (List<E>) cacheQuery;
    }
}
