
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
// Add new student

    public void addStudent(Student student) {
        String sql = "INSERT INTO students (roll_no, name, department, marks) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, student.getRollNo());
            ps.setString(2, student.getName());
            ps.setString(3, student.getDepartment());
            ps.setDouble(4, student.getMarks());
            ps.executeUpdate();

            // Get auto-generated ID 
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                student.setId(rs.getInt(1));
            }
            System.out.println("Student added successfully! ID: " + student.getId());
        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }
// Get all students

    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students ORDER BY id";
        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Student s = new Student(rs.getInt("id"),
                        rs.getString("roll_no"), rs.getString("name"),
                        rs.getString("department"),
                        rs.getDouble("marks")
                );
                list.add(s);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching students: " + e.getMessage());
        }
        return list;
    }
// Update student

    public void updateStudent(int id, String name, String department, double marks) {
        String sql = "UPDATE students SET name = ?, department = ?, marks = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, department);
            ps.setDouble(3, marks);
            ps.setInt(4, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student ID " + id + " updated successfully!");
            } else {
                System.out.println("No student found with ID " + id);
            }
        } catch (SQLException e) {
            System.out.println("Error updating: " + e.getMessage());
        }
    }
// Delete student

    public void deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student ID " + id + " deleted!");
            } else {
                System.out.println("No student found with ID " + id);
            }
        } catch (SQLException e) {
            System.out.println("Error deleting: " + e.getMessage());
        }
    }
// Get one student by ID

    public Student getStudentById(int id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Student(
                        rs.getInt("id"),
                        rs.getString("roll_no"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getDouble("marks")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error fetching student: " + e.getMessage());
        }
        return null;
    }
}
