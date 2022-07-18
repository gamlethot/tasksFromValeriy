package Task2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    @Getter
    @AllArgsConstructor
   static class User {
        private String username;
        private Integer age;
        private List<Group> groups;

        @Override
        public String toString() {
            return "User{" +
                    "username='" + username + '\'' +
                    ", age=" + age +
                    ", groups=" + groups +
                    '}';
        }
    }


    @Getter
    static class Group {
        private String name;
        private String description;

        public Group(String name, String description) {
            this.name = name;
            this.description = description;
        }

        @Override
        public String toString() {
            return "Group{" +
                    "name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }


    public static class Runner {
        public static void main(String[] args) {
            Group group1 = new Group("Hi", "1");
            Group group2 = new Group("L", "2");
            Group group3 = new Group("HO", "3");
            List<Group> groups = new ArrayList<>(List.of(group1, group2, group3));

            Group group4 = new Group("i", "1");
            Group group5 = new Group("k", "2");
            Group group6 = new Group("n", "3");
            List<Group> groups2 = new ArrayList<>(List.of(group4, group5, group6));

            Group group7 = new Group("i", "1");
            Group group8 = new Group("Her", "2");
            Group group9 = new Group("n", "3");
            List<Group> groups3 = new ArrayList<>(List.of(group7, group8, group9));

            User user1 = new User("U1", 1, groups);
            User user2 = new User("U2", 1, groups2);
            User user3 = new User("U3", 1, groups3);

            List<User> userList = new ArrayList<>(List.of(user1, user2, user3));
            System.out.println(consume(userList.stream()));

        }

        //вернуть список User у которых хотя бы одна группа начинается с H
        public static List<User> consume(Stream<User> usersStream) {
            return usersStream
                    .filter(user ->
                            user.getGroups().stream()
                                    .anyMatch(group ->
                                            group.getName().startsWith("H")))
                    .collect(Collectors.toList());
        }
    }
}
