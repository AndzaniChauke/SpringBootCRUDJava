package com.example.StudentSpring.repo;

import com.example.StudentSpring.model.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }

    @Test
    void findStudentByEmail() {
        //given

        String email="adny@gmail.com";
        Student student =new Student(
                "Andy",
                email,
                LocalDate.of(2001, Month.JANUARY, 5)

        );

        underTest.save(student);


        //when


        Optional<Student> exists = underTest.findStudentByEmail(email);


        //then
        assertThat(exists).isPresent();
    }
}