class SequenceIterator {			// iterator class
	Sequence currentSequence;		// holds a seq obj
	SequenceIterator(){				// initialize seq to null
		currentSequence = null;
	}
	
	SequenceIterator advance(){		// advance to the next seq node
		currentSequence = currentSequence.getNext();	
		return this;
	}
	
	Element get(){					// gets current seq node content
		return currentSequence.first();
	}
	public boolean equal(SequenceIterator other){	// check if two iterators are pointing to the same thing
		return currentSequence == other.currentSequence;
	}
}
