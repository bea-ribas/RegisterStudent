package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Student;

//Class where all the SQL query's is defined and the object Student
//is modified, save, alter or delete
public class StudentDAO 
{
	private Connection connection; 
	
	public StudentDAO() 
	{
		new DataBaseConnection();
		this.connection = DataBaseConnection.getConnection();
	}
	
	//method to insert new Objects student in the Local DataBase
	public void InsertStudent(Student student) 
	{
		String sql = "INSERT INTO Student (name,email,address) values (?,?,?)";
		try
		{ 
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, student.getName());
			stmt.setString(2, student.getEmail());
			stmt.setString(3, student.getAddress());
			
			stmt.execute();

			stmt.close();
			} 
		catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	//method that delete Objects student that are in the Local DataBase
	public void	DeleteStudent(Student student) 
	{
		try
		{
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM Student WHERE ID=?");
			stmt.setLong(1, student.getID());
			stmt.execute();
			stmt.close();
			} 
		catch (SQLException e) 
		{
			throw new RuntimeException(e);
			}
	}
	
	//method that helps the user to decide and see 'who' he is going to update
	// the Object student that are in the Local DataBase
	public String ConfirmUpdateStudent(int id) 
	{
		String name = null;
		String sql = "SELECT Name FROM Student WHERE ID = " + id;
		try
		{
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) 
			{ 
			name = rs.getString("name");
			}
			rs.close();
			stmt.close();
		}
		catch (SQLException e) 
		{ 
			throw new RuntimeException(e);
			}
		return name;
	}
	
	//method to update the Object selected by the user
	public void UpdateStudent(Student student)
	{
		String sql = "UPDATE Student SET Email=?, Address=? WHERE ID=? ";
		try
		{
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, student.getEmail());
			stmt.setString(2, student.getAddress());
			stmt.setInt(3, student.getID());
			//stmt.setString(4, student.getName());
			stmt.execute();
			stmt.close();
			} 
		catch (SQLException e) 
		{ 
			throw new RuntimeException(e);
			}
		
	}
	
	//method that put in the Object student inside an ArrayList, that will be able to see for the end user
	public List<Student> getStudentList() 
	{
		List<Student> students = new ArrayList<Student>();
		PreparedStatement stmt;
		try
		{
			stmt = this.connection.prepareStatement("SELECT * FROM Student");
			ResultSet rs = stmt.executeQuery(); 
			while (rs.next()) 
			{ 
				Student student = new Student();
				student.setID(rs.getInt("ID"));
				student.setName(rs.getString("name"));
				student.setEmail(rs.getString("email"));
				student.setAddress(rs.getString("address"));
				students.add(student);
				
				}
			rs.close();
			stmt.close();
			} 
		catch (SQLException e) 
		{
			throw new RuntimeException(e);
			}
		return students;
	}
}
