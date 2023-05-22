package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class StudentRepository {
    private HashMap<String,Student> studentLog = new HashMap<>();
    private HashMap<String,Teacher> teacherLog = new HashMap<>();
    private HashMap<String, List<String>> studentTeacherPair = new HashMap<>();


    public void addStudent(Student student) {
        studentLog.put(student.getName(),student);
    }

    public void addTeacher(Teacher teacher) {
        teacherLog.put(teacher.getName(),teacher);
    }

    public void makeStudentTeacherPair(String student, String teacher) {
        if(!studentLog.containsKey(student)){
            throw new StudentNotFound("Student Not Found");
        }
        if(!teacherLog.containsKey(teacher)){
            throw new TeacherNotFound("Teacher Not Found");
        }



        if(!studentTeacherPair.containsKey(teacher)){
            studentTeacherPair.put(teacher,new ArrayList<>());
        }
        studentTeacherPair.get(teacher).add(student);

    }

    public Optional<Student> getStudentByName(String name) {
        for(String name1 : studentLog.keySet()){
            if(name.equals(name1)){
                return Optional.of(studentLog.get(name));
            }
        }
        return Optional.empty();
    }

    public Optional<Teacher> getTeacherByName(String name) {
        for(String name1 : teacherLog.keySet()){
            if(name.equals(name1)){
                return Optional.of(teacherLog.get(name));
            }
        }
        return Optional.empty();
    }

    public Optional<List<String>> getStudentsByTeacherName(String teacher) {
        if(!teacherLog.containsKey(teacher)){
            return Optional.empty();
        }
        return Optional.of(studentTeacherPair.get(teacher));
    }

    public List<String> getAllStudents() {
        return new ArrayList<>(studentLog.keySet());
    }

    public boolean checkTeacherInPair(String teacher) {
        return studentTeacherPair.containsKey(teacher);
    }

    public void deleteTeacherByName(String teacher) {
        studentTeacherPair.remove(teacher);
    }

    public void deleteAllTeachers() {
        studentTeacherPair.clear();
    }
}
