package com.Task00A1.A_task.week2nd;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test2{
    public static void main(String[] args) {
        // Upload student data from the CSV file
        List<Student> students = uploadStudent("/home/dell/Downloads/Filefolder//student.csv");

        // Upload class data from the CSV file
        List<Class> classes = uploadClass("/home/dell/Downloads/Filefolder//class.csv");

        // Upload address data from the CSV file
        List<Address> addresses = uploadAddress("/home/dell/Downloads/Filefolder/address.csv");

        // Paginated read of females students from records 1 to 9
        List<Student> studentResult = fun1(students, "F", 1, 9);
        for (Student student : studentResult) {
            System.out.println(student);
        }

     }

    static class Student {
        int id;
        String name;
        int classId;
        int marks;
        String gender;
        int age;

        public Student(int id, String name, int classId, int marks, String gender, int age) {
            this.id = id;
            this.name = name;
            this.classId = classId;
            this.marks = marks;
            this.gender = gender;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", classId=" + classId +
                    ", marks=" + marks +
                    ", gender='" + gender + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    static class Class {
        int id;
        String name;

        public Class(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Class{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    // Define the Address class
    static class Address {
        int id;
        String pinCode;
        String city;
        int studentId;

        public Address(int id, String pinCode, String city, int studentId) {
            this.id = id;
            this.pinCode = pinCode;
            this.city = city;
            this.studentId = studentId;
        }

        @Override
        public String toString() {
            return "Address{" +
                    "id=" + id +
                    ", pinCode='" + pinCode + '\'' +
                    ", city='" + city + '\'' +
                    ", studentId=" + studentId +
                    '}';
        }
    }

    // Function to upload student data from a CSV file
    static List<Student> uploadStudent(String filePath) {
        List<Student> studentList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split("\\|");
                int id = Integer.parseInt(fields[0].trim());
                String name = fields[1].trim();
                int classId = Integer.parseInt(fields[2].trim());
                int marks = Integer.parseInt(fields[3].trim());
                String gender = fields[4].trim();
                int age = Integer.parseInt(fields[5].trim());
                Student student = new Student(id, name, classId, marks, gender, age);
                studentList.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return studentList;
    }

    // Function to upload class data from a CSV file
    static List<Class> uploadClass(String filePath) {
        List<Class> classList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split("\\|");
                int id = Integer.parseInt(fields[0].trim());
                String name = fields[1].trim();
                Class classObj = new Class(id, name);
                classList.add(classObj);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return classList;
    }

    static List<Address> uploadAddress(String filePath) {
        List<Address> addressList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split("\\|");
                int id = Integer.parseInt(fields[0].trim());
                String pinCode = fields[1].trim();
                String city = fields[2].trim();
                int studentId = Integer.parseInt(fields[3].trim());
                Address address = new Address(id, pinCode, city, studentId);
                addressList.add(address);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return addressList;
    }

    static <T> List<T> fun1(List<T> data, String gender, int startRecord, int endRecord) {
        List<T> result = new ArrayList<>();

        for (T item : data) {
            if (item instanceof Student) {
                Student student = (Student) item;
                if (student.gender.equals(gender)) {
                    result.add(item);
                }
            }
        }

        int totalRecords = result.size();
        int startIndex = Math.min(startRecord - 1, totalRecords);
        int endIndex = Math.min(endRecord, totalRecords);

        return result.subList(startIndex, endIndex);
    }
}