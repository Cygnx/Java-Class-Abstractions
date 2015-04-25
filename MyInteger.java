public class MyInteger extends Element {
	int value;	// Data
	
	MyInteger(){	// Initialize Data
		value = 0;
	}
	
	public int Get(){	// Returns data
		return value;
	}
	
	public void Set(int data){	// Sets data
		value = data; 
	}
	
	@Override
	public void Print() {	// Prints data
		// TODO Auto-generated method stub
		System.out.print(Integer.toString(value));
	}
}
