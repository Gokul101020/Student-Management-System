
public class Student {

private int id;
private String rollNo;
private String name;
private String department;
private double marks;

// Constructot for adding new student(no ID yet)
public Student(String rollNo, String name, String department, double marks){
    this.rollNo = rollNo;
    this.name = name;
    this.department = department;
    this.marks = marks;
}

// Constructor for reading from database (with ID)
public Student(int id, String rollNo, String name, String department, double marks){
    this.id = id;
    this.rollNo = rollNo;
    this.name = name;
    this.department = department;
    this.marks = marks;
}
//gettters and setters
public int getId(){return id;}
public void setId(int id) { this.id = id; }

public String getRollNo() {return rollNo; }
public void setRollNo(String rollNo) {this.rollNo = rollNo;}

public String getName() {return name; }
public void setName(String name) {this.name = name;}

public String getDepartment() {return department; }
public void setDepartment(String department) {this.department = department;}

public double getMarks() {return marks; }
public void setMarks(double marks) {this.marks = marks;}

@Override
public String toString(){
    return "ID: " + id + "| Roll: " + rollNo + "| Name: " + name + 
            "|Department: " + department + "|Marks: " + marks;
}

}
