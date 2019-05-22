package com.hodge.daniel.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hodge.daniel.hibernate.demo.entity.Instructor;
import com.hodge.daniel.hibernate.demo.entity.InstructorDetail;
import com.hodge.daniel.hibernate.demo.entity.Student;


public class CreateDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {	
//			Instructor tempInstructor = 
//					new Instructor("Chad", "Darby", "darby@luv2code.com");
//			
//			InstructorDetail tempInstructorDetail = 
//					new InstructorDetail(
//							"http://www.luv2code.com/youtube", 
//							"Luv 2 code!!!");
			
			Instructor tempInstructor = 
					new Instructor("Madhu", "Patel", "madhu@luv2code.com");
			
			InstructorDetail tempInstructorDetail = 
					new InstructorDetail(
							"http://www.youtube.com", 
							"Guitar");
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			System.out.println("Saving instructor: " + tempInstructor);
			session.beginTransaction();
						
			session.save(tempInstructor);
			
			session.getTransaction().commit();
			
			System.out.println("Done!");
		} finally {
			factory.close();
		}
	}

}
