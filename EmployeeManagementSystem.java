import java.io.*;
import java.util.*;

class Employee {
    private int id;
    private String name;
    private double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Salary: " + salary;
    }
}

public class EmployeeManagementSystem {
    private static final String FILE_PATH = "employees.txt";
    private static List<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {
        loadEmployeesFromFile();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEmployee Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addEmployee(scanner);
                    break;
                case 2:
                    viewEmployees();
                    break;
                case 3:
                    updateEmployee(scanner);
                    break;
                case 4:
                    deleteEmployee(scanner);
                    break;
                case 5:
                    saveEmployeesToFile();
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void loadEmployeesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                double salary = Double.parseDouble(parts[2]);
                employees.add(new Employee(id, name, salary));
            }
        } catch (IOException e) {
            // Ignore if the file doesn't exist initially
        }
    }

    private static void saveEmployeesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Employee employee : employees) {
                writer.write(employee.getId() + "," + employee.getName() + "," + employee.getSalary());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addEmployee(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Employee Salary: ");
        double salary = scanner.nextDouble();
        employees.add(new Employee(id, name, salary));
        System.out.println("Employee added successfully!");
    }

    private static void viewEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        }
    }

    private static void updateEmployee(Scanner scanner) {
        System.out.print("Enter Employee ID to update: ");
        int id = scanner.nextInt();
        Employee employeeToUpdate = null;
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employeeToUpdate = employee;
                break;
            }
        }
        if (employeeToUpdate != null) {
            System.out.print("Enter new Employee Name: ");
            scanner.nextLine(); // Consume newline
            String name = scanner.nextLine();
            System.out.print("Enter new Employee Salary: ");
            double salary = scanner.nextDouble();
            employeeToUpdate = new Employee(id, name, salary);
            System.out.println("Employee updated successfully!");
        } else {
            System.out.println("Employee not found!");
        }
    }

    private static void deleteEmployee(Scanner scanner) {
        System.out.print("Enter Employee ID to delete: ");
        int id = scanner.nextInt();
        Employee employeeToDelete = null;
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employeeToDelete = employee;
                break;
            }
        }
        if (employeeToDelete != null) {
            employees.remove(employeeToDelete);
            System.out.println("Employee deleted successfully!");
        } else {
            System.out.println("Employee not found!");
        }
    }
}