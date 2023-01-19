package java8.servises;

import java8.enums.Gender;
import java8.models.Student;
import java8.dao.StudentDao;
import java8.dao.StudentDaoImpl;

import java.util.List;
import java.util.Map;

/**
 * Shabdanov Ilim
 **/
public class StudentServiceImpl implements StudentService{
    StudentDao studentDao = new StudentDaoImpl();
    public String createTable() {
        studentDao.createTable();
        return "Successfully created !";
    }

    public String saveStudents(Student student) {
        studentDao.saveStudents(student);
        return "Successfully saved ! ";
    }
    public Student findByStudentId(Long id) {
        return studentDao.findByStudentId(id);
    }

    public List<Student> findAllStudents() {
        return  studentDao.findAllStudents();
    }

    public String updateStudents(Long studentId, Student newStudent) {
        studentDao.updateStudents(studentId,newStudent);
        return "Successfully updated !";
    }

    public String deleteByStudentId(Long studentId) {
        studentDao.deleteByStudentId(studentId);
        return "Successfully delete !";
    }

    @Override
    public List<Student> getAllStudentsSortByAge(String ascOrDesc) {
        return studentDao.getAllStudentsSortByAge(ascOrDesc);
    }

    @Override
    public boolean checkByAge() {
        return studentDao.checkByAge();
    }

    @Override
    public void addColumnGender() {
      studentDao.addColumnGender();
    }

    @Override
    public Map<Gender, List<Student>> gruopByGender() {
        return studentDao.gruopByGender();
    }

    @Override
    public void deleteAllStudents() {
  studentDao.deleteAllStudents();
    }
}
