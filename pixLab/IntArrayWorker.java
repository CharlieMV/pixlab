public class IntArrayWorker
{
  /** two dimensional matrix */
  private int[][] matrix = null;
  
  /** set the matrix to the passed one
    * @param theMatrix the one to use
    */
  public void setMatrix(int[][] theMatrix)
  {
    matrix = theMatrix;
  }
  
  /**
   * Method that returns the number of times a value is found in matrix
   * @param	int	number to check for
   * @return int number of times number is found
   */
  public int getCount(int target) {
	int count = 0;
	for (int i = 0; i < matrix.length; i++)
		for (int j = 0; j < matrix[i].length; j++)
			if (matrix[i][j] == target)
				count ++;
	return count;
  }
  
  /**
   * Method that returns largest number in matrix
   * @return int largest num
   */
  public int getLargest() {
	  int largest = matrix[0][0];
	  for (int i = 0; i < matrix.length; i++)
		for (int j = 0; j < matrix[i].length; j++)
			if (matrix[i][j] > largest)
				largest = matrix[i][j];
	  return largest;
  }
  /**
   * Returns total of all integers in specified column
   * @param int col
   * @return int sum
   */
  public int getColTotal(int col) {
	  int sum = 0;
	  for (int i = 0; i < matrix.length; i++)
		sum += matrix[i][col];
	  return sum;
  }
  
  /**
   * Method to return the total 
   * @return the total of the values in the array
   */
  public int getTotal()
  {
    int total = 0;
    for (int row = 0; row < matrix.length; row++)
    {
      for (int col = 0; col < matrix[0].length; col++)
      {
        total = total + matrix[row][col];
      }
    }
    return total;
  }
  
  /**Method to reverse the array */
  public void reverseRows() {
	  for (int i = 0; i < matrix.length; i++)
		for (int j = 0; j < matrix[i].length / 2; j++) {
			int temp = matrix[i][j];
			matrix[i][j] = matrix[i][matrix[i].length - 1 - j];
			matrix[i][matrix[i].length - 1 -j] = temp;
		}
  }
  
  /**
   * Method to return the total using a nested for-each loop
   * @return the total of the values in the array
   */
  public int getTotalNested()
  {
    int total = 0;
    for (int[] rowArray : matrix)
    {
      for (int item : rowArray)
      {
        total = total + item;
      }
    }
    return total;
  }
  
  /**
   * Method to fill with an increasing count
   */
  public void fillCount()
  {
    int numCols = matrix[0].length;
    int count = 1;
    for (int row = 0; row < matrix.length; row++)
    {
      for (int col = 0; col < numCols; col++)
      {
        matrix[row][col] = count;
        count++;
      }
    }
  }
  
  /**
   * print the values in the array in rows and columns
   */
  public void print()
  {
    for (int row = 0; row < matrix.length; row++)
    {
      for (int col = 0; col < matrix[0].length; col++)
      {
        System.out.print( matrix[row][col] + " " );
      }
      System.out.println();
    }
    System.out.println();
  }
  
  
  /** 
   * fill the array with a pattern
   */
  public void fillPattern1()
  {
    for (int row = 0; row < matrix.length; row++)
    {
      for (int col = 0; col < matrix[0].length; 
           col++)
      {
        if (row < col)
          matrix[row][col] = 1;
        else if (row == col)
          matrix[row][col] = 2;
        else
          matrix[row][col] = 3;
      }
    }
  }
 
}
