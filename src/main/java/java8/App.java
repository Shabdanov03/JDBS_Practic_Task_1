package java8;

import java8.enums.Gender;
import java8.models.Student;
import java8.servises.StudentService;
import java8.servises.StudentServiceImpl;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        StudentService studentService = new StudentServiceImpl();
        Student student = new Student("Ilim", (byte) 19);
        Student student1 = new Student("Iskhak", (byte) 21);
        Student student2 = new Student("Dastan", (byte) 20);
        Student newStudent = new Student(21L, "Nuradil", (byte) 18);

        while (true) {
            System.out.println("""
                    1. create students 
                    2. save students 
                    3. Find by student id !
                    4. get all students
                    5. update students 
                    6. delete students :
                    7.get all students sort :
                    8.check by  age
                    9.add column gender :
                    10.group by gender :
                    11.delete all students : 
                                        
                    """);
            System.out.println("Enter by commands : ");
            int a = new Scanner(System.in).nextInt();
            switch (a) {
                case 1 -> System.out.println(studentService.createTable());
                case 2 -> System.out.println(studentService.saveStudents(student));
                case 3 -> {
                    System.out.println("Enter by id  :");
                    Long id = new Scanner(System.in).nextLong();
                    System.out.println(studentService.findByStudentId(id));
                }
                case 4 -> System.out.println(studentService.findAllStudents());
                case 5 -> {
                    System.out.println("Enter by id :");
                    Long id = new Scanner(System.in).nextLong();
                    System.out.println(studentService.updateStudents(id, newStudent));
                }
                case 6 -> {
                    System.out.println("Enter by id : ");
                    Long id = new Scanner(System.in).nextLong();
                    System.out.println(studentService.deleteByStudentId(id));
                }
                case 7 -> {
                    System.out.println("Enter by commands :(asc / desc ");
                    String word = new Scanner(System.in).nextLine();
                    studentService.getAllStudentsSortByAge(word).forEach(System.out::println);
                }
                case 8 -> System.out.println(studentService.checkByAge());
                case 9 -> {
                    studentService.addColumnGender();
                }
                case 10-> System.out.println(studentService.gruopByGender());
                case 11 -> studentService.deleteAllStudents();
                default -> System.out.println("No such command!");

            }
        }

    }
}
