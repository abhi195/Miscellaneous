/*
 * Increase Recursion Stack Size
 */

public class IncreaseRecursionStack{
	
	public static void main(String args[]) {
        new Thread(null, new Runnable() {
            public void run() {
                try{
                    code();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }, "1", 1 << 26).start();
	}

    static void code(){
		
		//CODE
    	
    }
}