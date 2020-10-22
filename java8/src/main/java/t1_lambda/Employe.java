package t1_lambda;

import java.util.Objects;

/**
 * Create by lzm on 2020/10/22
 */
public class Employe implements Comparable<Employe> {

    private String name;

    private int salary;

    private int age;

    public Employe() {

    }

    public Employe(String name, int salary, int age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Employe{");
        sb.append("name='").append(name).append('\'');
        sb.append(", salary=").append(salary);
        sb.append(", age=").append(age);
        sb.append('}');
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Employe o) {
        return this.getSalary() - o.getSalary();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employe)) return false;
        Employe employe = (Employe) o;
        return salary == employe.salary &&
                age == employe.age &&
                Objects.equals(name, employe.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, salary, age);
    }
}
