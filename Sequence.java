public class Sequence extends Element {
	Element content;			// Holds the data of current node
	Sequence next;				// Pointer to next node
	Sequence padding; 			// Padding added to the end of sequence to indicate end
	
	Sequence(){					// Constructor
		content = null;			// Initialize 
		next = null;			// Initialize
	}
	
	SequenceIterator begin(){
		if(padding == null){			// if padding is not yet initialized
			padding = new Sequence();	// Initialize Padding
			add(padding, length());		// add padding to the end of seq, (it will not be counted since it has a content value of null)
		}
		
		SequenceIterator nwIterator = new SequenceIterator();	// make a new iterator object
		nwIterator.currentSequence = this; 						// point iterator to head
		return nwIterator;										// return iterator
	}
	
	SequenceIterator end(){										// end iterator
		SequenceIterator nwIterator = new SequenceIterator();	// make a new iterator object
		nwIterator.currentSequence = getSeqAtPos(length() - 1); // point iterator to last seq
		return nwIterator;										// return iterator
	}
	
	public Sequence flatten(){				// Flattens out a sequence
		Sequence nwSeq = new Sequence();	// new temp Seq
		
		if ( first() instanceof Sequence )	// If current node is a sequence
			nwSeq = ((Sequence)first()).flatten();	// Flatten current Node			
		else									// Base Case, if current node is NOT a seq
			nwSeq.content = content;			// Set Current Node to the content of the current seq
		
		if(rest() != null){						// If there are any more nodes after the current
			if( first() instanceof Sequence)    // If current node was a Seq
				nwSeq.getSeqAtPos(nwSeq.length() - 1).next = rest().flatten();	// Flatten the rest, append it to the end of the flatten sequence
			else
				nwSeq.next = rest().flatten();	// Flatten the rest, append it to the end of the current node
		}	
		
		return nwSeq;
	}
	
	public Sequence copy(){							// Return a deep copy of the entire list
		Sequence nwSeq = new Sequence();			//	New temp sequence
		for(int i = 0; i < length(); i++){			// Iterate through the list 
			if( index(i) instanceof Sequence ){ 	// If current element is a sequence
				nwSeq.add( ((Sequence)index(i)).copy(),nwSeq.length());	// recursively call copy on this sequence, and add it to the end of the new temp seq list
			}
			else{									// If current element is not a sequence
				if(index(i) instanceof MyChar){		// if its a MyChar
					MyChar data = new MyChar();		// Make a MyChar object
					data.Set( ((MyChar)index(i)).Get() );	// Fill out temp object with content from current element
					nwSeq.add(data, nwSeq.length());		// Add it to new temp seq list
				} 			
				if(index(i) instanceof MyInteger){		// If current element is a MyInteger 
					MyInteger data = new MyInteger();	// Make a new MyInteger Object
					data.Set( ((MyInteger)index(i)).Get() );	// Fill out temp object with content from current element
					nwSeq.add(data, nwSeq.length());	// Add it to new temp seq list
				} 
			}
		}
		return nwSeq; 								// return the new seq list
	}
	
	public Element index(int pos){				// returns the element at pos 
		if( isValidPos(pos) )					// validate pos
			return getSeqAtPos(pos).first();	//	Access seq at pos, returns the element at this seq pos
		else{
			System.err.print("Accessing invalid position");
			return null;
		}
	}
	
	public Element first(){		// Returns first element of series
		return content;	
	}
	
	public Sequence rest(){		// Returns the rest of the series
		return next;
	}
	
	public int length(){		// Loops through the sequence until we hit the end. Return the number of traversals
		Sequence cur = this; 	// start at the current series
		int i = 0;				// Counter starting at 0
		while(cur != null){		// While cur is still valid
			if(cur.content != null)		// if cur's content is not null
				i++;					// increment i counter
			cur = cur.getNext();		// traverse to cur's next
		}
		return i;						// return our counter
	}

	public void add(Element e, int pos){	// Adds an element e to our sequence at position pos
		Sequence nw = new Sequence();		// new temporary sequence
		Sequence seq = getSeqAtPos(pos);	// Gets sequence located at pos
		
		if(pos == length()){				// Special case handling for if pos == length
			if (pos == 0)					// If no elements in seq
				content = e;				// Set the current content to e
			else{							// If there is already a seq
				nw.setContent(e);			// Initialize the temporary Seq
				nw.setNext(null);			// Set the Temp Seq's next to null
				getSeqAtPos(pos - 1).setNext(nw);	// Connect seq to new temp node
			}
		}
		else								// Else if we're adding to the middle of a seq
		{
			if(isValidPos(pos)){			// Make sure that pos is valid
				nw.setContent(seq.first());	// Copy contents of the node at pos to our temp node
				nw.setNext(seq.getNext());	 
				seq.setContent(e);			// Change the contents of pos node to e
				seq.setNext(nw);			// Point pos node to our temp node
			}
			else
				System.err.println("Invalid position");	// Error accessing pos node
		}
	}
	
	public void delete(int pos){					// Deletes node at current pos
		if(isValidPos(pos))							// Validate pos 
		{		
			Sequence seq = getSeqAtPos(pos);		// Get node at pos
			if(seq.next != null){					// If there is still nodes to the right of pos
				seq.setContent(seq.next.first());	//	Copy the contents of pos node.next's into pos node 
				seq.setNext(seq.getNext().getNext());// Set pos node's next to pos node.next.next
			}
			else									// If pos node has no next
			{
				if(pos == 0){						// if pos node is the only node in the seq
					content = null;					// Null
					next = null;					// Null
				}
				else	
					getSeqAtPos(pos - 1).setNext(null);	// Set the node before pos node to have a next of null 
			}
		}
		else 
			System.err.println("Invalid position");
		
	}
	
	public Sequence getSeqAtPos(int pos){			// returns the node at position pos
		if(isValidPos(pos)){						// validates the pos
			Sequence cur = this; 					// iterate 
			for(int i = 0; i < pos; i++){ cur = cur.next;};	// iterate
			return cur;								// return iteration
		}
		else 										// couldn't find it
			return null;							// return nothing
	}
	
	public boolean isValidPos(int pos){					// Check if pos is a valid position in our list
			return (pos >= 0 && pos < length());		// Makes sure that pos is within 0 to length of seq
	}
	
	public void Print() {	
		System.out.print("[");							// Formating print
		for(int i = 0; i < length(); i++){				// iterates through list
			System.out.print(" ");						// Formatting
			getSeqAtPos(i).first().Print();				// prints current element
		} 					
		System.out.print(" ]");							// formating print
	}
	
	public Sequence getNext(){return next;};					// returns next node
	public void setContent(Element input){content = input;};	// Sets content of current node
	public void setNext(Sequence input){next = input;};			// Sets next node
}
