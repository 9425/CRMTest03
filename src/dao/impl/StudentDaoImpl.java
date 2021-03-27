package dao.impl;

import dao.StudentDao;
import domain.Student;
import org.apache.ibatis.session.SqlSession;
import util.SqlSessionUtil;

//这个类的作用是对将要用到的sql语句相关进行编写，使得可以在下一次进行很方便的调用

//这里实现了StudentDao,相当于UsbSell的UsbFactory
public class StudentDaoImpl implements StudentDao {
    @Override
    public Student getById(String id) {
        SqlSession session= SqlSessionUtil.getSession();
        Student s=session.selectOne("CRM03.getById",id);
        return s;
    }

    @Override
    public void save(Student s) {
        SqlSession session=SqlSessionUtil.getSession();
        session.insert("CRM03.save",s);
        //session.commit();
    }
}
