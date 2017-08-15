package View;

import java.util.List;
import java.util.Scanner;

import DAO.StudentDAO;
import Model.Student;

//Class where all the methods and other classes are used to 
//execute the Application for the end user
public class Application 
{
	private static String opcao, op;
	private static String name;
	private static String email;
	private static String address;
	private static int id;
	static Scanner input = new Scanner(System.in);
	
	//Class where the Application is executed
	public static void main(String[] args) 
	{
		
		do
        {
            System.out.println("         -- System of Register Students -- ");
            System.out.println("\n            1 - Register Student");
            System.out.println("            2 - Alter Student");
            System.out.println("            3 - Delete Student");
            System.out.println("            4 - List Students");
            System.out.println("            0 - EXIT");
            
            opcao = input.nextLine();
            
            switch (opcao)
            {
                case "1":
                	System.out.println("         -- Register Student -- ");
                	InsertStudent();
                	break;
                
                case "2":
                	System.out.println("         -- Alter Student -- ");
                	AlterStudent();
                	break;
                	
                case "3":
                	System.out.println("         -- Delete Student -- ");
                	DeleteStudent();
                	break;
                	
                case "4":
                	System.out.println("         -- List of Registered Students -- ");
                	ListStudent();
                	break;
                
                case "0":
                	System.exit(0);
                	break;
            }
        }while (!opcao.equals("0"));
	}
	
	//Class that specify how the Insert function will happen
	private static void InsertStudent() 
	{
        StudentDAO dao = new StudentDAO();

        Student student = new Student();
        System.out.println("\nName: ");
        name = input.nextLine();
        student.setName(name);
        System.out.println("\nEmail: ");
        email = input.nextLine();
        student.setEmail(email);
        System.out.println("\nAddress: ");
        address = input.nextLine();
        student.setAddress(address);
        dao.InsertStudent(student);
        System.out.println("\n Student Registered successfully ");
       
    }
	
	//Class that specify how the Alter function will happen
	private static void AlterStudent() 
	{
        StudentDAO dao = new StudentDAO();

        Student student = new Student();
        System.out.println("\nID to UpDate: ");
        id = input.nextInt();
        student.setID(id);
        name = dao.ConfirmUpdateStudent(id);
        student.setName(name);
        System.out.println("\nName: " + name);
        System.out.println("\nDo you want to UpDate this Registration? (Y / N)");
        op = input.nextLine();
        if ((!op.equals("N")) || (!op.equals("n")) || (!op.equals("n")) || (!op.equals("n"))
        		||(!op.equals("n")) || (!op.equals("n")))
        {
        	System.out.println("\nNew Email: ");
            email = input.nextLine();
            student.setEmail(email);
            System.out.println("\nNew Address: ");
            address = input.nextLine();
            student.setAddress(address);
            dao.UpdateStudent(student);
            System.out.println("\n Student Altered successfully ");
        }
        else main(null);
    }
	
	//Class that specify how the Delete function will happen
	private static void DeleteStudent() 
	{
        StudentDAO dao = new StudentDAO();

        Student student = new Student();
        System.out.println("\nID to Delete: ");
        id = input.nextInt();
        student.setID(id);
        dao.DeleteStudent(student);
        System.out.println("\n Student Deleted successfully ");
    }

	//Class that specify how the ArrayList will show the information of the Local DataBase
    private static void ListStudent() 
    {
    	StudentDAO dao = new StudentDAO();
        List<Student> student = dao.getStudentList();
        for (Student student1 : student) 
        {
            System.out.println("\n\tID:" + student1.getID() + "\nName: " + student1.getName() + "\nEmail: " + 
            		student1.getEmail() + "\nAddress: " + student1.getAddress());
            System.out.println("\n-----------------------------------------------------------------------------------");
        }
        System.out.println("\n  -- This is All! --  ");
    }
}
