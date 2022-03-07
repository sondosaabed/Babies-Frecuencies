import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AVLTree {
	private ANode root;
	
	//constructor
	public AVLTree() {
		
	}

	//check if empty tree
	public boolean isEmpty() {
		return getRoot()==null;
	}
	
	//This method to insert to the tree
	public void insert(Name data, String year, int frecuency ) {
		setRoot(insert(getRoot(),data, year, frecuency));
	}
	
	//This method to find from the tree
	public ANode find(Name x) {
		return(find(getRoot(), x));
	} 
	
	//This method to find maxfrom the tree
	public ANode maxi(ANode root) {
		root =this.getRoot();
		if(root== null )
			return null;  
		int res = root.getFrecuenies().getSize();
        int lres = root.getLeft().getFrecuenies().getSize();
        int rres = root.getRight().getFrecuenies().getSize();
         
        if (lres > res) {
            return root= maxi(root.getLeft()); 
        }else if (rres > res) { 
            return root=maxi(root.getRight());
        }   
        return root;
	}
	 
	public ANode findMax(){
	    ANode result =root;
	    result = findMax(root,result);
	    return result;
	}

	private ANode findMax(ANode node,ANode result){
	    if (node == null){
	        return result;
	    } 
	    if (node.getFrecuenies().getSize() > result.getFrecuenies().getSize() || (node.getFrecuenies().getSize() == result.getFrecuenies().getSize() )){
	        result = node;
	    }
	    result = findMax(node.getLeft(),result);
	    result = findMax(node.getRight(),result);
	    return result;
	}
	//This method to print in order
	public void printInorder() {
		inOrder(getRoot());
	}
	
	//This method to print in preorder
	public void printpreOrder() {
		preOrder(getRoot());
	}
	
	public void totalt( String year) {
		totalt(getRoot(), year);
	}
	
	//Utilities functions 
	private ANode find(ANode root, Name x) { 
		if(root != null)
			if(root.getName().compareTo(x)== 0)
				return root;
			else if (root.getName().compareTo(x)  < 0 )
				return (find(root.getRight(), x));
			else
				return (find(root.getLeft(), x));
		return root;
	}
  
	private ANode insert(ANode root, Name data, String year, int frecuency) {  
		ANode newNode = new ANode(data, year, frecuency);
	     if (root == null) {
	    	 root = newNode;
	    	 return root;
	 	 } else {
	 		 ANode found =this.find(data);
	 		 if(found!= null) {
	 			Frecuency frecuenc=new Frecuency(frecuency, year);
	        	 if(found.getFrecuenies().search(year) == null){  
	 	 			found.getFrecuenies().insertionSorted(frecuenc );
	        	  } else if(found.getFrecuenies().search(year) != null){
	        		   int u=found.getFrecuenies().search(year).getData().getFrecuency();
	        	   found.getFrecuenies().search(year).getData().setFrecuency( u+ frecuency);
	        	  }     
	 		 }else if(found== null) {
	 			int k = root.getName().compareTo(data) ;
	 			if (k > 0) {
		        	root.setLeft(insert(root.getLeft(), data, year, frecuency));
		         } else{
		         	root.setRight(insert(root.getRight(), data, year, frecuency)) ;
	 	         } 
	 		 }   
	        
	         int heightDiff = diffHight(root);
	            if (heightDiff < -1) {
	                if (diffHight(root.getRight()) > 0)  
	                	root.setRight(rotatRight(root.getRight()));
                    return rotatLeft(root); 
	            } else if (heightDiff > 1) {
	                if (diffHight(root.getLeft()) < 0)  
	                	root.setLeft(rotatLeft(root.getLeft()));
                    return rotatRight(root);   
	            }  
	        }
	     root.setHieght(max(root)+ 1);
	     return root;
	}
	
	private int hieght(ANode node) {
        if (node == null) {
            return 0;
        }
        return node.getHieght();
    }
	
	public int diffHight(ANode root){
		if(root==null)
			return 0;
		return hieght(root.getLeft()) -hieght(root.getRight() );
	}
	
	public int max(ANode root) {
		return Math.max(hieght(root.getLeft()) , hieght(root.getRight())); 
	} 
	
	public ANode rotatRight(ANode root) {  
		ANode newRoot  =root.getLeft();
		root.setLeft(newRoot.getRight()); 
		newRoot.setRight(root); 
		root.setHieght(max(root)+ 1);
		newRoot.setHieght(max(newRoot)+ 1);
		return newRoot;
	}
	
	public ANode rotatLeft(ANode root) { 
		ANode newRoot  =root.getRight();
		root.setRight(newRoot.getLeft());
		newRoot.setLeft(root);
		root.setHieght(max(root)+ 1);
		newRoot.setHieght(max(newRoot)+ 1);
		return newRoot;
	}  
	
	private void inOrder(ANode root ) { 
		if(root==null) {
			return;
		}
		else { 
			inOrder(root.getLeft());
			System.out.println(root.getName().toString());
			root.getFrecuenies().traverse();
			inOrder(root.getRight());
		} 
	}
	 
	private void preOrder(ANode root) { 
		if(root==null) {
			return;
		}
		else { 
			System.out.println(root.getName().toString()  );
			root.getFrecuenies().traverse();
			preOrder(root.getLeft()); 
			preOrder(root.getRight());
		} 
	}
	
	private int totalt(ANode root, String year){
		ANode curr = new ANode();
		int total =0;
		if(root==null) {
			return 0;
		}
		else {
			curr=root; 
		    if(curr.getFrecuenies().search(year)!=null) { 
			  total = total +1;
			 }  
		    totalt(root.getLeft(),year);
		    totalt(root.getRight(),year);
		}
		return total;
	}
	
	public int write(String filePath){
		int answer;
		try { 
			BufferedWriter out = new BufferedWriter(new FileWriter(filePath));
			if(root == null) { 
				answer =-1;
			}
		if(root != null) { 
	      root.write(out);
	    }  
	  out.close();
	  answer = 1;
	} catch (IOException e) {
	       System.out.println(e); 
	       answer = 0;
	    } 
		return answer;
	}

	
	public ANode getRoot() {
		return root;
	}

	public void setRoot(ANode root) {
		this.root = root;
	}  
}
