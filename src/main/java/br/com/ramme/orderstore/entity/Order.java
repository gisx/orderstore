package br.com.ramme.orderstore.entity;


import java.text.SimpleDateFormat;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Entity
//@Table(name = "torder", uniqueConstraints = {
//	@UniqueConstraint(columnNames = "codcontrolorder")
//	})
@Table(name = "torder")
@XmlRootElement(name = "order")
public class Order {

  @Basic
  @Id
  @Column(name = "codcontrolorder", nullable=false, unique=true)
  private String codControlOrder;
  
  @Basic
  //@XmlJavaTypeAdapter(TimestampAdapter.class)
  @Column(name = "creationdatetime")
  private java.sql.Timestamp creationDatetime;
  
  @Basic
  @Column(name = "nameitem")
  private String nameItem;
  
  @Basic
  @Column(name = "valueunititem")
  private Double valueUnitItem;
  
  @Basic
  @Column(name = "qtitems")
  private Integer qtItems;
  
  @Basic
  @Column(name = "codclient")
  private Long codClient;

  
  public Order()
  {
  }

  public Order(String codControlOrder, String nameItem, long codClient)
  {
	this.codControlOrder = codControlOrder;
	this.nameItem = nameItem;
	this.codClient = new Long(codClient);
  }

  public Order(String codControlOrder, java.sql.Timestamp creationDatetime, String nameItem, double valueUnitItem, int qtItems, long codClient)
  {
	this.codControlOrder = codControlOrder;
	this.creationDatetime = creationDatetime;
	this.nameItem = nameItem;
	this.valueUnitItem = new Double(valueUnitItem);
	this.qtItems = new Integer(qtItems);
	this.codClient = new Long(codClient);
  }
  
  
  // not persist field in database
  @Transient
  private Double valueTotalItems = this.getValueTotalItems();
  
  @Transient
  public Double getValueTotalItems() {
	Double result = new Double(10000000.0);
	if ((this.qtItems != null) && (this.valueUnitItem != null)) {
	  if (this.valueUnitItem < 6) {
		result = (new Double( (this.qtItems.intValue() * this.valueUnitItem.doubleValue()) ));
	  } else if ((this.valueUnitItem > 5) && (this.valueUnitItem < 10)) {
		result = (new Double( (this.qtItems.intValue() * this.valueUnitItem.doubleValue() * 0.95) ));
	  } else if (this.valueUnitItem >= 10) {
		result = (new Double( (this.qtItems.intValue() * this.valueUnitItem.doubleValue() * 0.90) ));
	  }
	} 
	return result;
  }
  

  public String getCodControlOrder() {
    return codControlOrder;
  }

  @XmlElement  
  public void setCodControlOrder(String codControlOrder) {
    this.codControlOrder = codControlOrder;
  }


  public java.sql.Timestamp getCreationDatetime() {
  //public String getCreationDatetime() {
	//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//return dateFormat.format(this.creationDatetime);	
    return creationDatetime;
  }

  @XmlElement
  public void setCreationDatetime(java.sql.Timestamp creationDatetime) {
    this.creationDatetime = creationDatetime;
  }

  public String getNameItem() {
    return nameItem;
  }

  @XmlElement
  public void setNameItem(String nameItem) {
    this.nameItem = nameItem;
  }

  public Double getValueUnitItem() {
    return valueUnitItem;
  }

  @XmlElement
  public void setValueUnitItem(Double valueUnitItem) {
    this.valueUnitItem = valueUnitItem;
  }

  public Integer getQtItems() {
    return qtItems;
  }

  @XmlElement
  public void setQtItems(Integer qtItems) {
    this.qtItems = qtItems;
  }

  public Long getCodClient() {
    return codClient;
  }

  @XmlElement
  public void setCodClient(Long codClient) {
    this.codClient = codClient;
  }

  @Override
  public boolean equals(Object object){
     if(object == null){
        return false;
     }else if(!(object instanceof Order)){
        return false;
     }else {
        Order order = (Order)object;
        if(codControlOrder == order.getCodControlOrder()
           && creationDatetime.equals(order.getCreationDatetime())
           && nameItem.equals(order.getNameItem())
           && valueUnitItem.equals(order.getValueUnitItem())
           && qtItems.equals(order.getQtItems())
           && codClient.equals(order.getCodClient())
        ){
           return true;
        }			
     }
     return false;
  }	  

}
