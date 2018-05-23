package cn.edu.bjut.core;

import java.util.List;

/**
 * 
 * @author <a href = "mailto:pzczxs@gmail.com">XU, Shuo</a>
 * version 1.0
 *
 */
public interface IGrouper {
	
	public static long SEED_DEFAULT = 56567651; 
	
	public static int NFOLD_DEFAULT = 5; 
	
	public List<Student> getGroup(final int split);
}
