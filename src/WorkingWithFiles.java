import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
 
public class WorkingWithFiles
{
	public static void main(String[] args)
	{
		// Introduction to File class, along with relative vs. absolute paths
		// (my project folder contains a data folder that contains a file called rubbish.txt)
		File fileRelativePath = new File("Quiz-5-2LEDS.jpg");
		File fileAbsolutePath = new File("/Users/peter/Desktop/Quiz-5-2LEDS.jpg");
		System.out.println( fileRelativePath.getName() ); // rubbish.txt
		System.out.println( fileRelativePath.getPath() ); // data\rubbish.txt
		System.out.println( fileAbsolutePath.getPath() ); // C:\Users\dahl\Desktop\Week13\data\rubbish.txt
		System.out.println( fileRelativePath.getAbsolutePath() ); // C:\Users\dahl\Desktop\Week13\data\rubbish.txt
 
		// Folders/directories can also be represented by File objects
		File folder = new File("data"); // my project folder contains a folder named data
		System.out.println( folder.exists() ); // true
		System.out.println( folder.isDirectory() ); // true		
		File[] folderContents = folder.listFiles();
		for(File f : folderContents) // replaced : for(int i=0;i<contents.length;i++)
									 //                File f = contents[i];
			if(f.isDirectory())
				System.out.println("   +" + f.getName());
			else
				System.out.println("    " + f.getName() + " ("+f.length()+")");		
	
		// We can read input in from files using a scanner:
		File existingFile = new File("data/treasure.txt");
		try {
			Scanner in = new Scanner( existingFile );			
			// display the contents of this file to the console
			while( in.hasNextLine() ) System.out.println( in.nextLine() );
			in.close();			
		} catch(FileNotFoundException e) {
			System.out.println("Could not read from file: " + existingFile.getAbsolutePath());
		}
 
		File newFile = new File("data/myNewFile.txt");
		try {
			PrintWriter out = new PrintWriter(newFile);
			// print the numbers 1-10 into this new file
			for(int i=0;i<10;i++) out.println((i+1) + " is awesome!");
			out.close();
		} catch(FileNotFoundException e) {
			// we expect this new file to be not found, but this exception will be
			// throws when we do not have permission to write to this this, or when
			// the folder that this files is supposed to be in does not exist
			System.out.println("Could not write to file: " + newFile.getAbsolutePath());			
		}
		
	}
	
}