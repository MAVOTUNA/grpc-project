package session;

import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SessionFactory {

    private SqlSessionFactory sessionFactory;

    public SessionFactory() {

        String resource = "Mybatis.xml";

        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (Exception e) {
          e.printStackTrace();
        }
    }

    public SqlSession openSession() {
        return this.openSession(true);
    }

    public SqlSession openSession(boolean autoCommit) {
        SqlSession session = null;
        session = sessionFactory.openSession(autoCommit);
        return session;
    }

}
