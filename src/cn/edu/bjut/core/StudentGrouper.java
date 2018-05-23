package cn.edu.bjut.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.edu.bjut.utils.CokusRandom;
import cn.edu.bjut.utils.RandomSamplers;

/**
 * 
 * @author <a href = "mailto:pzczxs@gmail.com">XU, Shuo</a>
 * version 1.0
 *
 */
public class StudentGrouper implements IGrouper {
	private List<Student> data;
	
	private int nfold; 
	
	private long seed; 
	
	// permutation of the male and female corpus used for splitting
	private int[] permMale = null;
	private int[] permFemale = null;

	// starting points of the male and female corpus segments
	private int[] startsMale = null;
	private int[] startsFemale = null;
	
	// indexes for male and female corpus
	private int[] idxMale = null;
	private int[] idxFemale = null; 
	
	public StudentGrouper(final List<Student> data, final int nfold, final long seed) {
		this.data = data; 
		this.nfold = nfold; 
		this.seed = seed; 
		
		List<Integer> vidxPlus = new ArrayList<Integer>();
		List<Integer> vidxMinus = new ArrayList<Integer>(); 
		
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).isGender()) { // male
				vidxPlus.add(i); 
			} else { // female
				vidxMinus.add(i); 
			}
		}
		
		final int nPlus = vidxPlus.size();
		idxMale = new int[nPlus]; 
		for (int i = 0; i < nPlus; i++) {
			idxMale[i] = vidxPlus.get(i); 
		}
		
		final int nMinus = vidxMinus.size();
		idxFemale = new int[nMinus];
		for (int i = 0; i < nMinus; i++) {
			idxFemale[i] = vidxMinus.get(i); 
		}
	}
	
	public StudentGrouper(final List<Student> data, final int nfold) {
		this(data, nfold, SEED_DEFAULT); 
	}
	
	public StudentGrouper(final List<Student> data) {
		this(data, NFOLD_DEFAULT); 
	}
	
	private void randomPermute() {
		Random rand = new CokusRandom(seed);
		
		final int nMale = idxMale.length;
		final int nFemale = idxFemale.length; 
		RandomSamplers rs = new RandomSamplers(rand);
		permMale = rs.randPerm(nMale);
		permFemale = rs.randPerm(nFemale); 
		startsMale = new int[nfold + 1]; 
		startsFemale = new int[nfold + 1]; 
		
		for (int v = 0; v <= nfold; v++) {
			startsMale[v] = Math.round(nMale * (v / (float) nfold)); 
			startsFemale[v] = Math.round(nFemale * (v / (float) nfold)); 
		}
	}

	@Override
	public List<Student> getGroup(final int split) {
		assert split < nfold && split >= 0; 
		
		if (permMale == null || permFemale == null) {
			randomPermute(); 
		}
		
		List<Student> group = new ArrayList<Student>(); 
		// plus split
		for (int m = startsMale[split]; m < startsMale[split + 1]; m++) {
			group.add(data.get(idxMale[permMale[m]])); 
		}
		// minus split
		for (int m = startsFemale[split]; m < startsFemale[split + 1]; m++) {
			group.add(data.get(idxFemale[permFemale[m]])); 
		}
		
		return group; 
	}
}
