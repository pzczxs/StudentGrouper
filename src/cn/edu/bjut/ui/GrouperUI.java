package cn.edu.bjut.ui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.bjut.core.IGrouper;
import cn.edu.bjut.core.Student;
import cn.edu.bjut.core.StudentGrouper;

public class GrouperUI {
	private String fname; 
	private int nMale = 0; 
	private int nFemale = 0; 
	
	public GrouperUI(final String fname) {
		this.fname = fname; 
	}
	
	public List<Student> load() {
		List<Student> data = new ArrayList<Student>(); 
		
		BufferedReader reader = null; 
		try {
			reader = new BufferedReader(new FileReader(fname));
			
			int nLine = 0;
			for (String nextLine; ((nextLine = reader.readLine()) != null); nLine++) {
				final String[] parts = nextLine.split("\\t"); 
				
				if (parts.length != 4) {
					throw new IllegalArgumentException("The format of file \"" + fname + "\" is wrong at #line: " + nLine); 
				}
				
				if (parts[2].equalsIgnoreCase("M")) {
					data.add(new Student(parts[0], parts[1], true, parts[3])); 
					nMale++; 
				} else if (parts[2].equalsIgnoreCase("F")) {
					data.add(new Student(parts[0], parts[1], false, parts[3])); 
					nFemale++; 
				} else {
					; // do nothing
				}
				
			}
			reader.close(); 
			
			System.out.println("#of male students: " + nMale);
			System.out.println("#of female students: " + nFemale); 
			System.out.println(); 
		} catch (Exception e) {
			e.printStackTrace(); 
		} finally {
			try {
				reader.close(); 
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		return data; 
	}
	
	private static void usage(final String programName) {
		System.out.println(programName + " v1.0: Grouping randomly the students into several groups.");
		System.out.println("Author: XU Shuo (pzczxs@gmail.com)");
		System.out.println("Usage: java -jar " + programName + " [-nfold num] [-seed num] -file input_file");
		System.out.println("\t-nfold num: the number of groups (default 2)");
		System.out.println("\t-seed num: random seed (default 0)");
		System.out.println("\t-file input_file: student name list seperated by tab.");
		System.exit(0);
	}
	
	private static Map<String, String> parse(final String[] args) {
		Map<String, String> params = new HashMap<String, String>(); 
		
		for (int i = 0; i < args.length; i++) {
			final String arg = args[i]; 
			
			if (arg.startsWith("-")) {
				params.put(arg.substring(1).toLowerCase(), args[++i]); 
			}
		}
		
		return params; 
	}
	
	static public void main(String[] args) {
		final String programName = "StudentGrouper.jar"; 
		
		if (args.length > 6 || args.length < 1) {
			usage(programName);
		}
		
		final Map<String, String> params = parse(args); 
		for (Map.Entry<String, String> e: params.entrySet()) {
			System.out.println(e.getKey() + "\t" + e.getValue());
		}
		
		if (!params.containsKey("file")) {
			System.err.println("Please specify the input file name.");
		}
		
		GrouperUI ui = new GrouperUI(params.get("file")); 
		List<Student> data = ui.load(); 
		
		final int nfold = (params.containsKey("nfold"))? Integer.parseInt(params.get("nfold")): 2; 
		final long seed = (params.containsKey("seed"))? Long.parseLong(params.get("seed")): 0; 
		
		System.out.println(nfold + "\t" + seed);
		IGrouper grouper = new StudentGrouper(data, nfold, seed); 
		for (int v = 0; v < nfold; v++) {
			final List<Student> group = grouper.getGroup(v); 
			
			System.out.println("==========Group " + v + "=========="); 
			for (int i = 0; i < group.size(); i++) {
				System.out.println(group.get(i)); 
			}
		}
	}
}
