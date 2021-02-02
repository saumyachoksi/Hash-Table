/* ALEX GERBESSIOTIS cs610 4880 prp */

/*
 * mplexicon4880.java main file to be run
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class mplexicon4880 {
	
	public static void main(String[] args) throws IOException {
		// readBatchFile() is to read the file I/O from Command Line Arg
		readBatchFile(args[0]);			
	}
	
	public static void readBatchFile(String str) throws FileNotFoundException {
		File file = new File(str);
        if (file.exists()) {
            Readable f = new FileReader(str);
            Scanner sc = new Scanner(f);
            
            hash4880 h = new hash4880();
            
            while (sc.hasNextLine()) {
                String st = sc.nextLine();
                String s[] = st.split(" ");
                List<String> al = new ArrayList<String>();
                al = Arrays.asList(s);

                if (Integer.parseInt(al.get(0))== 10){
                    h.insert(al.get(1));	// insert
                }
                if (Integer.parseInt(al.get(0))== 11){
                    int val = h.delete(al.get(1));	// delete
                    if (val != 0) {
                    	System.out.println(al.get(1) + "\t delete at slot " + val);
                    }else {
                    	System.out.println(al.get(1) + "\t not in table");
                    }
                }
                if (Integer.parseInt(al.get(0))== 12){
                    int b[] = h.search(al.get(1));	//search
                    if (b[0] == 1) {
                    	System.out.println(al.get(1) + "\t found from slot " + b[1]);
                    }else {
                    	System.out.println(al.get(1) + "\t not found");
                    }
                }
                if (Integer.parseInt(al.get(0))== 13){
                    h.print();	//print
                }
                if (Integer.parseInt(al.get(0))== 14){
                    h.create(Integer.parseInt(al.get(1)));	//create slots
                }
                if (Integer.parseInt(al.get(0))== 15){
                    System.out.println(al.get(1));	//comment
                }
            }
            sc.close();
            //h.finalout();
        }
        else {
            System.out.println("File Doesn't Exists");
        }
	}
}
