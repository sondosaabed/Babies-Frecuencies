import java.io.BufferedWriter;
import java.io.IOException;

public class ANode {
	private ANode left;
	private ANode right;
	private Name name; 
	private LinkedList frecuenies= new LinkedList();
	private int hieght;
	
	//Non argument constructor
	public ANode() {
		
	}
	
	//Constructor
	public ANode(Name data,String year, int frecuency) {
		this.setHieght(1);
		this.name = data; 
		Frecuency frecuenc= new Frecuency(frecuency, year);
		frecuenies.insertionSorted(frecuenc);
	}
	
	//Getters and setters
	public ANode getLeft() {
		return left;
	}
	public void setLeft(ANode left) {
		this.left = left;
	}
	public ANode getRight() {
		return right;
	}
	public void setRight(ANode right) {
		this.right = right;
	}
	public Name getName() {
		return name;
	}
	public void setName(Name name) {
		this.name = name;
	} 

	public int getHieght() {
		return hieght;
	}

	public void setHieght(int hieght) {
		this.hieght = hieght;
	} 

	public LinkedList getFrecuenies() {
		return frecuenies;
	}

	public void setFrecuenies(LinkedList frecuenies) {
		this.frecuenies = frecuenies;
	}

	public void write(BufferedWriter out){
	    if (this.getLeft() != null) this.getLeft().write(out);
	    try {
			out.write(this.getName().getName()+","+this.getName().getGender() +","
					  +this.getFrecuenies().total()+"\n");
		} catch (IOException e) { 
			e.printStackTrace();
		}
	    if (this.getRight() != null) 
	    	this.getRight().write(out);
	}
	
	@Override
	public String toString() {
		System.out.println("ANode [ name=" + name + ", frecuenies=" + "]"); 
		 frecuenies.traverse();
		 return "";
	} 
}
