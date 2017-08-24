package reflection;

/**
 * <p>
 * Description: 
 * </p>
 *
 */
public class Employee {

    private int id;
    private String name;
    private int dept;
    private String gender;
    private int age;

    public Employee() {

    }

    public Employee(String name, int dept, String gender, int age) {
        this.name = name;
        this.dept = dept;
        this.gender = gender;
        this.age = age;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the dept
     */
    public int getDept() {
        return dept;
    }

    /**
     * @param dept the dept to set
     */
    public void setDept(int dept) {
        this.dept = dept;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return "name: " + name + "，dept :" + dept + "，gender :" + gender + "，age: " + age;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof Employee) {
            Employee emp = (Employee) obj;
            return emp.name == this.name && emp.dept == this.dept && emp.gender == this.gender;
        } else {
            return false;
        }
    }
}
