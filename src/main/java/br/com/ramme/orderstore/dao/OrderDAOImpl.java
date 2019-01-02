package br.com.ramme.orderstore.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.HibernateException; 
import org.hibernate.Transaction;


import br.com.ramme.orderstore.entity.Order;

public class OrderDAOImpl implements OrderDAO {

  private SessionFactory sessionFactory = null;

  /**
   * @return the sessionFactory
   */
  public SessionFactory getSessionFactory() {
	return sessionFactory;
  }

  /**
   * @param sessionFactory the sessionFactory to set
   */
  public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
  }

  public void setup() {
	// configures settings from hibernate.cfg.xml
	final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	try {
	  sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	} catch (Exception ex) {
	  System.err.println("Failed to create sessionFactory object." + ex);
	  StandardServiceRegistryBuilder.destroy(registry);
	  // throw new ExceptionInInitializerError(ex);
	}
  }

  public void exit() {
	sessionFactory.close();
  }
  
  public List<Order> getAllOrders()
  {
	List<Order> orderList = new ArrayList<Order>();	
	//Order order = new Order("00000011", new Timestamp(System.currentTimeMillis()), "teste11", 100.0, 1, 11);
	//orderList.add(order);
	
	Session session = sessionFactory.openSession();
	Transaction tx = null;

    try {
      tx = session.beginTransaction();
      orderList = session.createQuery("FROM Order").list(); 
      for (Iterator iterator = orderList.iterator(); iterator.hasNext();){
         Order order = (Order) iterator.next(); 
         System.out.println("NameItem: " + order.getNameItem());
      }
      tx.commit();
   } catch (HibernateException e) {
      if (tx!=null) tx.rollback();
      e.printStackTrace(); 
   } finally {
      session.close(); 
   }	
	
	
	System.out.println("orders size: " + orderList.size());	
	return orderList;
  }
  
  public Order getOrder(String codControlOrder)
  {
	Order order = null;
	
	if (codControlOrder != null) {
	  //order = new Order("00000012", new Timestamp(System.currentTimeMillis()), "teste12", 200.0, 1, 12);

	  
	  Session session = sessionFactory.openSession();

	  try {
		order = session.get(Order.class, codControlOrder);
	  } catch (HibernateException e) {
	    e.printStackTrace(); 
	  } finally {
	    session.close();
	    System.out.println("NameItem: " + order.getNameItem());
	  }	  
	}  
	
	System.out.println("order to recover: " + codControlOrder);
	return order;	
  }
  
  public int addOrder(Order pOrder)
  {
	int result=0;
	if (pOrder.getCodControlOrder() != null) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		try {
		  tx = session.beginTransaction();
		  session.save(pOrder);
		  tx.commit();
	    } catch (HibernateException e) {
	      if (tx!=null) tx.rollback();
	      e.printStackTrace(); 
	    } finally {
	      session.close();
	      result=1;
	    }	  
	}
	
	System.out.println("saved order: " + pOrder.getCodControlOrder());
	return result;
  }
  
  public int updateOrder(Order pOrder)
  {
	int result=0;
	if (pOrder.getCodControlOrder() != null) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		try {
		  tx = session.beginTransaction();
		  session.update(pOrder);
		  tx.commit();
	    } catch (HibernateException e) {
	      if (tx!=null) tx.rollback();
	      e.printStackTrace(); 
	    } finally {
	      session.close();
	      result=1;
	    }  
	}
	
	System.out.println("updated order: " + pOrder.getCodControlOrder());
	return result;
  }
  
  public int deleteOrder(String codControlOrder)
  {
	int result=0;
	if (codControlOrder != null) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Order order = new Order();
		order.setCodControlOrder(codControlOrder);
		
		try {
		  tx = session.beginTransaction();
		  session.delete(order);
		  tx.commit();
	    } catch (HibernateException e) {
	      if (tx!=null) tx.rollback();
	      e.printStackTrace(); 
	    } finally {
	      session.close();
	      result=1;
	    }  
	  
	}
	
	System.out.println("deleted order: " + codControlOrder);
	return result;
  }

}
