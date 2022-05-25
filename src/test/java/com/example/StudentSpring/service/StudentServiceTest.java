package com.example.StudentSpring.service;

import com.example.StudentSpring.model.Student;
import com.example.StudentSpring.repo.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;

    private StudentService underTest;

    @BeforeEach
    void setUp(){
        underTest=new StudentService(studentRepository);
    }

    @Test
    void getStudents() {
        //when

        underTest.getStudents();

        //then

        verify(studentRepository).findAll();

    }

    @Test
    void addNewStudent() {
        //given
        Student student =new Student(
                "Andy",
                "adny@gmail.com",
                LocalDate.of(2001, Month.JANUARY, 5)

        );
        //when
        underTest.addNewStudent(student);

        //then
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);

        verify(studentRepository).save(studentArgumentCaptor.capture());

        Student capturedStudent=studentArgumentCaptor.getValue();
        assertThat(capturedStudent).isEqualTo(student);


    }


}