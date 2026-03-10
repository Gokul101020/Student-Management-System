
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();
        while (true) {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Roll No: ");
                    String rollNo = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Department: ");
                    String dept = scanner.nextLine();
                    System.out.print("Enter Marks: ");
                    double marks = scanner.nextDouble();
                    scanner.nextLine();

                    Student student = new Student(rollNo, name, dept, marks);
                    dao.addStudent(student);
                    break;

                case 2:
                    List<Student> students = dao.getAllStudents();
                    if (students.isEmpty()) {
                        System.out.println("No students found.");
                    } else {
                        for (Student s : students) {
                            System.out.println(s);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter Student ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();

                    Student existing = dao.getStudentById(updateId);
                    if (existing == null) {
                        System.out.println("Student not found!");
                        break;
                    }

                    System.out.println("Current: " + existing);

                    System.out.print("New Name (or Enter to keep): ");
                    String newName = scanner.nextLine();
                    if (!newName.isEmpty()) {
                        existing.setName(newName);
                    }

                    System.out.print("New Department (or Enter to keep): ");
                    String newDept = scanner.nextLine();
                    if (!newDept.isEmpty()) {
                        existing.setDepartment(newDept);
                    }

                    System.out.print("New Marks (or -1 to keep): ");
                    double newMarks = scanner.nextDouble();
                    scanner.nextLine();
                    if (newMarks != -1) {
                        existing.setMarks(newMarks);
                    }

                    dao.updateStudent(updateId, existing.getName(), existing.getDepartment(), existing.getMarks());
                    break;

                case 4:
                    System.out.print("Enter Student ID to delete: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    dao.deleteStudent(deleteId);
                    break;

                case 5:
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
