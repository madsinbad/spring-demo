package demo.threadtest;

import java.util.*;
import java.util.stream.Stream;

/**
 * @ClassName: demo.threadtest.StreamTest
 * @Description:
 * @Author: weizhengbo
 * @Date: 2023/5/23 15:22
 * @Version: 1.0
 */

class Student {
    private String name;
    private int age;
    private int score;

    public Student(String name, int age, int score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age &&
                score == student.score &&
                Objects.equals(name, student.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, age, score);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
}


public class StreamTest {
    public static Stream<Student> getDataSource() {
        // 1. 实例化一个集合，存Student对象
        List<Student> list = new ArrayList<>();
        // 2. 添加若干数据
        Collections.addAll(list,
                new Student("xiaoming",17,90),
                new Student("xiaohong",18,100),
                new Student("xiaohong",21,100),
                new Student("xiaolan",18,90),
                new Student("xiaoli",19,68),
                new Student("xiaobai",18,72),
                new Student("xiaohei",20,56),
                new Student("xiaoke",17,93),
                new Student("xiaoqing",19,79),
                new Student("xiaofei",18,56)
        );

        // 3. 读取数据源，得到Stream对象
        return list.stream();
    }
    public static void main(String[] args) {
        Stream<Student> dataSource = getDataSource();
//        dataSource.filter(s -> s.getScore() >= 60).forEach((item) -> System.out.println(item.getName()));
        dataSource.sorted(Comparator.comparing(Student::getScore).thenComparing(Student::getName)).forEach((item) -> System.out.println(item.getName()));
        dataSource.close();
    }
}
