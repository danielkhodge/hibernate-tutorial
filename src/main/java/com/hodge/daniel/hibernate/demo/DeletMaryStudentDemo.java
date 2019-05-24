package com.hodge.daniel.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hodge.daniel.hibernate.demo.entity.Course;
import com.hodge.daniel.hibernate.demo.entity.Instructor;
import com.hodge.daniel.hibernate.demo.entity.InstructorDetail;
import com.hodge.daniel.hibernate.demo.entity.Review;
import com.hodge.daniel.hibernate.demo.entity.Student;

public class DeletMaryStudentDemo {

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
			
			System.out.println("\nDeleting student: " + tempStudent);
			session.delete(tempStudent);
			
			session.getTransaction().commit();
			
			System.out.println("Done!");
		} finally {
			
			session.close();
			
			factory.close();
		}
	}

}
