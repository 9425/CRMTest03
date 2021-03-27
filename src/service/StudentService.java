package service;

import domain.Student;

//这里也相当于usbSell
public interface StudentService {
    public Student getById(String id);
    public void save(Student s);
}
