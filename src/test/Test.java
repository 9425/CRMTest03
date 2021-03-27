package test;

import domain.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import service.StudentService;
import service.impl.StudentServiceImpl;
import util.ServiceFactory;

import java.io.IOException;
import java.io.InputStream;

public class Test {
    public static void main(String[] args) {
       /* String resource="mybatis-config.xml";
        InputStream inputStream=null;
        try {
            inputStream= Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session=sqlSessionFactory.openSession();
        Student student01=session.selectOne("CRM03.getById","A005");
        System.out.println(student01);
        session.close();*/

        //通过上面的测试，mybatis框架的搭建并没有出现问题

        //创建要进行代理的服务对象
        StudentService ss0=new StudentServiceImpl();
        //将要代理的服务对象放入，创建出代理对象
        StudentService ss1=(StudentService) ServiceFactory.getService(ss0);
        //使用代理对象进行方法调用
        //Student s=ss1.getById("A001");
        //System.out.println(s);
        Student ss2=new Student("A0012","zs",22);
        ss1.save(ss2);

    }
}
