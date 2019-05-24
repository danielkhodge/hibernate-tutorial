package com.hodge.daniel.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hodge.daniel.hibernate.demo.entity.Course;
import com.hodge.daniel.hibernate.demo.entity.Instructor;
import com.hodge.daniel.hibernate.demo.entity.InstructorDetail;
import com.hodge.daniel.hibernate.demo.entity.Review;
import com.hodge.daniel.hibernate.demo.entity.Student;

public class CreateCoursesAndStudentsDemo {

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
			
			Course tempCourse = new Course("pacman - How To Score One Million Points");
			
			System.out.println("\nSaving the course ...");
			session.save(tempCourse);
			System.out.println("Saved the course: " + tempCourse);
			
			Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
			Student tempStudent2 = new Student("Will", "Smith", "will@luv2code.com");
			Student tempStudent3 = new Student("Mary", "Jane", "mary@luv2code.com");
			
			tempCourse.addStudent(tempStudent1);
			tempCourse.addStudent(tempStudent2);
			tempCourse.addStudent(tempStudent3);
			
			System.out.println("Saving the students ...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);
			System.out.println("Students Saved: " + tempCourse.getStudents());
			
			session.getTransaction().commit();
			
			System.out.println("Done!");
		} finally {
			
			session.close();
			
			factory.close();
		}
	}

}
