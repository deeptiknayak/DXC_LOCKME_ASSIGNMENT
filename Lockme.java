package com.file;

import java.io.*;
import java.util.*;
import java.io.*;
import java.net.URL; 
public class Lockme {
	static int count=0;
	public static void main(String arg[]) {
		Scanner sc=new Scanner(System.in);
		File file=null;
		File fileurl=null;
		int f=0;String record="";
		int ch;
		file=createFile();
		fileurl=createurl();
		
		while(true)
    {
			System.out.println("**************************************************");
			System.out.println("**************************************************");
			System.out.println("**********WELCOME TO LOCKME.COM******************");
			System.out.println("**************************************************");
			System.out.println("**************************************************");
      System.out.println("Enter ur choice:");
      System.out.println("  1. Registration");
      System.out.println("  2. Login");
      System.out.println("  3. Delete user");
      System.out.println("  4. Close the application");
		ch=sc.nextInt();
		switch(ch) {
		case 1:
			System.out.println("**************************************************");
			System.out.println("*********WELCOME TO REGISTRATION PAGE*************");
			System.out.println("**************************************************");
			System.out.println("*****************INSTRUCTIONS*********************************");
			System.out.println("UserName: User name should have 5 letters in lower case and 3 numbers after the letter");
			System.out.println("Password should be 7 character or more. Password should begin with capital letter, special character is not permitted");
			System.out.println("Username :");
			String name=sc.next();
			System.out.println("Password :");
			String p=sc.next();
			System.out.println("Validating the input....");
			if(name.length()==8)
			{
				if((name.charAt(0)>='a'&&name.charAt(0)<='z')&&(name.charAt(1)>='a'&&name.charAt(1)<='z')&&(name.charAt(2)>='a'&&name.charAt(2)<='z'))
				{
					if((name.charAt(3)>='a'&&name.charAt(3)<='z')&&(name.charAt(4)>='a'&&name.charAt(4)<='z')&&(Character.isDigit(name.charAt(5))))
					{

						if((Character.isDigit(name.charAt(6)))&&(Character.isDigit(name.charAt(7))))
						{
							//System.out.println("User name entered is valid");
							if(p.length()>=7) {
								if(p.charAt(0)>='A'&&p.charAt(0)<='Z') {
									for(int i=1;i<p.length();i++) {
										if((p.charAt(i)>='A'&&p.charAt(i)<='Z')||(p.charAt(i)>='a'&&p.charAt(i)<='z')||(p.charAt(i)>='0'&&p.charAt(i)<='9'))
												continue;
										else {
											f=1;break;
										}
									}
									if(f==0)
									{
										System.out.println("User name entered is valid");
										record=name+" "+p;
										writeFile(file,record);
										System.out.println("The user was successfully added! Kindly login with the user name and password");
									}
								}
							}
						}
					}
				}
			}
			
			readFile(file);
			break;
			
		case 2:
			System.out.println("Enter username to login");
			String username=sc.next();
			System.out.println("Enter password to login");
			String pwd=sc.next();
			if(login(username,pwd)==1)
			{
				System.out.println("Welcome! "+username);
				System.out.println("************************************************************************************************************************************************");			
				while(true)
				{
					int choice;
					System.out.println("  1. Add URL");
					System.out.println("  2. View Url");
					System.out.println("  3. Search URL");
					System.out.println("  4. My Account");
					System.out.println("  5. Logout");
					choice=sc.nextInt();
					
					switch(choice) {
						case 1:
							System.out.println("Enter url");
							String url=sc.next();
							if (isValid(url))
							{			
								System.out.println("The entered url is correct,URL validation is completed");
								writeurl(fileurl,url);
							}
							else
								System.out.println("URL eneterd is wrong");
							System.out.println();
							break;
						case 2:
							viewurl();
							System.out.println();
							break;
						case 3:
							System.out.println("Enter url to search");
							String url1=sc.next();
							if(searchurl(url1)==true)
								System.out.println("URL Found");
							else
								System.out.println("URL not Found");
							System.out.println();
						break;
						case 4:
							System.out.println("Username is: "+username);
							System.out.println();
							myaccount();
							System.out.println();
						break;
						case 5:
						System.out.println("Successfully logged out");
						break;
					}
					if(choice==5)
					break;
				}	
			}
			else
				System.out.println("Your username and password does not match");
		break;
		case 3:
		System.out.println("Username to be deleted from File");
		String uname=sc.next();
		deleteuser(uname);
		break;		
		case 4:
			return;
			
		}
	}
		
	}
	public static File createFile() {
		File file=null;
		try {
			file=new File("registered.txt");
			boolean res=file.createNewFile();
		}
		catch(IOException e){}
		return file;
	}
	public static void writeFile(File file,String record) {
		FileOutputStream fos=null;
		try {
			fos=new FileOutputStream(file,true);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			byte ar[]=record.getBytes();
			fos.write(ar);
			fos.write('\n');
			count++;
		}
		catch(IOException e) {}
	}
	public static void readFile(File f) {
		FileInputStream fis=null;int d=0;
		try {
			fis=new FileInputStream(f);
			while((d=fis.read())!=-1){
				System.out.print((char)d);
			}

		}
		catch(IOException e) {}
	}
	
		public static void deleteuser(String lineToRemove) {
			int f=0;
		File inputFile = new File("registered.txt");
File tempFile = new File("myTempFile.txt");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

//String lineToRemove = "bbb";
String currentLine;

while((currentLine = reader.readLine()) != null) {
    // trim newline when comparing with lineToRemove
	String w[]=currentLine.split(" ");
    String trimmedLine = w[0].trim();
    if(trimmedLine.equals(lineToRemove))
	{	
		f=1;
		System.out.println(lineToRemove+" user deleted");
		continue;
	}
    writer.write(currentLine + System.getProperty("line.separator"));
}
writer.close(); 
reader.close(); 
//boolean successful = tempFile.renameTo(inputFile);
if(f==0)
System.out.println("User not present in file");
//CopyFile

FileInputStream instream = null;
	FileOutputStream outstream = null;
 
    	    File infile =new File("myTempFile.txt");
    	    File outfile =new File("registered.txt");
 
    	    instream = new FileInputStream(infile);
    	    outstream = new FileOutputStream(outfile);
 
    	    byte[] buffer = new byte[1024];
 
    	    int length;
    	    /*copying the contents from input stream to
    	     * output stream using read and write methods
    	     */
    	    while ((length = instream.read(buffer)) > 0){
    	    	outstream.write(buffer, 0, length);
    	    }

    	    //Closing the input/output file streams
    	    instream.close();
    	    outstream.close();


		}
		catch(IOException e) {}
	}

public static int login(String lineToRemove,String password) 
{
	int f=0;
	System.out.println("Validating the creadentials");
	File inputFile = new File("registered.txt");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));

String currentLine;

while((currentLine = reader.readLine()) != null) {

	String w[]=currentLine.split(" ");
    String trimmedLine = w[0].trim();
	String trimmedLine1 = w[1].trim();
    if(trimmedLine.equals(lineToRemove)&&trimmedLine1.equals(password))
	{	
		f=1;
		System.out.println("Login Successful");
		System.out.println("Logged in Successfully");
		break;
	}
}
reader.close(); 

}	
catch(IOException e) {}
if(f==1)
	return 1;
else
	return 0;	
}	
	
	
	
	public static File createurl() {
		File file=null;
		try {
			file=new File("url.txt");
			boolean res=file.createNewFile();
		}
		catch(IOException e){}
		return file;
	}
	public static void writeurl(File fileurl,String record) {
		FileOutputStream fos=null;
		try {
			fos=new FileOutputStream(fileurl,true);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			byte ar[]=record.getBytes();
			fos.write(ar);
			fos.write('\n');
			count++;
		}
		catch(IOException e) {}
	}
	
	public static void readurl(File f) {
		FileInputStream fis=null;int d=0;
		try {
			fis=new FileInputStream(f);
			while((d=fis.read())!=-1){
				System.out.print((char)d);
			}

		}
		catch(IOException e) {}
	}
	
	
	public static boolean isValid(String url) 
    { 
        /* Try creating a valid URL */
        try { 
            new URL(url).toURI(); 
            return true; 
        } 
          
        // If there was an Exception 
        // while creating URL object 
        catch (Exception e) { 
            return false; 
        } 
    } 
	
	
public static void viewurl() 
{
	int f=0,c=0;
	File inputFile = new File("url.txt");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));

String currentLine;

while((currentLine = reader.readLine()) != null) {
	if(c==0)
	{
		System.out.println("URl file contents are");
		System.out.println();
	}
	System.out.println(currentLine);
	f=1;
	c++;
}

reader.close(); 

}	
catch(IOException e) {}
if(f==0)
	System.out.println("URL file is empty");
	System.out.println();
}	


public static boolean searchurl(String url) 
{	
	File inputFile = new File("url.txt");
try
{
BufferedReader reader = new BufferedReader(new FileReader(inputFile));

String currentLine;

while((currentLine = reader.readLine()) != null) {

    String trimmedLine = currentLine.trim();
    if(trimmedLine.equals(url))
	{		
			return true;
	}
}
reader.close(); 
}
catch(Exception e) {}	
	return false;
}

public static void myaccount() 
{	
	int f=0,c=0;
	File inputFile = new File("url.txt");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));

String currentLine;

while((currentLine = reader.readLine()) != null) {
	if(c==0)
	{
		System.out.println("URL's are:");
		System.out.println();
	}
	System.out.println(currentLine);
	f=1;
	c++;
}

reader.close(); 

}	
catch(IOException e) {}
if(f==0)
	System.out.println("URL's are not there");
	System.out.println();
}	
		
		




		}