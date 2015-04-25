
public class Pair extends Element{
	MyChar key;
	Element val;
	
	public Pair(MyChar key, Element value){
		this.key = key;
		val = value;
	}
	
	public void Print(){
		System.out.print("(");
		key.Print();
		System.out.print(" ");
		val.Print();
		System.out.print(")");
	};
}
