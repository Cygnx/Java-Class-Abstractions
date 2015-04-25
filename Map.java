public class Map extends Sequence{		// Map Class
	
	public void add(Pair p){			// Adds an object of type 'Pair' to sequence
		int i = 0;						// Indexer
		if(length() != 0)				// Do a check to see if there are any elements in the seq 
			for(i = 0; i < length() && p.key.Get() >= ((Pair)index(i)).key.Get(); i++){}// If there is elements in seq. Iterate through Seq. Stop when key > seq(i).key
		super.add(p, i);				// Add pair to this spot
	}
	
	public MapIterator begin(){
		MapIterator nwIterator = new MapIterator();					// make a new map iterator object
		nwIterator.currentSequence = super.begin().currentSequence;	// point iterator to beggining of seq
		return nwIterator;											// return iterator
	}
	
	public MapIterator end(){
		MapIterator nwIterator = new MapIterator();					// make a new iterator object
		nwIterator.currentSequence = super.end().currentSequence; 	// point iterator to last obj of seq
		return nwIterator;											// return iterator
	}
	public MapIterator find(MyChar key){							// returns an iterator to a pair that contains key, if none is found, return last pair of seq
		MapIterator it;												// New MapIterator Object
		for (it = begin(); !it.equal(end()); it.advance()){			// Iterate through Seq
			if(it.get().key.Get() == key.Get())						// If current key matches our input key
				return it;											// Return the iterator used to get to this key
		}
		return it;													// Return the iterator that points to the last pair in the seq 
	}
}
