package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetails;

public class DeleteDemo {

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
			
			int id=1;			
			
			//start transaction
			System.out.println("Begin session1");
			session.beginTransaction();

			Instructor theinstructor = session.get(Instructor.class, id);
			
//			deleting object
			System.out.println("Saving data");
			
			if(theinstructor!=null) {
//				this will also delete the information of the instructorDetails
				session.delete(theinstructor);
			}
			
//			make commit
			System.out.println("commit session1");
			session.getTransaction().commit();			
			
			System.out.println("Done");
			
			
		} finally {
			factory.close();
		}
	}

}
