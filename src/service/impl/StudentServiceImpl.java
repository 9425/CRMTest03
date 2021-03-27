package service.impl;

import dao.StudentDao;
import dao.impl.StudentDaoImpl;
import domain.Student;
import service.StudentService;

//这个包具体实现的是代理类的实现

//这里相当于usbSellFactory,将要对usbSellFactory进行代理
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao=new StudentDaoImpl();
    @Override
    public Student getById(String id) {
        Student s=studentDao.getById(id);
        return s;
    }

    @Override
    public void save(Student s) {
        studentDao.save(s);
    }
}
