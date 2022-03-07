
public class LNode {
	LNode next;
	Frecuency data;
	
	//non arguments constructor
	public LNode() {
		
	}
	
	//Constructor
	public LNode(Frecuency data) { 
		this.data=data;
		this.next = null; 
	}
	
	//Getters and setters
	public LNode getNext() {
		return next;
	}

	public void setNext(LNode next) {
		this.next = next;
	}

	public Frecuency getData() {
		return data;
	}

	public void setData(Frecuency data) {
		this.data = data;
	}

	//To string
		@Override
		public String toString() {
			return " data=" + data + "]\n";
		}  
}
