import java.util.*;

public class Dcompress_to_Compressed_String {
	
	static String strNum = "";
	static String letters = "";
	static String innerNum = "";
	static String innerL = "";
	static int innerIndex = 0;
	static ArrayList<Integer> nums = new ArrayList<Integer>();
	static ArrayList<String> strs = new ArrayList<String>();
	static ArrayList<String> innerStrs = new ArrayList<String>();
	static ArrayList<String> innerNums = new ArrayList<String>();
	static String out = "";
	static boolean open = false;
	static boolean innerOpen = false;
	
	
	public static void main(String[] args) {
		String strIn = "x3[a3[b]2[c]nn]r4[h2[q]]j";
		//String strIn = "2[3[a]b]";
		decompress_a_compressed_string(strIn);
	}
	
	public static String decompress_a_compressed_string(String str) {
		
		for(int i=0; i<str.length(); i++) {
			
			if(Character.isDigit(str.charAt(i)) == true && open==false) { //add to array if integer
				strNum += str.charAt(i); 
				nums.add(Integer.parseInt(strNum));
				
				if(Character.isDigit(str.charAt(i+1)) == false) {
					strNum = "";
				}		
			}
			
			if(Character.isDigit(str.charAt(i))==true && open==true) { //add to inner array if integer
				innerNum = String.valueOf(str.charAt(i)); 
				innerNums.add(innerNum);
			}
			
			if(str.charAt(i)=='[' && open==true) { //Check if there is inner
				innerOpen = true;
			}
			
			if(str.charAt(i)=='[' && open==false) { //Check for open bracket
				open = true;
			}
			
			if(String.valueOf(str.charAt(i)).matches("[a-z?]") && innerOpen==true) { //add inner to string if a-z
				for(int j=0; j<Integer.parseInt(innerNums.get(innerIndex)); j++) {
					innerL += str.charAt(i);
					innerStrs.add(String.valueOf(str.charAt(i)));
				}
				innerIndex++;
			}
			
			if(String.valueOf(str.charAt(i)).matches("[a-z?]") && open==false) { //add to string if a-z when open=false
				letters += str.charAt(i);
			}
			
			if(String.valueOf(str.charAt(i)).matches("[a-z?]") && open==true && innerOpen==false) { //add to string if a-z when open=true
				letters += str.charAt(i);
			}
			
			if(String.valueOf(str.charAt(i)).matches("[a-z?]") && open==false) { //add letters out of brackets to array strs
				strs.add(letters);
				letters = "";
				nums.add(1);
			}
			
			if(str.charAt(i)==']' && open==true && innerOpen==false) { //add inner letters to array strs
				strs.add(letters);
				letters = "";
				innerL = "";
				open = false;
			}
			
			if(str.charAt(i)==']' && innerOpen==true) { //Check if inner is close
				letters += innerL;
				innerL = "";
				innerOpen = false;
			}
			
			
			
		}
		
		
		
		// Add in order all letters to String out 
		for(int i=0; i<strs.size(); i++) {
			if(i >= nums.size()) {
				out += strs.get(i);
			} else {
				for(int j=0; j<nums.get(i); j++) {
					out += strs.get(i);
				}
			}
		}
		
		System.out.println(out);
		
		return(out);
		
	}
}
