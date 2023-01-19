package java8.servises;

import java8.enums.Gender;
import java8.models.Student;

import java.util.List;
import java.util.Map;

/**
 * Shabdanov Ilim
 **/
public interface StudentService {

    //create table
    String createTable();
    // save students
    String saveStudents(Student student);


    // find by students id
    Student findByStudentId(Long id);

    // findAllStudents
    List<Student> findAllStudents();

    // update students
    String updateStudents(Long studentId,Student newStudent);

    //delete
    String deleteByStudentId(Long studentId);

    List<Student> getAllStudentsSortByAge(String ascOrDesc);

    boolean checkByAge();

    void addColumnGender();

    Map<Gender, List<Student>> gruopByGender();

    void deleteAllStudents(); //ddl

}
