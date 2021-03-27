package util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

//这个类的作用是创建出可用的session,并且可以在使用完毕之后进行关闭

public class SqlSessionUtil {
    private SqlSessionUtil(){

    }
    private static SqlSessionFactory sqlSessionFactory;
    static {
        String resource="mybatis-config.xml";
        InputStream inputStream=null;
        try {
            inputStream= Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
    }
    //以上代码即将sqlSessionFactory进行了创立
    //ThreadLocal是线程池，将sqlSession进行存储
    private static ThreadLocal<SqlSession> t=new ThreadLocal<SqlSession>();
    public static SqlSession getSession(){
        SqlSession session=t.get();
        if (session==null){
            //如果session为空的，没有可用的，需要进行创建
            session=sqlSessionFactory.openSession();
            t.set(session);
        }
        return session;
    }

    //关闭SqlSession对象
    public static void myClose(SqlSession session){
        if (session!=null){
            session.close();
        }
        //下面的一句必须进行添加
        t.remove();
    }
    public static void myCommit(SqlSession session){
        if (session!=null){
            session.commit();
        }
    }
}
