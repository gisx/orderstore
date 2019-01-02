package br.com.ramme.orderstore.dao;

import java.util.List;

import br.com.ramme.orderstore.entity.Order;


public interface OrderDAO {

  public List<Order> getAllOrders();
  
  public Order getOrder(String codControlOrder);
  
  public int addOrder(Order pOrder);
  
  public int updateOrder(Order pOrder);
  
  public int deleteOrder(String codControlOrder);
 
}
