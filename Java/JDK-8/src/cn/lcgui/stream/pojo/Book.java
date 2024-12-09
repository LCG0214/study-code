package cn.lcgui.stream.pojo;

/**
 * @author : LCG
 * @description : TODO
 * @since : 2024-12-09  15:44
 */
public class Book {

    private String name;

    private String author; // 多个作者之间,间隔，例如：张三,李四,王五


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public Book(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
