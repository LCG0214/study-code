package cn.lcgui.stream.pojo;


import java.util.List;

/**
 * @author : LCG
 * @description : 用于测试 Stream的实体类
 * @since : 2024-12-09  15:43
 */
public class Student {

    private String name;

    private Integer age;

    private String sex;

    private List<Book> books;

    public Student(String name, Integer age, String sex, List<Book> books) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.books = books;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", books=" + books +
                '}';
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
