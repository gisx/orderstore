package br.com.ramme.orderstore.restful;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import br.com.ramme.orderstore.dao.OrderDAO;
import br.com.ramme.orderstore.dao.OrderDAOImpl;
import br.com.ramme.orderstore.entity.Order;

@Path("/OrderService")
public class OrderService {

  OrderDAO orderDAO = new OrderDAOImpl();
  private static final String SUCCESS_RESULT="<result>success</result>";
  private static final String FAILURE_RESULT="<result>failure</result>";


  @GET
  @Path("/orders")
  @Produces( {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON} )
  public List<Order> getOrders(){
    return orderDAO.getAllOrders();
  }

  
  @GET
  @Path("/orders/{codControlOrder}")
  @Produces( {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON} )
  public Order getOrder(@PathParam("codControlOrder") String codControlOrder){
     return orderDAO.getOrder(codControlOrder);
  }

      
  @POST
  @Path("/orders")
  @Produces( {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON} )
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public String addOrder(@FormParam("codcontrolorder") String codControlOrder, 
	  @FormParam("creationdatetime") String creationDatetime, 
	  @FormParam("nameitem") String nameItem, 
	  @FormParam("valueunititem") double valueUnitItem, 
	  @FormParam("qtitems") int qtItems, 
	  @FormParam("codclient") long codClient,
	  @Context HttpServletResponse servletResponse) throws IOException
  {
	Timestamp ts = Timestamp.valueOf( creationDatetime ) ;
	Order order = new Order(codControlOrder, ts, nameItem, valueUnitItem, qtItems, codClient);
	int result = orderDAO.addOrder(order);
	
    if(result == 1) {
       return SUCCESS_RESULT;
    }
    return FAILURE_RESULT;
  }

  
  @PUT
  @Path("/orders")
  @Produces( {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON} )
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)  
  public String updateOrder(@FormParam("codcontrolorder") String codControlOrder, 
	  @FormParam("creationdatetime") String creationDatetime, 
	  @FormParam("nameitem") String nameItem, 
	  @FormParam("valueunititem") double valueUnitItem, 
	  @FormParam("qtitems") int qtItems, 
	  @FormParam("codclient") long codClient,
	  @Context HttpServletResponse servletResponse) throws IOException
  {
	Timestamp ts = Timestamp.valueOf( creationDatetime ) ;
	Order order = new Order(codControlOrder, ts, nameItem, valueUnitItem, qtItems, codClient);
	int result = orderDAO.updateOrder(order);
    if (result == 1) {
        return SUCCESS_RESULT;
     }
    return FAILURE_RESULT;
  }
  
  
  @DELETE
  @Path("/orders/{codControlOrder}")
  @Produces( {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON} )
  public String deleteOrder(@PathParam("codcontrolorder") String codControlOrder)
  {
	int result = orderDAO.deleteOrder(codControlOrder);
	
    if (result == 1) {
        return SUCCESS_RESULT;
    }
    return FAILURE_RESULT;
  }
  

  @OPTIONS
  @Path("/orders")
  @Produces( {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON} )
  public String getSupportedOperations(){
     return "<operations>GET, PUT, POST, DELETE</operations>";
  }  

}
