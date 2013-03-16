package at.reseau.graph.util;


public class UtilMatrix {

	public static int max(int n, int m) {
		if(n>m) {
			return n;
		} else {
			return m;
		}
	}
	
	public static int min(int n, int m) {
		if(n<m) {
			return n;
		} else {
			return m;
		}
	}
	
	public static int exp(int n,int e) {
		if(e == 1) {
			return n;
		} else {
			return n*exp(n,e-1);
		}
	}

}
