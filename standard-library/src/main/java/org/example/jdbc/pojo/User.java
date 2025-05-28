package org.example.jdbc.pojo;

import java.util.Objects;

public class User {
    private Long id;

    private String name;

    private Integer age;

    private Double points;

    public User() {
    }

    public User(Long id, String name, Integer age, Double points) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.points = points;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", points=" + points +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(age, user.age) && Objects.equals(points, user.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, points);
    }
}
