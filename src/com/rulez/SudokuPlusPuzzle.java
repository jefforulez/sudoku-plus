
package com.rulez ;

import java.lang.Double ;
import java.lang.Integer ;
import java.lang.Math ;

import java.util.* ;

public class SudokuPlusPuzzle
{
	private Integer width ;
	private Integer sum ;
	private Integer boxWidth ;
	
	private boolean isValid ;
	private boolean isFirstRow ;

	private ArrayList< ArrayList<Integer> > rows ;
	private ArrayList< Set<Integer> >       columns ;
	private ArrayList< Set<Integer> >       boxes ;

	public SudokuPlusPuzzle()
	{
		isFirstRow = true ;
		
		rows = new ArrayList< ArrayList<Integer> >() ;
		columns = new ArrayList< Set<Integer> >() ;
		boxes = new ArrayList< Set<Integer> >() ;
	}
	
	public boolean isValidSolution() 
	{
		if ( isValid == true ) {
			return true ;
		}
		
		// check number of columns
		if ( columns.size() != this.width ) 
		{
			isValid = false ;
			return false ;
		}
		
		// check sums of columns
		for ( Set<Integer> column : columns )
		{
			int columnSum = 0 ;
			for ( Integer i : column ) 
			{
				columnSum += i ;
			}
			if ( columnSum != this.sum ) 
			{
				isValid = false ;
				return false ;
			}
		}
		
		// check sums of boxes
		for ( Set<Integer> box : boxes )
		{
			int boxSum = 0 ;
			for ( Integer i : box ) 
			{
				boxSum += i ;
			}
			if ( boxSum != this.sum ) 
			{
				isValid = false ;
				return false ;
			}
		}
		
		isValid = true ;
		return true ;
	}
	
	public void addRow( String[] values ) 
		throws SudokuPlusException
	{
		// invalidate the puzzle when a row is added
		isValid = false ;
	
		// use the first row to initialize the puzzle parameters
		if ( isFirstRow == true ) 
		{
			isFirstRow = false ;
			
			this.width    = new Integer( values.length ) ;
			this.sum     = new Integer( ( values.length * ( values.length + 1 ) ) / 2 ) ;

			double bs    = Math.sqrt( values.length ) ;
			this.boxWidth = new Integer( Double.valueOf( bs ).intValue() ) ;
			
			if ( Double.compare( bs, this.boxWidth.doubleValue() ) != 0 )
			{
				throw new SudokuPlusException( "! invalid first row, puzzle" ) ;
			}
		}
		
		// verify the row length
		if ( values.length != this.width )
		{
			throw new SudokuPlusException( "! invalid row length" ) ;
		}
		
		//
		// create an array of the values in the row, 
		// and compute the sum of the numbers in the row
		//
		Integer rowSum = 0 ;

		// used to detect dups in the row
		Set<Integer> rowSet = new HashSet<Integer>();
		
		// used to preserve order of the row
		ArrayList<Integer> rowArray = new ArrayList<Integer>() ;
		
		int columnIndex = 0 ;
		
		for ( String v : values )
		{
			Integer w ;
			
			try 
			{
				w = Integer.valueOf( v ) ;
			}
			catch ( NumberFormatException nfe ) 
			{
				throw new SudokuPlusException( "! invalid field value" ) ;
			}

			// add to the row and look for duplicate values
			if ( rowSet.add( w ) == false ) 
			{
				throw new SudokuPlusException( "! duplicate row value" ) ;
			}

			// add the value to the ordered row array
			rowArray.add( w ) ;

			// add value to the row sum
			rowSum += w ;
			
			// 
			// add value to the appropriate column
			//			
			if ( columns.size() < columnIndex + 1 ) {
				columns.add( new HashSet<Integer>() ) ;
			}
			Set<Integer> columnSet = columns.get( columnIndex ) ;
			
			if ( columnSet.add( w ) == false ) {
				throw new SudokuPlusException( "! duplicate column value" ) ;
			}
			
			//
			// add value to the appropriate box
			//
			int boxIndex = ( rowSet.size() - 1 ) / this.boxWidth 
			             + ( ( columnSet.size() - 1 ) / this.boxWidth ) * this.boxWidth
			             ;

			if ( boxes.size() < boxIndex + 1 ) {
				boxes.add( new HashSet<Integer>() ) ;
			}
			Set<Integer> boxSet = boxes.get( boxIndex ) ;
			
			if ( boxSet.add( w ) == false ) {
				throw new SudokuPlusException( "! duplicate box value" ) ;
			}

			++columnIndex ;
		}
		
		//
		// verify the sum of the row values
		//
		if ( this.sum.compareTo( rowSum ) != 0  )
		{
			throw new SudokuPlusException( "! invalid row sum" ) ;
		}
		
		//
		// make sure the row order isn't a duplicate of previous rows
		//
		if ( rows.add( rowArray ) == false )
		{
			throw new SudokuPlusException( "! invalid row" ) ;
		}		
	}
	
	public String toString()
	{
		StringBuffer sb = new StringBuffer() ;
		sb.append( "\nPUZZLE INFO\n" ) ;
		sb.append( " width: " ).append( this.width ).append( "\n" ) ;
		sb.append( " box width: " ).append( this.boxWidth ).append( "\n" ) ;
		sb.append( " expected sum: " ).append( this.sum ).append( "\n" ) ;
		sb.append( " rows: " ).append( rows ).append( "\n" ) ;
		sb.append( " columns: " ).append( columns ).append( "\n" ) ;		
		sb.append( " boxes: " ).append( boxes ).append( "\n" ) ;
		return sb.toString() ;
	}

}
