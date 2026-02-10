import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static class Employee {
        private String name;
        private int age;
        private String gender;
        private double salary;
        private String designation;
        private String department;

        public Employee(String name, int age, String gender, double salary, String designation, String department) {
            this.name = name;
            this.age = age;
            this.gender = gender;
            this.salary = salary;
            this.designation = designation;
            this.department = department;
        }

        public String getName() { return name; }
        public int getAge() { return age; }
        public String getGender() { return gender; }
        public double getSalary() { return salary; }
        public String getDesignation() { return designation; }
        public String getDepartment() { return department; }
        public void setSalary(double salary) { this.salary = salary; }

        @Override
        public String toString() {
            return name + " | " + age + " | " + gender + " | " + salary + " | " + designation + " | " + department;
        }
    }

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", 45, "Female", 95000, "Manager", "HR"),
            new Employee("Bob", 38, "Male", 70000, "Developer", "IT"),
            new Employee("Charlie", 50, "Male", 120000, "Manager", "Finance"),
            new Employee("Diana", 28, "Female", 60000, "Developer", "IT"),
            new Employee("Ethan", 35, "Male", 65000, "Tester", "QA"),
            new Employee("Fiona", 32, "Female", 68000, "Developer", "IT"),
            new Employee("George", 41, "Male", 80000, "Team Lead", "IT"),
            new Employee("Hannah", 29, "Female", 55000, "Tester", "QA"),
            new Employee("Ian", 36, "Male", 72000, "Developer", "IT"),
            new Employee("Jane", 33, "Female", 67000, "Developer", "IT"),
            new Employee("Kyle", 40, "Male", 75000, "Team Lead", "IT"),
            new Employee("Laura", 27, "Female", 58000, "Tester", "QA"),
            new Employee("Mike", 31, "Male", 66000, "Developer", "IT"),
            new Employee("Nina", 42, "Female", 90000, "Manager", "Sales"),
            new Employee("Oscar", 39, "Male", 71000, "Developer", "IT"),
            new Employee("Paula", 34, "Female", 69000, "Developer", "IT"),
            new Employee("Quentin", 48, "Male", 115000, "Manager", "Operations"),
            new Employee("Rachel", 30, "Female", 62000, "Tester", "QA"),
            new Employee("Steve", 37, "Male", 73000, "Developer", "IT"),
            new Employee("Tina", 26, "Female", 56000, "Tester", "QA"),
            new Employee("Uma", 44, "Female", 88000, "Manager", "IT"),
            new Employee("Victor", 29, "Male", 64000, "Developer", "IT"),
            new Employee("Wendy", 35, "Female", 67000, "Developer", "IT"),
            new Employee("Xander", 33, "Male", 70000, "Developer", "IT"),
            new Employee("Yara", 38, "Female", 72000, "Team Lead", "IT")
        );

        Employee highestPaid = employees.stream().max(Comparator.comparingDouble(Employee::getSalary)).orElse(null);
        System.out.println("Highest Paid: " + highestPaid);

        Map<String, Long> genderCount = employees.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        System.out.println("Gender Count: " + genderCount);

        Map<String, Double> deptExpense = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.summingDouble(Employee::getSalary)));
        System.out.println("Department Expenses: " + deptExpense);

        List<Employee> top5Senior = employees.stream().sorted(Comparator.comparingInt(Employee::getAge).reversed()).limit(5).collect(Collectors.toList());
        System.out.println("Top 5 Senior Employees: " + top5Senior);

        List<String> managers = employees.stream().filter(e -> e.getDesignation().equalsIgnoreCase("Manager")).map(Employee::getName).collect(Collectors.toList());
        System.out.println("Managers: " + managers);

        employees.stream().filter(e -> !e.getDesignation().equalsIgnoreCase("Manager")).forEach(e -> e.setSalary(e.getSalary() * 1.2));
        System.out.println("Salaries after hike:");
        employees.forEach(System.out::println);

        long totalEmployees = employees.stream().count();
        System.out.println("Total Employees: " + totalEmployees);
    }
}
