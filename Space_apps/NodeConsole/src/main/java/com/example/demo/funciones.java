package com.example.demo;

import java.io.IOException;
import java.nio.file.attribute.FileTime;
import java.util.Date;
import java.util.Scanner;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserPrincipal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.nio.file.attribute.BasicFileAttributes;

public class funciones {
	static File log=createRoot();
	
	//MENU
	public static void menu() throws IOException {
		Scanner sc=new Scanner(System.in);
		
		System.out.println();
		System.out.println("+======================= Menu ========================+");
		System.out.println("|0. View the path of the root directory.              |");
		System.out.println("|                                                     |");
		System.out.println("|               Local request                         |");
		System.out.println("|1. Create file.                                      |");
		System.out.println("|2. Modify file.                                      |");
		System.out.println("|3. Delete file.                                      |");
		System.out.println("|4. List of files.                                    |");
		System.out.println("|5. View a file.                                      |");
		System.out.println("|-----------------------------------------------------|");
		System.out.println("|               Remote request                        |");
		System.out.println("|9.  List all. (GET)                                  |");
		System.out.println("|10. Delete file. (DELETE)                            |");
		System.out.println("|11. Create file. (POST)                              |");
		System.out.println("|12. Transfer file.                                   |");
		System.out.println("|-----------------------------------------------------|");
		System.out.println("|13. Exit.                                            |");
		System.out.println("+=====================================================+");
		
		System.out.println();
		System.out.println("Enter an option: ");
		int op=sc.nextInt();
		
		switch(op) {
			case 0:
	           	funciones.path();
	            break;
			case 1:
				funciones.createFile();
				break;
			case 2: 
				funciones.modifyFile();
				break;
	        case 3:
	            funciones.deleteFile();
	            break;
	        case 4:
	            funciones.listOfFiles();
	            break;
	        case 5:
	        	funciones.viewFiles();
	        	break;
	        case 13:
	        	System.out.println("Bye.");
	        	System.exit(0);
	        	break;
	    default:
            System.out.println("The selected number isn't a valid option");
            break;
		}
		funciones.menu();
	}
	
	
	
	//---------------------------------AUXILIARY FUNCTIONS---------------------------------
	
	
	//CREATE ROOT (RUNS AT THE START OF THE PROGRAM) + FILE .LOG
	
	public static File createRoot() {
		//ROOT DIRECTORY
        File dir = new File("NodeFiles");
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                System.out.println("Creating root directory.");
            } else {
                System.out.println("Error creating root directory.");
            }
        }
        else {
        	System.out.println("Initializing node root.");
        }
        //text.log FILE
        File file = new File("NodeFiles\\text.log");
        try {
			if (file.createNewFile())
			    System.out.println("text.log file created and added.");
			else
				System.out.println("The directory already exist or couldn't be created.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return file;
	}
	
	
	//SHOW FILES (OPTIONAL) + ASK FOR FILE NAME
	
	public static File getFiles(String operation) {
		Scanner sc= new Scanner(System.in);
		
		System.out.printf("Enter the name of the file you want to %s.\n",operation);
		
		String archive = sc.next();
		String path="NodeFiles\\"+archive;
		File file = new File(path);
		return file;
	}
	
	
	//UPDATE TEXT.LOG AFTER EACH INSERT, MODIFICATION, DELETE
	//COMMENT
	
	public static void updateLog(File f,String o,File log){
		Scanner sc=new Scanner(System.in);
		//COMMENT
		String comment=null;
		System.out.println("Enter a comment if you want.");
		comment=sc.nextLine();
		//DATE
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		//FILE
		String name=f.getName();
		//USER
		String username="";
		try {
            Path path = Paths.get(f.getAbsolutePath());
            UserPrincipal owner = Files.getOwner(path);
            username = owner.getName();
        } catch (Exception e) {
            System.out.println("Exception: " + e.toString());
        }
		//WE ENTER ALL THE DATA
		FileWriter writer;
		try {
			writer = new FileWriter(log.getAbsoluteFile(), true);
			writer.write(date+"  -  "+name+"  -  "+o+"  -  "+username+"  -  "+comment+"\n");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	//SHOW PATH
	
	public static void path() {
		System.out.println("Path -> current directory, folder 'NodeFiles'");
	}
	
	
	//---------------------------------MODO LOCAL---------------------------------
	
	
	//CREATE FILE
	
	public static void createFile() {
		File file = getFiles("create");
        try {
        	//We create the file if we can. If not, it is notified.
        	if (file.createNewFile()) {
            	System.out.println("The file has been created successfully");
            	updateLog(file,"CREATE",log);
            }
            else {
                System.out.println("The file could not be created.");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
	}
	
	
	//MODIFY FILE
	
	public static void modifyFile() {
		File file = getFiles("modify");
		try {
			Runtime.getRuntime().exec("notepad "+file.getAbsolutePath());
			updateLog(file,"MODIFY",log);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//DELETE FILE
	
	public static void deleteFile() {
		File file = getFiles("delete");
		//We are careful so that the txt.log file cannot be deleted
		if(!file.getPath().toString().contains(".log")) {         
			//If the file can be deleted, we proceed. If not, it is notified.
			if (file.delete()) {
				System.out.println("The file has been deleted successfully");
				updateLog(file,"DELETE",log);
			}
			else {
				System.out.println("The file cannot be deleted, or it does not exist.");
			}
		}
		else {
			System.out.println("Files with a .log extension cannot be deleted, for security reasons.");
		}
	}
	
	
	//SHOW ALL FILES IN THE NODE
	
	public static void listOfFiles() throws IOException {
		File dir=new File("NodeFiles");
		File[] list=dir.listFiles();
		if (list == null || list.length == 0) {
		    System.out.println("There are no items inside the root folder.");
		    return;
		}
		else {
			System.out.print("File name:       -  Creation date      -  User \n");
	    	 for (File file : list) {
			    	//FILE NAME
			    	System.out.print(file.getName()+"  -  ");
			    	//CREATION DATE
			    	
			    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    BasicFileAttributes attrs = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                    FileTime time = attrs.creationTime();
                    LocalDateTime time2=LocalDateTime.ofInstant(time.toInstant(), ZoneId.systemDefault());
                    String date = dtf.format(time2);
			    	
		    		System.out.print(date+"  -  ");
			    	//USER
		    		Path path = Paths.get(file.getAbsolutePath());
		            UserPrincipal owner = Files.getOwner(path);
		            String username = owner.getName();
		            System.out.println(username);
				}
		}
	}
	
	
	    //VIEW FILE
	    public static void viewFiles() throws IOException {
	    	File file = getFiles("view.");
	    	FileReader fr = null;
	        BufferedReader br = null;
    	    fr = new FileReader (file);
        	br = new BufferedReader(fr);
        	//WE READ THE FILE
	    	String line;
    		while((line=br.readLine())!=null) {
	        	System.out.println(line);
	      	}
	    }
	    
	    
	
	//---------------------------------REMOTE MODE---------------------------------
	
}
