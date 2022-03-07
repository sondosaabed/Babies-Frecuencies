import java.text.ParseException;
import java.text.SimpleDateFormat; 

public class LinkedList { 
    public LNode firstNode = null;  
    public LNode LastNode = null;  
    public int size=0;
    SimpleDateFormat f = new SimpleDateFormat("yyy");
    //non arguments constructor 
	public LinkedList() {
		
	}
	
	//this method is to know whether the linked list is empty or not
	public boolean isEmpty() { 
		if(firstNode==null) {
			return true;
		}
		else 
			return false;
	}  
	
	/*the way I chose to add to my build linkedList is sorted insertion 
	 based on the year */
	public void insertionSorted(Frecuency frecuency) { 
			LNode newNode = new LNode(frecuency);
			if(this.isEmpty()) {
				//case is empty
				firstNode=newNode;
				LastNode = firstNode;
				size++;
			}
			else if(frecuency.getYear().compareTo(firstNode.getData().getYear()) >0){
				//case greater than grade
				newNode.setNext(firstNode);
				firstNode=newNode;
				size++;
			}
			else {
				//case less than grade
				LNode pre=firstNode;
				LNode curr=firstNode.getNext();
				while(curr!=null && frecuency.getYear().compareTo(curr.getData().getYear() )<0) {
					pre=curr;
					curr=curr.getNext();
				}
				newNode.setNext(curr);
				pre.setNext(newNode);
				size++;
			}
		} 
	
	public LNode search(String x) {
		 LNode current = firstNode;  
		 java.util.Date y;
		try {
			y = f.parse(x);
			if (current  == null) {
				 return current ;
			 } 
		        while (current != null){
		        	if (current.getData().getYear().equals(y) )
		            	return current;     
		            current = current.next;
		        } 
		  return current;
		} catch (ParseException e) { 
			e.printStackTrace(); 
		}
		return current;   
	 }
	 
	//this method is created to traverse the linked list and print it's total of frecuencies 
    public int total() { 
    	int total =0;
    	LNode current = firstNode;  
        if(this.isEmpty()) {  
            System.out.println("Is empty!"); 
        }  
        while(current!= null) {  
            total = total + current.data.getFrecuency();  
            current = current.next;  
        }     
        return total;
    }  
    
    //this method is created to traverse the linked list and print it 
    public void traverse() { 
    	LNode current = firstNode;  
        if(this.isEmpty()) {  
            System.out.println("Is empty!");   
        }  
        while(current!= null) {   
            System.out.print(current.data.toString() + "\n");  
            current = current.next;  
        }     
    }  
    
    //getters and setters
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public LNode getFirstNode() {
		return firstNode;
	}
	public void setFirstNode(LNode firstNode) {
		this.firstNode = firstNode;
	}
	public LNode getLastNode() {
		return LastNode;
	}
	public void setLastNode(LNode lastNode) {
		LastNode = lastNode;
	} 	 
}
