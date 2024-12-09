package cn.lcgui.stream;


import cn.lcgui.stream.pojo.Book;
import cn.lcgui.stream.pojo.Student;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : LCG
 * @description :
 * @since : 2024-12-02  15:49
 */
public class StreamTest {
    /**
     * 生成测试数据
     * @return
     */
    public List<Student> getStudentList(){
        // 随机生成十个Book对象类型数据
        return Arrays.asList(
                new Student("张三", 18, "男", Arrays.asList(
                        new Book("Java", "小王,小李"),
                        new Book("C++", "小张"),
                        new Book("Python", "小唐,小李")
                )),
                new Student("李四", 19, "男", Collections.singletonList(
                        new Book("语文", "李不白")
                )),
                new Student("王五", 20, "男", Arrays.asList(
                        new Book("高数", "小黑子"),
                        new Book("算法", "菜虚坤"),
                        new Book("Python", "小唐,小李")
                )),
                new Student("赵六", 21, "男", Arrays.asList(
                        new Book("Java", "小王,小李"),
                        new Book("美术", "自然艺术"),
                        new Book("计算机网络", "🐓你太美,baby")
                )),
                new Student("钱七", 22, "男", Arrays.asList(
                        new Book("C++", "李四"),
                        new Book("高数", "小黑子"),
                        new Book("算法", "菜虚坤")
                ))
        );
    }

    /**
     * forEach用法：终止操作
     */
    @Test
    public void forEachTest(){
        // forEach属于终止操作，作用就是循环
        List<Student> studentList = getStudentList();  // 获取数据
        studentList.forEach(student -> System.out.println(student.getName()));
        //输出结果：
        //张三
        //李四
        //王五
        //赵六
        //钱七
    }

    /**
     * limit用法：中间操作
     */
    @Test
    public void limitTest(){
        // limit方法的作用是截取，例如：只获取前5个数据：
        List<Student> studentList = getStudentList();  // 获取数据
        studentList.stream()
                .limit(5)
                .forEach(student -> System.out.println(student.getName()));
        //张三
        //李四
        //王五
        //赵六
        //钱七
    }

    /**
     * skip用法：中间操作
     */
    @Test
    public void skipTest(){
        // skip方法的作用是跳过前5个数据：
        List<Student> studentList = getStudentList();  // 获取数据
        studentList.stream()
                .skip(5)
                .forEach(student -> System.out.println(student.getName()));
        //钱七
    }

    /**
     * filter用法：中间操作
     */
    @Test
    public void filterTest(){
        // filter方法的作用是筛选，例如：筛选年龄大于等于20岁以上的学生：
        List<Student> studentList = getStudentList(); // 获取数据
        studentList.stream()
                .filter(student -> student.getAge() >= 20)
                .forEach(student -> System.out.println(student.getName()));
        //王五
        //赵六
        //钱七
    }

    /**
     * map用法：中间操作
     */
    @Test
    public void mapTest(){
        // map方法的作用是转换，转换目标的对象，例如 我现在的数据是List<Student>类型，那么我想获取到所有学生的姓名，
        // 最终获取到的数据就应该是List<String>类型：
        List<Student> studentList = getStudentList(); // 获取数据
        studentList.stream()
                .map(Student::getName) // lambda表达式 和 .map(student -> student.getName())效果相同
                .forEach(name -> {
                    // 这里是可以遍历的  说明肯定是一个集合或者数组
                    System.out.println("判断是否为字符串类型：" + name instanceof String);
                });
    }

    /**
     * collect用法：终止操作
     */
    @Test
    public void collectTest(){
        // collect方法的作用是收集，
        //collect方法中常用的方法有： toList、toSet、toMap、joining、
        // reducing、groupingBy、partitioningBy、sorted、
        // noneMatch、findFirst、anyMath
        List<Student> studentList = getStudentList();
        // toList的作用是：把流中的元素收集到一个List中
        List<String> names = studentList.stream()
                .map(Student::getName)
                .collect(Collectors.toList());
        System.out.println(names); // [张三, 李四, 王五, 赵六, 钱七]
        // toSet的作用是：把流中的元素收集到一个Set中
        Set<String> nameSet = studentList.stream()
                .map(Student::getName)
                .collect(Collectors.toSet());
        System.out.println(nameSet); // [钱七, 李四, 张三, 王五, 赵六]
        // toMap的作用是：把流中的元素收集到一个Map中
        Map<String, Integer> nameMap = studentList.stream()
                // 等同于 .collect(Collectors.toMap(student -> student.getName(), student -> student.getAge()));
                .collect(Collectors.toMap(Student::getName, Student::getAge));
        System.out.println(nameMap); // {钱七=22, 李四=19, 张三=18, 王五=20, 赵六=21}
        // joining的作用是：把流中的元素收集到一个字符串中
        String nameString = studentList.stream()
                .map(Student::getName)
                .collect(Collectors.joining(","));
        System.out.println(nameString); // 张三,李四,王五,赵六,钱七
        // reducing的作用是：聚合，例如：求年龄的总和：
        Optional<Integer> sumAge = studentList.stream()
                .map(Student::getAge)
                .reduce(Integer::sum);
        if (sumAge.isPresent()) {
            System.out.println(sumAge.get()); // 100
        }
        // groupingBy的作用是：分组，例如：按照年龄分组：
        Map<Integer, List<Student>> ageMap = studentList.stream()
                .collect(Collectors.groupingBy(Student::getAge));
        System.out.println(ageMap); // {18=[Student{name='张三', age=18, sex='男', books=[Book{name='Java', author='小王,小李'}, Book{name='C++', author='小张'}, Book{name='Python', author='小唐,小李'}]}], 19=[Student{name='李四', age=19, sex='男', books=[Book{name='语文', author='李不白'}]}], 20=[Student{name='王五', age=20, sex='男', books=[Book{name='高数', author='小黑子'}, Book{name='算法', author='菜虚坤'}, Book{name='Python', author='小唐,小李'}]}], 21=[Student{name='赵六', age=21, sex='男', books=[Book{name='Java', author='小王,小李'}, Book{name='美术', author='自然艺术'}, Book{name='计算机网络', author='🐓你太美,baby'}]}], 22=[Student{name='钱七', age=22, sex='男', books=[Book{name='C++', author='李四'}, Book{name='高数', author='小黑子'}, Book{name='算法', author='菜虚坤'}]}]}
        // partitioningBy的作用是：分区，例如：按照年龄是否大于20岁进行分区：
        Map<Boolean, List<Student>> partitionMap = studentList.stream()
                .collect(Collectors.partitioningBy(student -> student.getAge() > 20));
        System.out.println(partitionMap);  // {false=[Student{name='张三', age=18, sex='男', books=[Book{name='Java', author='小王,小李'}, Book{name='C++', author='小张'}, Book{name='Python', author='小唐,小李'}]}, Student{name='李四', age=19, sex='男', books=[Book{name='语文', author='李不白'}]}, Student{name='王五', age=20, sex='男', books=[Book{name='高数', author='小黑子'}, Book{name='算法', author='菜虚坤'}, Book{name='Python', author='小唐,小李'}]}], true=[Student{name='赵六', age=21, sex='男', books=[Book{name='Java', author='小王,小李'}, Book{name='美术', author='自然艺术'}, Book{name='计算机网络', author='🐓你太美,baby'}]}, Student{name='钱七', age=22, sex='男', books=[Book{name='C++', author='李四'}, Book{name='高数', author='小黑子'}, Book{name='算法', author='菜虚坤'}]}]}
        // sorted的作用是：排序，例如：按照年龄排序：
        List<Student> sortedList = studentList.stream()
                .sorted(Comparator.comparingInt(Student::getAge))
                .collect(Collectors.toList());
        System.out.println(sortedList); // [Student{name='张三', age=18, sex='男', books=[Book{name='Java', author='小王,小李'}, Book{name='C++', author='小张'}, Book{name='Python', author='小唐,小李'}]}, Student{name='李四', age=19, sex='男', books=[Book{name='语文', author='李不白'}]}, Student{name='王五', age=20, sex='男', books=[Book{name='高数', author='小黑子'}, Book{name='算法', author='菜虚坤'}, Book{name='Python', author='小唐,小李'}]}, Student{name='赵六', age=21, sex='男', books=[Book{name='Java', author='小王,小李'}, Book{name='美术', author='自然艺术'}, Book{name='计算机网络', author='🐓你太美,baby'}]}, Student{name='钱七', age=22, sex='男', books=[Book{name='C++', author='李四'}, Book{name='高数', author='小黑子'}, Book{name='算法', author='菜虚坤'}]}]
        // noneMatch的作用是：判断是否所有元素都不满足某个条件：
        boolean noneMatch = studentList.stream()
                .noneMatch(student -> student.getAge() > 18);
        System.out.println(noneMatch); // false  如果是 student.getAge() > 30 ： true
        // findFirst的作用是：返回流中的第一个元素：
        Optional<Student> first = studentList.stream()
                .findFirst();
        if (first.isPresent()) {
            System.out.println(first.get()); // Student{name='张三', age=18, sex='男', books=[Book{name='Java', author='小王,小李'}, Book{name='C++', author='小张'}, Book{name='Python', author='小唐,小李'}]}
        }
        // anyMath的作用是：判断是否至少有一个元素满足某个条件，判断是否至少有一个女生：
        boolean anyMatch = studentList.stream()
                .anyMatch(student -> "女".equals(student.getSex()));
        System.out.println(anyMatch); // false
    }

    /**
     * flatMap用法：中间操作
     */
    @Test
    public void flatMapTest(){
        // flatMap方法的作用是把流中的每个值都换成另一个流，然后把所有流连接成一个流：
        // 可以这样理解：如果使用map方法的话.map(student -> student.getBooks())
        // 这样会获取到Stream<List<Book>>类型的数据，但是我想获取到的是Book对象本身而不是他的集合，
        // 所以需要使用flatMap方法：.flatMap(student -> student.getBooks().stream())可以直接获取到Stream<Book>类型数据
        List<Student> studentList = getStudentList();
        studentList.stream()
                .flatMap(student -> student.getBooks().stream())
                .forEach(book -> System.out.println(book.getName()));
    }

    /**
     * peek用法：中间操作
     */
    @Test
    public void peekTest(){
        // peek方法的作用是：在流中插入一个中间操作，用于对流中的元素进行一些操作，但不会改变流中的元素：
        List<Student> studentList = getStudentList();
        studentList.stream()
                .filter(student -> student.getAge() > 20)
                .peek(student -> System.out.println(student.getName())) //赵六 钱七
                .collect(Collectors.toList());
    }

    /**
     * distinct用法：中间操作
     */
    @Test
    public void distinctTest(){
        // distinct的作用是：去重，例如：去重后，打印出所有的书籍名称：
        List<Student> studentList = getStudentList();
        studentList.stream()
                .flatMap(student -> student.getBooks().stream())
                .distinct()
                .forEach(book -> System.out.println(book.getName()));
    }

    /**
     * 并行流的用法：中间操作
     * 上面的用法，在并行流中照样适用，不过的是上面的是串行流，下面的是并行流。可以理解为单线程和多线程的区别
     */
    @Test
    public void parallelStreamTest(){
        List<Student> studentList = getStudentList();
        studentList.parallelStream()
                .filter(student -> student.getAge() > 20)
                .peek(student -> System.out.println("Thread: " + Thread.currentThread().getName()))
                .forEach(student -> System.out.println(student.getName()));
        // 输出的线程名：
        //Thread: main
        //Thread: ForkJoinPool.commonPool-worker-2
    }

}
