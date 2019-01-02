package br.com.ramme.orderstore;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Test;

import br.com.ramme.orderstore.dao.OrderDAO;
import br.com.ramme.orderstore.dao.OrderDAOImpl;
import br.com.ramme.orderstore.entity.Order;

public class OrderDAOTester {

  public int addOrder()
  {
	OrderDAOImpl orderDAO = new OrderDAOImpl();
	orderDAO.setup();
	
	Order order = new Order("00000011", new Timestamp(System.currentTimeMillis()), "teste11", 200.0, 2, 11);
	int result = orderDAO.addOrder(order);
	
	orderDAO.exit();
	return result;
  }

  public int getOrder()
  {
	OrderDAOImpl orderDAO = new OrderDAOImpl();
	orderDAO.setup();
	
	Order order = orderDAO.getOrder("00000011");
	int result = (order.getCodClient() != null) ? 1 : 0;
	
	orderDAO.exit();
	return result;
  }
  
  public int updateOrder()
  {
	OrderDAOImpl orderDAO = new OrderDAOImpl();
	orderDAO.setup();
	
	Order orderUpdated = new Order("00000011", new Timestamp(System.currentTimeMillis()), "teste11update", 200.0, 2, 11);
	int result = orderDAO.updateOrder(orderUpdated);
	
	orderDAO.exit();
	return result;
  }
  
  public int deleteOrder()
  {
	OrderDAOImpl orderDAO = new OrderDAOImpl();
	orderDAO.setup();
	
	int result = orderDAO.deleteOrder("00000011");
	
	orderDAO.exit();
	return result;
  }

  @Test
  public void getAllOrders()
  {
	OrderDAOImpl orderDAO = new OrderDAOImpl();
	orderDAO.setup();
	List<Order> orderList = orderDAO.getAllOrders();
	orderDAO.exit();
	assertEquals(10, orderList.size());	
  }
  
  @Test
  public void crudTest()
  {
	int result = 0;
	result = addOrder();
	result += getOrder();
	result += updateOrder();
	result += deleteOrder();
	assertEquals(4, result);	
  }
  
  public static void main(String[] args)
  {
	OrderDAOTester test = new OrderDAOTester();
	//test.addOrder();
	//test.getOrder();
	//test.updateOrder();
	//test.deleteOrder();
  }  
  
}
