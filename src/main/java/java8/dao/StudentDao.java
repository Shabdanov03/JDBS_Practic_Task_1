package java8.dao;

import java8.enums.Gender;
import java8.models.Student;

import java.util.List;
import java.util.Map;

/**
 * Shabdanov Ilim
 **/
public interface StudentDao {
    //create table
    void createTable();
    // save students
    void saveStudents(Student student);


    // find by students id
    Student findByStudentId(Long id);

    // findAllStudents
    List<Student> findAllStudents();

   // update students
    void updateStudents(Long studentId,Student newStudent);

    //delete
    void deleteByStudentId(Long studentId);
    List<Student> getAllStudentsSortByAge(String ascOrDesc);

    boolean checkByAge();

    void addColumnGender();

    Map<Gender, List<Student>> gruopByGender();

    void deleteAllStudents(); //ddl


}
