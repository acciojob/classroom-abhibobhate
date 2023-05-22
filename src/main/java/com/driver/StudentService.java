package com.driver;

import java.util.List;
import java.util.Optional;

public class StudentService {
    StudentRepository studentRepository = new StudentRepository();
    public void addStudent(Student student) {
        studentRepository.addStudent(student);
    }

    public void addTeacher(Teacher teacher) {
        studentRepository.addTeacher(teacher);
    }

    public void makeStudentTeacherPair(String student, String teacher) {
        try{
            studentRepository.makeStudentTeacherPair(student,teacher);
        }catch (StudentNotFound s){
            System.out.println(s.toString());
        }catch (TeacherNotFound t){
            System.out.println(t.toString());
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public Student getStudentByName(String name) {
        Optional<Student> student = studentRepository.getStudentByName(name);
        if(student.isEmpty()){
            throw new StudentNotFound("Student Not Found");
        }
        return student.get();
    }

    public Teacher getTeacherByName(String name) {
        Optional<Teacher> teacher = studentRepository.getTeacherByName(name);
        if(teacher.isEmpty()){
            throw new TeacherNotFound("Teacher Not Found");
        }
        return teacher.get();
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        Optional<List<String>> students = studentRepository.getStudentsByTeacherName(teacher);
        if(students.isEmpty()){
            throw new TeacherNotFound("Teacher NOT Found");
        }
        return students.get();
    }

    public List<String> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    public void deleteTeacherByName(String teacher) {
        if(!studentRepository.checkTeacherInPair(teacher)){
            throw new TeacherNotFound("Teacher Not Found");
        }
        studentRepository.deleteTeacherByName(teacher);
    }

    public void deleteAllTeachers() {
        studentRepository.deleteAllTeachers();
    }
}
