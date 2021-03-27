package dao;

import domain.Student;

//这里是将要进行代理对象的接口，相当于UsbSell
public interface StudentDao {
    public Student getById(String id);
    public void save(Student s);
}
