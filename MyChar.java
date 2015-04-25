public class MyChar extends Element {
	char value;			// Data
	
	MyChar(){			// Constructor
		value = '0';	// Initialize
	}
	
	public char Get(){	// returns data
		return value;
	}
	
	public void Set(char data){	// Set data
		value = data; 
	}
	
	public void Print() {	// Print Data
		// TODO Auto-generated method stub
		System.out.print("'"+value+"'");
	}

}
