package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetails;

public class CreateDemo {

	public static void main(String[] args) {

//		create session factory / create only once in whole app 
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetails.class)
								.buildSessionFactory();
		
//		create session
		Session session = factory.getCurrentSession(); 

		try {
			
			Instructor theinstructor = new Instructor("babu", "saifi", "babu@gmail.com");
			
			InstructorDetails theinstructorDetails= new InstructorDetails("www.babu.com", "father");
			
			theinstructor.setInstructorDetails(theinstructorDetails);
						
//			start transaction
			System.out.println("Begin session1");
			session.beginTransaction();
			
//			save object
			System.out.println("Saving data");
//			Note: this will also save the information of InstructorDetails
			session.save(theinstructor);
			
//			make commit
			System.out.println("commit session1");
			session.getTransaction().commit();			
			
			System.out.println("Done");
			
			
		} finally {
			factory.close();
		}
	}

}
