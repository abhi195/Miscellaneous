/*
 * Author: Abhi Sapariya
 */
import java.util.*;
import java.io.*;
import java.math.*;
import java.text.DecimalFormat;

public class Template{
	static int MOD = (int)Math.pow(10,9)+7;
	static int INF = Integer.MAX_VALUE;
	static InputReader in;
    static PrintWriter out;
	
	public static void main(String[] args) {
		
		in = new InputReader(System.in);
		out = new PrintWriter(System.out);
		
		//CODE
		
	    out.close();
	}
    
    static class Pair implements Comparable<Pair>{

    	int x;
    	int y;
    	
    	public Pair(int x, int y){
    		this.x = x;
    		this.y = y;
    	}
    	
    	// Ascending First X then Y
		@Override
		public int compareTo(Pair p) {
			if( x == p.x )
				return y-p.y;
			return x-p.x;
		}
		
		@Override
		public String toString(){
			return "(" + x + "," + y + ")";
		}
    }
    
    // This class represents a directed graph using adjacency list
	// representation
	class Graph {
		private int V; // No. of vertices

		// Array  of lists for Adjacency List Representation
		private LinkedList<Integer> adj[];

		// Constructor
		Graph(int v) {
			V = v;
			adj = new LinkedList[v];
			for (int i = 0; i < v; ++i)
				adj[i] = new LinkedList<Integer>();
		}

		//Function to add an edge into the graph
		void addEdge(int v, int w) {
			adj[v].add(w); // Add w to v's list.
		}

		// A function used by DFS
		void DFSUtil(int v, boolean visited[]) {
			// Mark the current node as visited and print it
			visited[v] = true;
			System.out.print(v + " ");

			// Recur for all the vertices adjacent to this vertex
			Iterator<Integer> i = adj[v].listIterator();
			while (i.hasNext()) {
				int n = i.next();
				if (!visited[n])
					DFSUtil(n, visited);
			}
		}

		// The function to do DFS traversal. It uses recursive DFSUtil()
		void DFS(int v) {
			// Mark all the vertices as not visited(set as
			// false by default in java)
			boolean visited[] = new boolean[V];

			// Call the recursive helper function to print DFS traversal
			DFSUtil(v, visited);
		}
	}
    
	// Class for primes utilities
	static class Primes{
		
	    //for marking all prime numbers greater than 1 and less than equal to N
	    boolean[] sieveOfEratosthenes(int n) {

			boolean prime[] = new boolean[n+1];
			// Create a boolean array "prime[0..n]" and initialize
			// all entries it as true. A value in prime[i] will
			// finally be false if i is Not a prime, else true.
			Arrays.fill(prime, true);

			for (int p = 2; p * p <= n; p++) {
				// If prime[p] is not changed, then it is a prime
				if (prime[p] == true) {
					// Update all multiples of p
					for (int i = p * 2; i <= n; i += p)
						prime[i] = false;
				}
			}
			
			return prime;
		}
		
	    ArrayList<Long> primeFactors(long n){
	    	ArrayList<Long> ans = new ArrayList<Long>();
	    	// Print the number of 2s that divide n
	        while (n%2L==0L)
	        {
	            ans.add(2L);
	            n /= 2L;
	        }
	 
	        // n must be odd at this point.  So we can
	        // skip one element (Note i = i +2)
	        for (long i = 3; i <= Math.sqrt(n); i+= 2L)
	        {
	            // While i divides n, print i and divide n
	            while (n%i == 0)
	            {
	            	ans.add(i);
	                n /= i;
	            }
	        }
	 
	        // This condition is to handle the case whien
	        // n is a prime number greater than 2
	        if (n > 2)
	            ans.add(n);
	        return ans;
	    }
	
	    //Minimum prime factors of number till n
		int[] minimumPrimeFactors(int n){
			int[] minPrime = new int[n+1];
			minPrime[1]=1;
	        for (int i = 2; i * i <= n; ++i) {
	            if (minPrime[i] == 0) {         //If i is prime
	                for (int j = i * i; j <= n; j += i) {
	                    if (minPrime[j] == 0) {
	                        minPrime[j] = i;
	                    }
	                }
	            }
	        }
	        for (int i = 2; i <= n; ++i) {
	            if (minPrime[i] == 0) {
	                minPrime[i] = i;
	            }
	        }
	        return minPrime;
		}
		
	    // isPrime
		boolean isPrime(long n) {
			if(n < 2L) return false;
			if(n == 2L || n == 3L) return true;
			if(n%2L == 0 || n%3L == 0) return false;	
			long sqrtN = (long)Math.sqrt(n)+1L;
			for(long i = 6L; i <= sqrtN; i += 6L) {
				if(n%(i-1) == 0 || n%(i+1) == 0) return false;
			}
			return true;
		}
	}
	
    // Binary indexed Tree parameters (arr,arr.length)
    static class BITree {

		int[] tree;

		public BITree(int arr[], int n) {
			tree = new int[n + 1];
			for (int i = 0; i < n; i++) {
				update(i, arr[i]);
			}
		}

		public void update(int x, int val) {
			x++;
			while (x < tree.length) {
				tree[x] += val;
				x = next(x);
			}
		}

		public int sum(int x) {
			x++;
			int ans = 0;
			while (x > 0) {
				ans += tree[x];
				x = parent(x);
			}
			return ans;
		}

		public int parent(int x) {
			return x - (x & -x);
		}

		public int next(int x) {
			return x + (x & -x);
		}

	}
    
    // Range minimum query Segment Tree parameters - (arr,arr.length)
    static class SegmentTree {

		static int[] segT;

		public SegmentTree(int arr[], int n) {
			int nearPow = (int) (Math.ceil(Math.log(n) / Math.log(2)));
			int len = 2 * ((int) Math.pow(2, nearPow)) - 1;
			segT = new int[len];
			constructTree(arr, 0, n - 1, 0);
		}

		public void constructTree(int arr[], int low, int high, int pos) {
			if (low == high) {
				segT[pos] = arr[low];
				return;
			}

			int mid = (low + high) / 2;
			constructTree(arr, low, mid, 2 * pos + 1);
			constructTree(arr, mid + 1, high, 2 * pos + 2);
			segT[pos] = Math.min(segT[2 * pos + 1], segT[2 * pos + 2]);
		}

		public int rmq(int low, int high, int n) {
			return rmq(0, n - 1, low, high, 0);
		}

		public int rmq(int low, int high, int qlow, int qhigh, int pos) {

			if (qlow <= low && qhigh >= high) {
				return segT[pos];
			}
			if (high < qlow || qhigh < low) {
				return Integer.MAX_VALUE;
			}
			int mid = (low + high) / 2;
			return Math.min(rmq(low, mid, qlow, qhigh, 2 * pos + 1),
					rmq(mid + 1, high, qlow, qhigh, 2 * pos + 2));
		}

	} 
    
    // Disjoint Union Set with Path Compression
    static class DUS{
    	int p;
    	int r;
    	DUS set[];
    	
    	public DUS(int pr, int rk){
    		p = pr;
    		r = rk;
    	}
    	
    	public DUS(int n){
    		set = new DUS[n+1];
    		for(int i=0; i<n; i++){
    			set[i] = new DUS(i,0);
    		}
    	}

    	public int find(int key){
    		if(set[key].p!=key){
    			set[key].p = find(set[key].p);
    		}
    		return set[key].p;
    	}

    	public void union(int x, int y){
    		int px = find(x);
    		int py = find(y);

    		if(set[px].r > set[py].r){
    			set[py].p = px;
    		}
    		else if(set[py].r > set[px].r){
    			set[px].p = py;
    		}
    		else{
    			set[py].p = px;
    			set[px].r++;
    		}
    	}
    }
    
	static class Manacher {
	    private int[]  p;  // p[i] = length of longest palindromic substring of t, centered at i
	    private String s;  // original string
	    private char[] t;  // transformed string

	    public Manacher(String s) {
	        this.s = s;
	        preprocess();
	        p = new int[t.length];

	        int center = 0, right = 0;
	        for (int i = 1; i < t.length-1; i++) {
	            int mirror = 2*center - i;

	            if (right > i)
	                p[i] = Math.min(right - i, p[mirror]);
	 
	            // attempt to expand palindrome centered at i
	            while (t[i + (1 + p[i])] == t[i - (1 + p[i])])
	                p[i]++;
	 
	            // if palindrome centered at i expands past right,
	            // adjust center based on expanded palindrome.
	            if (i + p[i] > right) {
	                center = i;
	                right = i + p[i];
	            }
	        }

	    }

	    // Transform s into t.
	    // For example, if s = "abba", then t = "$#a#b#b#a#@"
	    // the # are interleaved to avoid even/odd-length palindromes uniformly
	    // $ and @ are prepended and appended to each end to avoid bounds checking
	    private void preprocess() {
	        t = new char[s.length()*2 + 3];
	        t[0] = '$';
	        t[s.length()*2 + 2] = '@';
	        for (int i = 0; i < s.length(); i++) {
	            t[2*i + 1] = '#';
	            t[2*i + 2] = s.charAt(i);
	        }
	        t[s.length()*2 + 1] = '#';
	    }
	 
	    // longest palindromic substring
	    public String longestPalindromicSubstring() {
	        int length = 0;   // length of longest palindromic substring
	        int center = 0;   // center of longest palindromic substring
	        for (int i = 1; i < p.length-1; i++) {
	            if (p[i] > length) {
	                length = p[i];
	                center = i;
	            }
	        }
	        return s.substring((center - 1 - length) / 2, (center - 1 + length) / 2);
	    }

	    // longest palindromic substring centered at index i/2
	    public String longestPalindromicSubstring(int i) {
	        int length = p[i + 2];
	        int center = i + 2;
	        return s.substring((center - 1 - length) / 2, (center - 1 + length) / 2);
	    }
	}
	
	//Class for fibonacci utilities
	static class fibonacci {
		
		//Nth fibonacci number in log(n)
		public static long fib(long n, long mod) {
			long F[][] = new long[][] { { 1, 1 }, { 1, 0 } };
			if (n == 0l)
				return 0l;
			power(F, n - 1, mod);

			return F[0][0] % mod;
		}

		public static void multiply(long F[][], long M[][], long mod) {
			long x = (((F[0][0]) * (M[0][0])) % mod + ((F[0][1]) * (M[1][0]))
					% mod)
					% mod;
			long y = (((F[0][0]) * (M[0][1])) % mod + ((F[0][1]) * (M[1][1]))
					% mod)
					% mod;
			long z = (((F[1][0]) * (M[0][0])) % mod + ((F[1][1]) * (M[1][0]))
					% mod)
					% mod;
			long w = (((F[1][0]) * (M[0][1])) % mod + ((F[1][1]) * (M[1][1]))
					% mod)
					% mod;

			F[0][0] = x;
			F[0][1] = y;
			F[1][0] = z;
			F[1][1] = w;
		}

		/* Optimized version of power() in method 4 */
		public static void power(long F[][], long n, long mod) {
			if (n == 0l || n == 1l)
				return;
			long M[][] = new long[][] { { 1l, 1l }, { 1l, 0l } };

			power(F, n / 2l, mod);
			multiply(F, F, mod);
			if (n % 2l != 0l)
				multiply(F, M, mod);
		}
		
		static boolean isPerfectSquare(int x)
		{
		    int s = sqrt(x);
		    return (s*s == x);
		}
		 
		// Returns true if n is a Fibinacci Number, else false
		static boolean isFibonacci(int n)
		{
		    // n is Fibinacci if one of 5*n*n + 4 or 5*n*n - 4 or both
		    // is a perferct square
		    return isPerfectSquare(5*n*n + 4) ||
		           isPerfectSquare(5*n*n - 4);
		}
	}
	
	static long modInverse(long A, long M)
	{
		long x=extendedEuclid(A,M)[0];
		return (x%M+M)%M;    //x may be negative
	}
	
	
	static long[] extendedEuclid(long A, long B) {
		if(B == 0) {
			long d = A;
			long x = 1;
			long y = 0;
			return new long[]{x,y,d};
		}
		else {
			long arr[]=extendedEuclid(B, A%B);
			long temp = arr[0];
			arr[0] = arr[1];
			arr[1] = temp - (A/B)*arr[1];
			return arr;
		}
	}
	
	static int max(int a, int b){
		return Math.max(a, b);
	}
	
	static long max(long a, long b){
		return Math.max(a, b);
	}
	static int min(int a, int b){
		return Math.min(a, b);
	}
	
	static long min(long a, long b){
		return Math.min(a,b);
	}
	
	static int abs(int a, int b){
		return Math.abs(a-b);
	}
	
	static long abs(long a, long b){
		return Math.abs(a-b);
	}
	
	static int sqrt(int a){
		return (int) Math.sqrt(a);
	}
	
	static long sqrt(long a){
		return (long) Math.sqrt(a);
	}
	
	static int gcd(int a, int b) {
		if (a == 0 || b == 0)
			return a + b;
		return gcd(b, a % b);
	}
	
	static long gcd(long a, long b) {
		if (a == 0 || b == 0)
			return a + b;
		return gcd(b, a % b);
	}
	
    static int power(int base, int exponent, int modulus){
	    int result = 1;
	    while (exponent > 0) {
	        if (exponent % 2 == 1)
	            result = (result * base) % modulus;
	        exponent = exponent >> 1;
	        base = (base * base) % modulus;
	    }
	    return result;
	}
	
    static long power(long base, long exponent, long modulus){
	    long result = 1L;
	    while (exponent > 0) {
	        if (exponent % 2L == 1L)
	            result = (result * base) % modulus;
	        exponent = exponent >> 1;
	        base = (base * base) % modulus;
	    }
	    return result;
	}
	
	public void printArr(int[] arr){
		for(int i = 0; i < arr.length; i++){
			out.print(arr[i] + " ");
		}
		out.println();
	}
	
	public void print2DArr(int[][] arr){
		for( int i = 0; i < arr.length; i++){
			for ( int j = 0; j < arr[0].length; j++){
				out.print(arr[i][j] + " ");
			}
			out.println();
		}
	}
 
	static class InputReader {
        private final InputStream stream;
        private final byte[] buf = new byte[8192];
        private int curChar, numChars;
        private SpaceCharFilter filter;
 
        public InputReader(InputStream stream) {
            this.stream = stream;
        }
        
        public int read() {
            if (numChars == -1) {
                throw new InputMismatchException();
            }
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }
 
        public String nextLine() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isEndOfLine(c));
            return res.toString();
        }
 
        public String readString() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }
 
        public long nextLong() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }
 
        public int nextInt() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }
 
        public int[] nextIntArray(int n) {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextInt();
            }
            return arr;
        }
 
        public long[] nextLongArray(int n) {
            long[] arr = new long[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextLong();
            }
            return arr;
        }
        
        public int[][] nextInt2DArray(int n, int m){
        	int[][] arr = new int[n][m];
        	for (int i = 0; i < n; i++){
        		for(int j = 0; j < m; j++){
        			arr[i][j] = nextInt();
        		}
        	}
        	return arr;
        }
        
        public long[][] nextLong2DArray(int n, int m){
        	long[][] arr = new long[n][m];
        	for (int i = 0; i < n; i++){
        		for(int j = 0; j < m; j++){
        			arr[i][j] = nextLong();
        		}
        	}
        	return arr;
        }
 
        public boolean isSpaceChar(int c) {
            if (filter != null)
                return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }
 
        private boolean isEndOfLine(int c) {
            return c == '\n' || c == '\r' || c == -1;
        }
        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);
        }
	}
}