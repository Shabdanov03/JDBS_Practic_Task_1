package java8.models;

/**
 * Shabdanov Ilim
 **/
public class Student {
    private Long id;
    private String  name;
    private byte age;

    public Student(){

    }

    public Student(String name, byte age) {
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student(Long id, String name, byte age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "   Student  :" +
                "\nid : " + id+
                "\nname : '" + name +
                "\nage : " + age +
                "\n====================\n";
    }
}
