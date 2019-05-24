package com.hodge.daniel.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hodge.daniel.hibernate.demo.entity.Course;
import com.hodge.daniel.hibernate.demo.entity.Instructor;
import com.hodge.daniel.hibernate.demo.entity.InstructorDetail;
import com.hodge.daniel.hibernate.demo.entity.Review;
import com.hodge.daniel.hibernate.demo.entity.Student;

public class AddCoursesForMaryDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {	
			
			session.beginTransaction();
			
			int studentId = 3;
			Student tempStudent = session.get(Student.class,  studentId);
			
			System.out.println("\nLoaded student: " + tempStudent);
			System.out.println("Courses: " + tempStudent.getCourses());
			
			Course tempCourse1 =new Course("Rubik's Cube - How To Speed Cube");
			Course tempCourse2 =new Course("Atari 2600 - Game Development");
			Course tempCourse3 =new Course("YoYo Flipping - Simple YoYo Tricks");
			
			tempCourse1.addStudent(tempStudent);
			tempCourse2.addStudent(tempStudent);
			tempCourse3.addStudent(tempStudent);
			
			System.out.println("\nSaving the course ...");
			
			session.save(tempCourse1);
			session.save(tempCourse2);
			session.save(tempCourse3);
			
			session.getTransaction().commit();
			
			System.out.println("Done!");
		} finally {
			
			session.close();
			
			factory.close();
		}
	}

}
