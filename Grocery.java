package com.example.sultan.myshoppings;


public class Grocery{
  //private variales
  private int _id;
  private String _groceryname;
  
  
 public Grocery() {
 }
  
  public Grocery(String groceryname) { this. _groceryname = groceryname;}
  
  public int get_id() {return _id;}
  
  public void set_id(int _id) {this._id =_id;}
  
  public String get_groceryname() { return this._groceryname;}
  public void set_groceryname(String _groceryname) { this._groceryname = _groceryname;}
  
}
