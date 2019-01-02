package br.com.ramme.orderstore.entity;

import java.sql.Timestamp;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import java.text.SimpleDateFormat;


public class TimestampAdapter extends XmlAdapter<String, Timestamp> {

  private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  
  public String marshal(Timestamp v) throws Exception {
    return dateFormat.format(v);
  }
  
  public Timestamp unmarshal(String v) throws Exception {
	return new Timestamp(dateFormat.parse(v).getTime());
  }
  
}