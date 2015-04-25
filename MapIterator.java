public class MapIterator extends SequenceIterator{
	Pair get(){												// gets current seq node content
		return (Pair)(currentSequence.first());				// Casts first() to a Pair object, return it
	}
}
