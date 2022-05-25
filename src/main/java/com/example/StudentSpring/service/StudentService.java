package com.example.StudentSpring.service;

import com.example.StudentSpring.model.Student;
import com.example.StudentSpring.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentEmailAvail = studentRepository.findStudentByEmail(student.getEmail());
        if(studentEmailAvail.isPresent()){
            throw new IllegalStateException("email taken");

        }else{
            studentRepository.save(student);
        }

        System.out.println(student);
    }

    public void deleteStudent(Long studentId) {

        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException("student with id "+studentId+" does not exists");
        }else{
            studentRepository.deleteById(studentId);
        }


    }
    @Transactional
    public void updateStudent(Long studentId, String name, String email) {

        //localhost:8080/api/v1/student/1?name=Maria
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()->new IllegalStateException("student with id"+studentId+"does not exist"));

        if(name!=null&&name.length()>0 && !Objects.equals(student.getName(),name)){
            student.setName(name);
        }

        if(email !=null && email.length() >0 && !Objects.equals(student.getEmail(),email)){
            Optional<Student> studentOptional=studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }

            student.setEmail(email);
        }

    }
}
