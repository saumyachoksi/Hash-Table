/* ALEX GERBESSIOTIS cs610 4880 prp */

import java.util.*;

public class hash4880 {
	int chsize = 15;
	int[] T;
	char[] A;
	int maxsize,cursize;
	int indarr = 0;
	
	// create() allocates T and Array
	public void create(int size) {
		T = new int[size];
		Arrays.fill(T,-1);
		A = new char[chsize * size];
		maxsize = size;
		cursize = 0;
		//System.out.println("Table size::"+size);
		//System.out.println("Array size::"+(chsize * size));
	}
	
	// checkFull() checks for table slots full or empty
	public int checkFull() {
		if (maxsize == cursize) {
			return (1);
		}else {
			return (0);
		}
	}
	
	// reinsert() - insert's into new table
	public void reinsert(String s) {
		int i = 0, flag = 0;
		if (s.charAt(0)!='*') {
			int h1 = asciiHash(s);
			int h2;
			do {
				h2 = (h1 + (i*i)) % maxsize;
				if (T[h2] == -1) {
					T[h2] = indVal(s);
					cursize++;
					flag = 1;
				}else {
					flag = 0;
					i++;
					if(i >= maxsize) { //overflow
						//System.out.println(s + " not getting slot");
						rehash();
					}
				}				
			}while(flag != 1);
		}else {
			indVal(s);
		}		
	}
	
	// rehash() - rehash when table overflows or slots are full
	public void rehash() {
		int[] T1 = T;
		char[] A1 = A;
		int s1 = 2 * maxsize;
		T = new int[s1];
		Arrays.fill(T,-1);
		A = new char[chsize * s1];
		indarr = 0;
		maxsize = s1;
		cursize = 0;
	
		String str = String.copyValueOf(A1);
		String[] arrofstr = str.split("\\\\");
		for (String a: arrofstr)
			reinsert(a);
		//System.out.println("rehashed");
		print();
	}
	
	//asciiHash() - calculates sum of ASCII values of char mod N
	public int asciiHash(String str) {
		int sum = 0;
        for (int i=0; i< str.length(); i++) {
            sum = sum + (int)str.charAt(i);
        }
		return sum % maxsize;
	}
	
	//indVal() - returns index value of word stored in array A
	public int indVal(String str) {
		String s1 = str + "\\";
		int n = s1.length();
		if (indarr == 0) {
			A = s1.toCharArray();
		}else {
			String s2 = String.copyValueOf(A).concat(s1);
	        A = s2.toCharArray();
		}
		indarr = indarr + n;
		return (indarr-n);
	}
	
	//insert() - inserts index value in T and word in array A
	public void insert(String s) {
		int i = 0, flag = 0, fg = -1;
		int h2;
		if (checkFull() == 1) {
			rehash();
		}
		int h1 = asciiHash(s);
		do {
			h2 = (h1 + (i*i)) % maxsize;
			if (T[h2] == -1) {
				T[h2] = indVal(s);
				cursize++;
				flag = 1;
			}else {
				flag = 0;
				i++;
				if(i >= maxsize) { //overflow
					//System.out.println(s + " not getting slot");
					rehash();
				}
			}			
		}while(flag != 1);
		
		
	}
	
	//search() - search for word through hashing
	public int[] search(String s) {
		char ch;
		String s1 ;
		int l = s.length();
		int h1 = asciiHash(s);
		int h2, flag = -1, i = 0;
		int b[] = new int[2];
		do {
			s1 ="";
			h2 = (h1 + (i*i)) % maxsize;
			int n = T[h2];
			
			if (n!=-1) {
				for (int j=n;j<l+n;j++) {
					ch = A[j];
					s1 = s1 + ch;
				}
				if(s1.equals(s)) {
					flag = 1;
					//System.out.println(s + " found at slot " + h2);
					b[0] = flag;
					b[1] = h2;
				}else {
					i++;
				}			
			}else {
				i++;
				//System.out.println(s + "not found");
			}
			if(i>maxsize) {
				flag = 0;
				//System.out.println(s + " not found");
				b[0] = flag;
				b[1] = h2;
			}
		}while(flag==-1);
		return (b);
	}
	
	// delete() - delete the word found from T and replace with * in array A
	public int delete(String s) {
		int l = s.length();
		int a[] = search(s);
		int flg = a[0];
		int ind = a[1];
		if(flg == 1) {
			int n = T[ind];
			for (int j=n;j<l+n;j++) {
				A[j]= '*';				
			}
			T[ind]=-1;
			cursize--;
			return ind;
		}
		else {
			//System.out.println(s + " not in table");
			return 0;
		}
	}
	
	//print() - prints table T and Array A 
	public void print() {
		System.out.println("\nData Structure T");
		for (int i=0; i<maxsize; i++) {
			if (T[i] != -1)
			System.out.println(i + ":" + T[i] );
			else
				System.out.println(i + ":");
		}
		System.out.println("\nArray A");
		System.out.println(A);
	}
	
	//finalout() - shows count of Slots filled in T and occupied char in array
	public void finalout() {
		int c=0, d=0;
		System.out.println("\nData Structure T");
		for (int i=0; i<maxsize; i++) {
			if (T[i] != -1)
			c++;
		}
		System.out.println("T Slots filled "+ c);
		System.out.println("Array space occupied " + A.length);
	}

}
