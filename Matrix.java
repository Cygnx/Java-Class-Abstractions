class Matrix extends Sequence{							// Matrix class will extend the Seq class
	int rowSize, colSize;								// Will hold the row and col size of the current matrix
	
	public Matrix(int r, int c) {
		rowSize = r;									// Save the current rowSize of the matrix
		colSize = c;									// Save the current colSize of the matrix
					
		for(int i = 0 ; i < rowSize * colSize; i++) {	// Iterate through the matrix
			MyInteger nwInt = new MyInteger();			// Allocate a new MyInteger Object for each iteration through the matrix
			super.add(nwInt, 0);						// Assign it a value of 0
		}
	}
	
	void Set(int r, int c, int value){  // set the value of an element
		intAtRowCol(r,c).Set(value);	// Gets the MyInt Object at Row r and Column c, sets the value to value 
	}
	
	int Get(int r, int c){ // get the value of an element
		return intAtRowCol(r,c).Get();	// Gets the MyInt Object at Row r and Column c, return the object's value
	}
	
	Matrix Sum(Matrix mat){ // return the sum of two matrices: mat & this
		Matrix nwSum = new Matrix(rowSize, colSize);	// Create a new Matrix object
		for(int i = 0 ; i < rowSize; i ++)				// Iterate through rows
			for(int j = 0 ; j < colSize; j++ ){			// Iterate through columns
				int val = mat.Get(i, j) + Get(i,j);		// val = this[i,j] + mat[i,j]
				nwSum.Set(i, j, val);					// Set the new Matrix[i,j] to val
			}
		return nwSum;									// Return the new Matrix 
	}
	
	Matrix Product(Matrix mat){ // return the product of two matrices: mat & this
		if(colSize != mat.rowSize){						// If there is a dimension error
			System.out.println("Matrix dimensions incompatible for Product");	// Error
			System.exit(0);								// Exit
		}
		
		Matrix nwProd = new Matrix(rowSize, mat.colSize);// Make a newMatrix of size [this.rowsize,mat.colSize]
		
		for(int i = 0 ; i < rowSize; i ++)				//	Iterate through rows
			for(int j = 0 ; j < mat.colSize; j++ ){		// Iterate through mat.cols
				int totalVal = 0 ;						// Temporary Sum for current new cell
				for(int k = 0; k < colSize; k++)		// Iterate through cols 
					totalVal += Get(i,k) * mat.Get(k, j);// Add this[i,k] * mat[k,j] to total val	
				nwProd.Set(i, j, totalVal);				// Assign total val to [i,j] of newMatrix
			}	
		return nwProd;									// Return new Matrix	
	}
	
	public void Print(){ 								// print the elements of matrix
		for(int i = 0 ; i < rowSize; i ++){				// iterate through rows
			System.out.print("[");						// formatting 
			for(int j = 0 ; j < colSize; j++ ){			// iterate through columns
				System.out.print(" ");					// formatting
				intAtRowCol(i,j).Print();				// print out cell 
			}
			System.out.print(" ]\n");					// formatting
		}
	}
	
	public int rowColToIndex(int r, int c){ 			// transforms a 2d indexing to a 1d indexing
		return (r * colSize) + c;						
	}
	
	public MyInteger intAtRowCol(int r, int c){			// Returns the MyInteger Object located at row r and column c
		Element elm = index( rowColToIndex(r, c) );		// Returns the element at r,c
		return ((MyInteger)elm);						// cast it to a MyInteger Obh and return 
	}
}
