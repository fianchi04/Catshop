package catalogue;

import java.io.Serializable;
import java.util.Collections;//needed for collections.sort


/**
 * The class BetterBasket aims to improve the user experience surrounding each instance of a basket:
 * 	- The basket items are sorted into ascending order, on their product key
 * 	- Multiple items of the same type are grouped into one request for a plural of the same product, rather than listed individually
 * 
 * @author  Anna Wilde 
 * @version 2.2
 */
public class BetterBasket extends Basket implements Serializable
{
  private static final long serialVersionUID = 1L;

  @Override
  public boolean add (Product pr)
  {
	  //group multiple items of the same type
	  for (int i=0; i<this.size(); i++){
		  if(this.get(i).getProductNum().equals(pr.getProductNum())){
			  this.get(i).setQuantity(this.get(i).getQuantity() + pr.getQuantity());
			  return true; //if the item was already there then exit the add method - 
			  //continuing from here would cause more raw products (!multiples)
		  }
	  }
	  //add the pr after multiple check (if added already it would have exited)
	  super.add(pr);
	  //successful sort
	  Collections.sort(this, BetterBasket::Sort); //betterbasket implies class, sort implies method
	  return true;
	  
  }	 
 
  public static int Sort (Product i, Product j) { //the method is called by Collections.sort. Passes two parameters (product i and i+1), these are compared/switched and then returned 
	  return i.getProductNum().compareTo(j.getProductNum());//compare to is passed as an int (+/-ve determined by results of comparison)
  }
  
  //improved method for removing items
  @Override
  public boolean remove (Product pr)
  {
	  //check all items in the basket (i) to see if one matches the product code to remove
	  for (int i=0; i<this.size(); i++){
		  if(this.get(i).getProductNum().equals(pr.getProductNum())){ //if a match is found:
			  //subtract 1 from the product quantity
			  this.get(i).setQuantity(this.get(i).getQuantity() - pr.getQuantity());
			  //check to see if item needs to be removed completely from list bc 0 or less quantity
			  if (this.get(i).getQuantity() <= 0){
				  super.remove(i);
			  }  
			  return true; //exit method
		  } 	  
	  }
	    
	  return false;
  }
  
  @Override
  public boolean checkList (Product pr)
  {
	  for (int i =0; i<this.size(); i++){
		  if(this.get(i).getProductNum().equals(pr.getProductNum())){
			  return true;
			  }
	  } 
	  return false;
  }
  
  
}
	  
  

