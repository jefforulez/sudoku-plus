
package com.rulez ;

import java.io.BufferedReader ;
import java.io.FileReader ;

public class SudokuPlusMain
{
	private static SudokuPlusPuzzle puzzle ;

	static
	{
		puzzle = new SudokuPlusPuzzle() ;			
	}

	public static void main( String args[] ) 
	{
		if ( args.length == 0 ) 
		{
			usage() ;
			return ;
		}
		
		String filename = ( String )( args[0] ) ;
		
		if ( filename.isEmpty() == true ) 
		{
			usage() ;
			return ;		
		}

		try 
		{
			//
			// loop line by line and split on commas
			//
			BufferedReader br = new BufferedReader( new FileReader( filename ) ) ;
			String line ;
			
			while ( ( line = br.readLine() ) != null )
			{
				String[] tokens = line.split( "," ) ;
				
				try 
				{
					puzzle.addRow( tokens ) ;
				}
				catch ( SudokuPlusException spe )
				{
					System.err.println( spe.getMessage() ) ;
					break ;
				}
			}
			
			if ( puzzle.isValidSolution() == true )
			{
				System.out.println( "+ VALID: sudoku plus solution is correct" ) ;
			}
			else
			{
				System.out.println( "- INVALID: sudoku plus solution is incorrect" ) ;
				System.out.println( puzzle ) ;
			}

		}
		catch ( Exception e )
		{
			System.out.println( e.getMessage() ) ;
			e.printStackTrace( System.out ) ;
			return ;
		}
		
		return ;
	}
	
	private static void usage()
	{	
		System.err.println( "usage: com.rulez.SudokuPlusValidator <filename>" ) ;
		System.err.println( "     <filename>: sudoku plus solution file" ) ;
		return ;
	}
	
	
}


