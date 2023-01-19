package java8.dao;

import java8.config.Util;
import java8.enums.Gender;
import java8.models.Student;

import java.sql.*;
import java.util.*;

/**
 * Shabdanov Ilim
 **/
public class StudentDaoImpl implements StudentDao {

    private Connection connection;

    public StudentDaoImpl() {
        this.connection = Util.getConnection();
    }

    public void createTable() {
        try (Statement statement = connection.createStatement()) {
            String query = """
                    create table if not exists students(
                    id serial primary key,
                    name varchar(50) not null,
                    age smallint not null);
                    """;
            statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveStudents(Student student) {
        String query = """
                insert into  students (name,age)values(?,?)""";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Student findByStudentId(Long id) {
        Student studentname = new Student();
        String query = "select * from students where id = ? ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                studentname.setName(resultSet.getString("name"));
                studentname.setAge(resultSet.getByte("age"));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return studentname;
    }

    public List<Student> findAllStudents() {
        List<Student> studentList = new ArrayList<>();
        String query = "select * from students";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                studentList.add(new Student(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getByte("age")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return studentList;
    }

    public void updateStudents(Long studentId, Student newStudent) {
        String query = """
                update students 
                set name = ?,age = ? where id = ?""";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, newStudent.getName());
            preparedStatement.setInt(2, newStudent.getAge());
            preparedStatement.setLong(3, studentId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteByStudentId(Long studentId) {
        try {
            String query = " delete from students where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, studentId);
            System.out.println(preparedStatement.executeUpdate());
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Student> getAllStudentsSortByAge(String ascOrDesc) {
        List<Student> studentList = new ArrayList<>();
        String query = null;
        System.out.println("Enter select command : (age / name");
        String word = new Scanner(System.in).nextLine();
        switch (word) {
            case "age" -> {
                if (ascOrDesc.equals("asc")) {
                    query = "select * from students order by age";
                } else {
                    query = "select * from students order by age desc ";
                }
            }
            case "name" -> {
                if (ascOrDesc.equals("asc")) {
                    query = "select * from students order by name";
                } else {
                    query = "select * from students order by name desc ";
                }
            }
        }
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getByte("age"));
                studentList.add(student);
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return studentList;
    }

    @Override
    public boolean checkByAge() {
        String query = "select name, age,case when age > 18 then 'true' else 'false' end from students";
       // String query = "select  name,age from students where age > 18 group by name, age";
        boolean isTrue = false;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                resultSet.getString("name");
                resultSet.getByte("age");
                isTrue = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return isTrue;
    }

    @Override
    public void addColumnGender() {
        try (Statement statement = connection.createStatement()) {
            String query = """
                    alter table students add column gender Gender;
                    """;
            statement.executeQuery(query);
            System.out.println("Successfully added column !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Map<Gender, List<Student>> gruopByGender() {
            Map<Gender,List<Student>> result=new HashMap<>();
            List<Student>girls=new ArrayList<>();
            List<Student>boys=new ArrayList<>();


            String sql= """
                select * from students where gender='Female'; 
                """;
            try(Statement statement=connection.createStatement()){
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    Student student = new Student();
                    student.setId(resultSet.getLong("id"));
                    student.setName(resultSet.getString(2));
                    student.setAge(resultSet.getByte(3));
                    girls.add(student);
                }
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }

            String sql1= """
                select * from students where gender='Male'; 
                """;
            try(Statement statement1=connection.createStatement()){
                ResultSet resultSet1 = statement1.executeQuery(sql1);
                while (resultSet1.next()) {
                    Student student = new Student();
                    student.setId(resultSet1.getLong("id"));
                    student.setName(resultSet1.getString(2));
                    student.setAge(resultSet1.getByte(3));
                    boys.add(student);
                }
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
            result.put(Gender.FEMALE, girls);
            result.put(Gender.MALE, boys);

            return result;
        }

    @Override
    public void deleteAllStudents() {
        String query = "truncate students ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            System.out.println(preparedStatement.executeUpdate());
            System.out.println("Successfully delete all !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
