package com.infotech.client;

import java.util.Optional;

import org.hibernate.Session;

import com.infotech.entities.Book;
import com.infotech.util.HibernateUtil;

public class ReadEntityClientTest {

	public static void main(String[] args) {
		
		//loadBookBySimpleNaturalId();
		//loadBookByNaturalId();
		loadBookByOptionalJava8NaturalId();
	}

	private static void loadBookByOptionalJava8NaturalId() {

		try(Session session = HibernateUtil.getSessionFactory().openSession() ) {
			String  naturalId = "900-9730228524";
			Optional<Book> optional = session.byNaturalId(Book.class).using("isbn", naturalId).loadOptional();
			if(optional.isPresent()){
				Book book = optional.get();
				System.out.println(book.getTitle()+"\t"+book.getAuthor().getName());
			}else{
				System.out.println("Information not found!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void loadBookByNaturalId() {
		try(Session session = HibernateUtil.getSessionFactory().openSession() ) {
			String  naturalId = "900-9730228524";
			Book book = session.byNaturalId(Book.class).using("isbn", naturalId).load();
			if(book != null){
				System.out.println(book.getTitle()+"\t"+book.getAuthor().getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void loadBookBySimpleNaturalId() {

		try(Session session = HibernateUtil.getSessionFactory().openSession() ) {
			String  naturalId = "900-9730228524";
			
			Book book = session.bySimpleNaturalId(Book.class).getReference(naturalId);
			if(book != null){
				System.out.println(book.getTitle()+"\t"+book.getAuthor().getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}
