package org.example.stream.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {

    private Integer id;
    private String name;
    private Integer age;
    private Boolean gender;

    private List<Role> roles;

    public static List<User> listUser() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(1, "zs", 18, true));
        users.add(new User(2, "ls", 22, true));
        users.add(new User(3, "ww", 16, false));
        users.add(new User(4, "zl", 32, true));
        users.add(new User(5, "ll", 48, false));
        users.add(new User(6, "ls", 22, false));
        users.add(new User(7, "vl", 26, true));
        return users;
    }

    public User() {
    }

    public User(Integer id, String name, Integer age, Boolean gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(age, user.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
