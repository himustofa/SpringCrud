package com.mk.SpringCrud.services;

import com.google.gson.Gson;
import com.mk.SpringCrud.models.Student;
import com.mk.SpringCrud.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    private String TAG = this.getClass().getSimpleName();

    ArrayList<Student> students = new ArrayList<>();

    public Student add(Student student) {
        students.add(student);
        return studentRepository.save(student);
    }

    public Iterable<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student getById(int id) {
        Optional<Student> student = studentRepository.findById(id); //return students.get(id);
        if(student.isPresent()) {
            System.out.println(TAG + " : " + new Gson().toJson(student));
            return student.get();
        } else {
            System.out.println("No employee record exist for given id");
            return null;
        }
    }

    public Student updateById(int id, String name, int age) {
        //return students.get(id);
        Student student = getById(id);
        student.setName(name);
        student.setAge(age);
        return studentRepository.save(student);
    }

    public boolean deleteById(int id) {
        //students.remove(id);
        studentRepository.deleteById(id);
        return true;
    }

    public Iterable<Student> deleteAll() {
        /*for (int i = 0; i < students.size(); i++) {
            students.remove(i);
        }*/
        studentRepository.deleteAll();
        /*for (Student student : studentRepository.findAll()) {
            studentRepository.deleteById(student.getId());
        }*/
        return null;
    }
}
