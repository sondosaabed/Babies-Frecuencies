import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException; 
import java.text.DecimalFormat; 
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos; 
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane; 
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser; 
import javafx.stage.Stage; 
import java.util.Scanner;

public class Main extends Application{
	public static DirectoryChooser directoryChooser = new DirectoryChooser();
	public static File folder; 
	public static String directoryi; 
	public static AVLTree tree= new AVLTree(); 
	
	public static void main(String[] args) { 
		launch(args) ;
	} 
	
	@Override
	public void start(Stage stage) throws Exception { 
		//new gridPane
		GridPane pane = new GridPane();
		//set alignment, padding, gaps and background color
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(100,130,100,130));
		pane.setHgap(10.5);
		pane.setVgap(10.5); 
					
		//make fill for my backgound
		BackgroundFill fill = new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY); 
		Background background = new Background(fill);  
		
		BackgroundFill fill1 = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY); 
		Background background1 = new Background(fill1);  
		pane.setBackground(background1);
		
		Label directory = new Label("Hello, please chose your folder:");
		directory.setFont(Font.font(16));  
		directory.setTextFill(Color.BROWN );
		pane.add(directory , 0, 0);
		
		TextField directory1 = new TextField("Browse Your Folder");
		directory1.setFont(Font.font(14));
		directory1.setPrefWidth(400); 
		pane.add(directory1 , 0, 1);
		
		Label process = new Label("Your process...");
		process.setFont(Font.font(12));  
		process.setTextFill(Color.RED );
		pane.add(process , 0, 5);
		
		Button browse = new Button("Browse"); 
		browse.setFont(Font.font(14)); 
		browse.setBackground(background);
		browse.setPrefSize(100, 40);
		pane.add(browse , 2, 1);
		
		browse.setOnAction(e -> {   
            folder = directoryChooser.showDialog(stage); 
            if(folder.listFiles().length==0) {
            	process.setText("You have not selected\n a folder of files, Please do");
            }
            else {  
            	try { 
            		directoryi=folder.getCanonicalPath();
            		process.setText("Ready to run");
					directory1.setText(folder.getCanonicalPath() );
				} catch (IOException e1) { 
					e1.printStackTrace();
				} 
            	
		Button run = new Button("Run");
		run.setFont(Font.font(14)); 
		run.setTextFill(Color.DARKGREEN);
		run.setPrefSize(100, 50);
		run.setBackground(background );
		pane.add(run, 1, 5);
		run.setOnAction(a-> {
			try {
				readFiles(folder.getCanonicalPath());
			} catch (IOException e1) { 
				e1.printStackTrace();
			}
			
			Stage stage1 = new Stage();
			GridPane pane1 = new GridPane();
			pane1.setBackground(background1); 
            pane1.setAlignment(Pos.CENTER); 
    		pane1.setHgap(15.5);
    		pane1.setVgap(15.5);  
    		
    		Button search = new Button();
    		search.setTextFill(Color.BROWN);
    		search.setFont(Font.font(14));
    		search.setPrefSize(200, 200); 
    		search.setGraphic(new ImageView(new Image("Search.png",200,200,false,true)));
    		search.setBackground(background );
    		search.setOnAction(k-> {
    			Stage stage2 = new Stage();
    			GridPane pane2 = new GridPane();
    			pane2.setPadding( new Insets( 100,130, 100,130 ));
    			pane2.setBackground(background1); 
                pane2.setAlignment(Pos.CENTER); 
        		pane2.setHgap(15.5);
        		pane2.setVgap(15.5);  
		    	  
		    	//Create number of shares label
		    	Label name= new Label(("       Name of the baby:"));
		    	name.setFont(Font.font(20));
		    	pane2.add(name,0,0); 
		    	  
		    	  //Create company name label
		    	Label gender= new Label(("       Gender of the baby:"));
		    	gender.setFont(Font.font(20));
		    	pane2.add(gender, 0, 1); 
		    	  
		    	  //Create number of shares TextField
		    	TextField name1 = new TextField();
		    	name1.setPrefSize(150, 30);
		    	pane2.add(name1, 1, 0); 
		    	  
		    	  //Create company name comboBox
		    	ComboBox<String> comboBox = new ComboBox<String>();   
		    	comboBox.getItems().add("Female") ;
		    	comboBox.getItems().add("Male"); 
		    	comboBox.setBackground(background);
		    	comboBox.setPrefSize(150, 30);
		    	pane2.add(comboBox, 1, 1);  
		    	
		    	//create button to buy shares
		    	  Button btSearch = new Button("Search");
		    	  btSearch.setTextFill(Color.RED);
		    	  btSearch.setFont(Font.font(16));
		    	  GridPane.setHalignment( btSearch, HPos.CENTER);
		    	  btSearch.setBackground(background);
		    	  pane2.add(btSearch, 0, 4); 
		    	   
		    	  btSearch.setOnAction(d-> {
		    		  if(comboBox.getSelectionModel().getSelectedItem()== null ||name1.getText()== null) {
		    			  Label answer= new Label("You must enter a value");
					      answer.setFont(Font.font(20));
					    	pane2.add(answer,0,3);   
		    		  } else {
		    		  Stage stage3 = new Stage();
		    			GridPane pane3 = new GridPane();
		    			pane3.setPadding( new Insets( 100,130, 100,130 ));
		    			pane3.setBackground(background1); 
		                pane3.setAlignment(Pos.CENTER); 
		        		pane3.setHgap(15.5);
		        		pane3.setVgap(15.5);  
				    	  
				    	//Create number of shares label
				    	Label answer= new Label( );
				    	answer.setFont(Font.font(20));
				    	pane3.add(answer,0,0);  
				    	
		    		  char g;
		    		  if(comboBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("Female")) {
		    			  g ='F';
		    		  }
		    		  else {
		    			  g='M';
		    		  }
		    		  
		    		String  nameS= name1.getText()+","+ g;
		    		ANode found=search(nameS); 
		        	
		        	  //create button to cancel 
			    	  Button btCancel = new Button("Ok");
			    	  btCancel.setTextFill(Color.RED);
			    	  btCancel.setFont(Font.font(16));
			    	  btCancel.setBackground(background);
			    	  pane3.add(btCancel, 2, 0); 
			    	  
			    	  btCancel.setOnAction(l-> {
		        			stage3.close();
		        		}); 
		    	  	   
		        	  if(found== null)    
			    	    answer.setText("Sorry, baby not found"); 
		        	  else {
		        		  answer.setText("Found, frecuencies over the years:"); 
		        	      TextArea area = new TextArea();  
		        	      area.setPrefColumnCount(15);
		        	      area.setPrefHeight(300);
		        	      area.setPrefWidth(300); 
		    		   LNode curr= found.getFrecuenies().getFirstNode();
		    		   String text="";
		    		   while(curr!=null) { 
		    		       text = text +curr.getData().f.format(curr.getData().getYear()) +" --> "+ curr.getData().getFrecuency() +" \n";
		    		      curr= curr.getNext();
		    		   }
		    		   area.setText(text );
		    		  pane3.add(area, 0, 1); 
		        	  }  
		        	  
		        	  Scene scene3 = new Scene(pane3); 
		                stage3.setTitle("Answer");
		                stage3.setScene(scene3);  
		                stage3.show();
		    		  }
		    		  });
		    	  
		    	  //create button to cancel 
		    	  Button btCancel = new Button("Cancel");
		    	  btCancel.setTextFill(Color.RED);
		    	  btCancel.setFont(Font.font(16));
		    	  btCancel.setBackground(background);
		    	  pane2.add(btCancel, 1, 4);  
        		 btCancel.setOnAction(d-> {
        			stage2.close();
        		});
		    	Scene scene = new Scene(pane2); 
        		
                stage2.setTitle("Search");
                stage2.setScene(scene);  
                stage2.show(); 
		    	  });
    		
    		Button average = new Button( );
    		average.setTextFill(Color.BROWN);
    		average.setFont(Font.font(14));
    		average.setPrefSize(200, 200); 
    		average.setGraphic(new ImageView(new Image("average.png",200,200,false,true)));
    		average.setBackground(background );
    		average.setOnAction(k-> {
    			Stage stage2 = new Stage();
    			GridPane pane2 = new GridPane();
    			pane2.setPadding( new Insets( 100,130, 100,130 ));
    			pane2.setBackground(background1); 
                pane2.setAlignment(Pos.CENTER); 
        		pane2.setHgap(15.5);
        		pane2.setVgap(15.5);  
		    	  
		    	  //Create number of shares label
		    	Label name= new Label(("       Name of the baby:"));
		    	name.setFont(Font.font(20));
		    	pane2.add(name,0,0); 
		    	  
		    	  //Create company name label
		    	Label gender= new Label(("       Gender of the baby:"));
		    	gender.setFont(Font.font(20));
		    	pane2.add(gender, 0, 1); 
		    	  
		    	  //Create number of shares TextField
		    	TextField name1 = new TextField();
		    	name1.setPrefSize(150, 30);
		    	pane2.add(name1, 1, 0); 
		    	  
		    	  //Create company name comboBox
		    	ComboBox<String> comboBox = new ComboBox<String>();   
		    	comboBox.getItems().add("Female" ) ;
		    	comboBox.getItems().add("Male"); 
		    	comboBox.setBackground(background);
		    	comboBox.setPrefSize(150, 30);
		    	pane2.add(comboBox, 1, 1);  
		    	
		    	//create button to buy shares
		    	  Button btAvg = new Button("Calculate average");
		    	  btAvg.setTextFill(Color.RED);
		    	  btAvg.setFont(Font.font(16));
		    	  GridPane.setHalignment( btAvg, HPos.CENTER);
		    	 btAvg.setBackground(background);
		    	  pane2.add(btAvg, 0, 4); 
		    	  btAvg.setOnAction(d-> {
		    		  if(comboBox.getSelectionModel().getSelectedItem()== null ||name1.getText()== null) {
		    			  Label answer= new Label("You must enter a value");
					      answer.setFont(Font.font(20));
					    	pane2.add(answer,0,3);   
		    		  } else {
		    			Stage stage3 = new Stage();
		    			GridPane pane3 = new GridPane();
		    			pane3.setPadding( new Insets( 100,130, 100,130 ));
		    			pane3.setBackground(background1); 
		                pane3.setAlignment(Pos.CENTER); 
		        		pane3.setHgap(15.5);
		        		pane3.setVgap(15.5); 
		        	  	Scene scene3 = new Scene(pane3); 
		        	  	char g;
			    		  if(comboBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("Female")) {
			    			  g ='F';
			    		  }
			    		  else {
			    			  g='M';
			    		  }
		        	  	Label answer  = new  Label("Answer"); 
		        	  	answer.setFont(Font.font(20));
				    	pane3.add(answer,1,0); 
				    	String  nameS= name1.getText()+","+ g;
			    		ANode found=search(nameS);
			    	    if(found== null)    
			    	    	answer.setText("Sorry, baby not found");
			    	    else { 
			    	    	answer.setText("Average is: "+new DecimalFormat("##.###").format(average(nameS)));
			    	    }
			    	    
				    	Button ok = new Button("Ok");
				    	  ok.setTextFill(Color.RED);
				    	  ok.setFont(Font.font(16));
				    	  GridPane.setHalignment( ok, HPos.CENTER);
				    	 ok.setBackground(background);
				    	  pane3.add(ok, 1, 1); 
				    	  ok.setOnAction(o-> {
			        			stage3.close();
			        		}); 
		                stage3.setTitle("Answer");
		                stage3.setScene(scene3);  
		                stage3.show();
		    	  } });
		    	  
		    	  //create button to cancel 
		    	  Button btCancel = new Button("Cancel");
		    	  btCancel.setTextFill(Color.RED);
		    	  btCancel.setFont(Font.font(16));
		    	  btCancel.setBackground(background);
		    	  pane2.add(btCancel, 1, 4);  
        		btCancel.setOnAction(d-> {
        			stage2.close();
        		});
		    	  Scene scene = new Scene(pane2); 
        		
                stage2.setTitle("Calcuate Average ");
                stage2.setScene(scene);  
                stage2.show();
    		});
    		
    		Button max = new Button( ); 
    		max.setTextFill(Color.BROWN);
    		max.setFont(Font.font(14));    
    		max.setPrefSize(200, 200);
    		max.setGraphic(new ImageView(new Image("max.png",200,200,false,true)));
    		max.setBackground(background );
    		max.setOnAction(k-> {
    			Stage stage2 = new Stage();
    			GridPane pane2 = new GridPane();
    			pane2.setPadding( new Insets( 100,130, 100,130 ));
    			pane2.setBackground(background1); 
                pane2.setAlignment(Pos.CENTER); 
        		pane2.setHgap(15.5);
        		pane2.setVgap(15.5);  
		    	  
		    	  //Create number of shares label
		    	Label name= new Label(("       Name of the baby with the most frecuencies all over the years:"));
		    	name.setFont(Font.font(20));
		    	pane2.add(name,0,0); 
		    	  
		    	  //Create answer name label
		    	Label answer= new Label();
		    	answer.setFont(Font.font(20));  
		    	GridPane.setHalignment( answer, HPos.CENTER);
		    	pane2.add(answer, 0, 1);  
		    	String gendes = "";
		    	if (max().getName().getGender()== 'F')
		    		gendes = "Female";
		    	else
		    		gendes = "Male";
		    	answer.setText(max().getName().getName()+" --> "+gendes);
		    	  //create button to cancel 
		    	Button ok = new Button("Ok");
		    	ok.setTextFill(Color.RED);
		    	ok.setFont(Font.font(16));
		    	ok.setBackground(background);
		    	GridPane.setHalignment( ok, HPos.RIGHT);
		    	pane2.add(ok, 0, 2);  
        		ok.setOnAction(d-> {
        			stage2.close();
        		});
		    	  Scene scene = new Scene(pane2); 
        		
                stage2.setTitle("Find Most Popular");
                stage2.setScene(scene);  
                stage2.show();
    		});
    		
    		Button total = new Button( );
    		total.setTextFill(Color.BROWN); 
    		total.setFont(Font.font(14));  
    		total.setPrefSize(200, 200);
    		total.setGraphic(new ImageView(new Image("total.png",200,200,false,true)));
    		total.setBackground(background );
    		total.setOnAction(k-> {
    			Stage stage2 = new Stage();
    			GridPane pane2 = new GridPane();
    			pane2.setPadding( new Insets( 100,130, 100,130 ));
    			pane2.setBackground(background1); 
                pane2.setAlignment(Pos.CENTER); 
        		pane2.setHgap(15.5);
        		pane2.setVgap(15.5);  
		    	  
		    	  //Create year label
		    	Label years= new Label(("       Enter year to find the total number of babies:"));
		    	years.setFont(Font.font(20));
		    	pane2.add(years,0,0);  
		    	
		    	  //Create year TextField
		    	TextField year1 = new TextField();
		    	year1.setPrefSize(50, 30);
		    	pane2.add(year1, 1,0); 
		    	  
		    	//create button to caculate total
		    	  Button btTotal = new Button("Calculate total");
		    	  btTotal.setTextFill(Color.RED);
		    	  btTotal.setFont(Font.font(16));
		    	  GridPane.setHalignment( btTotal, HPos.CENTER);
		    	  btTotal.setBackground(background);
		    	  pane2.add(btTotal, 0, 3); 
		    	  btTotal.setOnAction(d-> { 
		    			Stage stage3 = new Stage();
		    			GridPane pane3 = new GridPane();
		    			pane3.setPadding( new Insets( 100,130, 100,130 ));
		    			pane3.setBackground(background1); 
		                pane3.setAlignment(Pos.CENTER); 
		        		pane3.setHgap(15.5);
		        		pane3.setVgap(15.5); 
		        	  	Scene scene3 = new Scene(pane3); 
		        	  	Label answer  = new  Label( ); 
		        	  	answer.setFont(Font.font(20));
				    	pane3.add(answer,1,0); 
		        	  	try {
							if(total(year1.getText().trim())==0)
								answer.setText("Yaer not found!!");
							else {
								answer.setText("Total is: "+total(year1.getText().trim()));
							}
						} catch (FileNotFoundException e1) { 
							e1.printStackTrace();
						}
				    	Button ok = new Button("Ok");
				    	  ok.setTextFill(Color.RED);
				    	  ok.setFont(Font.font(16));
				    	  GridPane.setHalignment( ok, HPos.CENTER);
				    	 ok.setBackground(background);
				    	  pane3.add(ok, 1, 1); 
				    	  ok.setOnAction(o-> {
			        		 stage3.close();
			        	 }); 
		                stage3.setTitle("Answer");
		                stage3.setScene(scene3);  
		                stage3.show(); 
		    	  });
		    	  //create button to cancel 
		    	Button btCancel = new Button("Cancel");
		    	btCancel.setTextFill(Color.RED);
		    	btCancel.setFont(Font.font(16));
		    	btCancel.setBackground(background);
		    	GridPane.setHalignment( btCancel, HPos.RIGHT);
		    	pane2.add(btCancel, 1, 3);  
        		btCancel.setOnAction(d-> {
        			stage2.close();
        		});
		    	  Scene scene = new Scene(pane2); 
        		
                stage2.setTitle("Find Total");
                stage2.setScene(scene);  
                stage2.show();
    		});

    		Button export = new Button( );
    		export.setTextFill(Color.BROWN);
    		export.setFont(Font.font(14));  
    		export.setPrefSize(200, 200);
    		export.setGraphic(new ImageView(new Image("export.png",200,200,false,true)));
    		export.setBackground(background );
    		export.setOnAction(k-> {
    			Stage stage2 = new Stage();
    			GridPane pane2 = new GridPane();
    			pane2.setPadding( new Insets( 100,130, 100,130 ));
    			pane2.setBackground(background1); 
                pane2.setAlignment(Pos.CENTER); 
        		pane2.setHgap(15.5);
        		pane2.setVgap(15.5);  
		    	  
        		
		    	  //Create number of shares label
		    	Label name= new Label( );
		    	name.setFont(Font.font(20));
		    	pane2.add(name,0,0);   
		    	
		    	File report = new File("report.txt");
		    	if(export(report ) ==0){
		    		name.setText("File not found");
		    	}else if(export(report ) ==-1){
		    		name.setText("Your files were empty"); 
		    	}else {
		    		name.setText("Your report has been exported to report.txt file"); 
		    	}
		    	
		    	  //create button to cancel 
		    	Button btCancel = new Button("Ok");
		    	btCancel.setTextFill(Color.RED);
		    	btCancel.setFont(Font.font(16));
		    	btCancel.setBackground(background);
		    	GridPane.setHalignment( btCancel, HPos.RIGHT);
		    	pane2.add(btCancel, 0, 2);  
        		btCancel.setOnAction(d-> {
        			stage2.close();
        		});
		    	  Scene scene = new Scene(pane2); 
        		
                stage2.setTitle("Export");
                stage2.setScene(scene);  
                stage2.show();
    		});
    		
    		Button exit = new Button( );
    		exit.setTextFill(Color.BROWN);
    		exit.setFont(Font.font(14));
    		exit.setPrefSize(200, 200);
    		exit.setGraphic(new ImageView(new Image("exit.png",200,200,false,true)));
    		exit.setBackground(background );
    		exit.setOnAction(x -> Platform.exit()); 
    		
    		pane1.add(search, 0, 0); 
    		pane1.add(average, 1, 0);
    		pane1.add(max, 2, 0);
    		pane1.add(total, 0, 1);
    		pane1.add(export, 1, 1);
    		pane1.add(exit, 2, 1);
    		
    		Scene secondScene = new Scene(pane1, 900,600); 
    		
            stage1.setTitle("Home");
            stage1.setScene(secondScene);  
            stage1.show();
		});
            } 
        });
		
		Button cancel = new Button("Cancel");
		cancel.setFont(Font.font(14)); 
		cancel.setTextFill(Color.DARKGREEN);
		cancel.setBackground(background );
		cancel.setPrefSize(100, 50);
		pane.add(cancel, 2,5); 
		cancel.setOnAction(e -> Platform.exit()); 
		 
		Scene scene  = new Scene(pane);
		stage.setScene(scene);
		stage.setTitle("Babies");
		stage.show();
	} 
	
	public static void readFiles(String directory) {
		File folder = new File(directory);
		File[] listOfFiles = folder.listFiles();
		Scanner scan; 
		String year;
		int cuma2;   
		String line;
		Name name ; 
		
		if (listOfFiles.length > 0) {
			for (int i = 0; i < listOfFiles.length; i++ ) {
				if (listOfFiles[i].isFile()) { 
				 
				try {
					scan = new Scanner( new FileReader(listOfFiles[i] )); 
					 
					while(scan.hasNextLine() ) {
						line = scan.nextLine();
						cuma2 = line.lastIndexOf(',');
						name = new Name(line) ;
						
						//USA_yob2000.txt 
						year = (listOfFiles[i].getName().substring(7,listOfFiles[i].getName().indexOf("."))); 
						tree.insert(name, year, Integer.parseInt(line.substring(cuma2+1))); 
					} 
					scan.close();
				} catch (FileNotFoundException e) { 
					e.printStackTrace();
				} 
				}
			}
	  }
	}
	
	public static ANode search(String name) {
		Name name1 = new Name(name );
		return tree.find(name1);
	}

	public static double average(String name) {
		double avg=0;
		int size=0;
		int total =0;
		LNode current= new LNode();
		Name name1 = new Name(name );
		ANode found = tree.find(name1);
		if(found!= null) {
			size =found.getFrecuenies().size;
			current= found.getFrecuenies().firstNode;
			while(current!=null) {
				total= total+ current.data.getFrecuency();
				current=current.next;
			}
			avg=(double) total/size;
		}
		return avg;
	}

	public static ANode max() {
		return tree.findMax();
	}

	public static int lines(File file ) {
		int count = 0; 
	    try {   
	      Scanner sc = new Scanner(file); 
	      while(sc.hasNextLine()) {
	        sc.nextLine();
	        count++;
	      } 
	      sc.close();
	    } catch (Exception e) {
	      e.getStackTrace();
	    }
	    return count;
	}
	   
	public static int total(String year) throws FileNotFoundException {
		File folder = new File(directoryi);
		File[] listOfFiles = folder.listFiles();   
		int total = 0;
		if (listOfFiles.length > 0) {
			for (int i = 0; i < listOfFiles.length; i++ ) { 
				if( listOfFiles[i].isFile() && listOfFiles[i].getName().substring(7,listOfFiles[i].getName().indexOf(".")).equalsIgnoreCase(year)) {
					total=  lines(listOfFiles[i]);
				}  
			}
	  }
		return total;  
	}
	
	public static int export(File selectedFile) { 
		return tree.write(selectedFile.getPath());
	}
		  
}
