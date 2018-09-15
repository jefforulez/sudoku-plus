
//
// SUDOKU PLUS VALIDATOR
//
// by @jefforulez
//
// 2012.03.20
//

1. BUILD INSTRUCTIONS

> javac -d ./classes -classpath ./classes ./src/com/rulez/*.java

2. HOW TO USE

a. usage

  > java -classpath ./classes com.rulez.SudokuPlusMain
  usage: com.rulez.SudokuPlusValidator <filename>
  	<filename>: sudoku plus solution file

b. valid 4x4

  > java -classpath ./classes com.rulez.SudokuPlusMain ./data/sampleInput_4x4.txt
  + VALID: sudoku plus solution is correct

c. broken row

  > java -classpath ./classes com.rulez.SudokuPlusMain ./data/sampleInput_broken_row.txt
  ! duplicate row value
  - INVALID: sudoku plus solution is incorrect

  PUZZLE INFO
   width: 4
   box width: 2
   expected sum: 10
   rows: [[1, 4, 2, 3], [2, 3, 1, 4], [4, 2, 3, 1]]
   columns: [[1, 2, 3, 4], [1, 2, 3, 4], [1, 2, 3, 4], [1, 3, 4]]
   boxes: [[1, 2, 3, 4], [1, 2, 3, 4], [1, 2, 3, 4], [1, 3, 4]]

d. broken column

  > java -classpath ./classes com.rulez.SudokuPlusMain ./data/sampleInput_broken_column.txt
  ! duplicate column value
  - INVALID: sudoku plus solution is incorrect

  PUZZLE INFO
   width: 4
   box width: 2
   expected sum: 10
   rows: [[1, 4, 2, 3], [2, 3, 1, 4], [4, 2, 3, 1]]
   columns: [[1, 2, 4], [2, 3, 4], [1, 2, 3], [1, 3, 4]]
   boxes: [[1, 2, 3, 4], [1, 2, 3, 4], [2, 4], [1, 3]]

e. broken row length

  > java -classpath ./classes com.rulez.SudokuPlusMain ./data/sampleInput_broken_length.txt
  ! invalid row length
  - INVALID: sudoku plus solution is incorrect

  PUZZLE INFO
   width: 4
   box width: 2
   expected sum: 10
   rows: [[1, 4, 2, 3]]
   columns: [[1], [4], [2], [3]]
   boxes: [[1, 4], [2, 3]]


3. MANIFEST

a. compile_and_run.sh -- a simple shell script used during development for testing

b. sudoku_plus_validator.pl -- a PERL prototype used to quickly validate the validation algorithm

c. java source files

  > ls -1 ./src/com/rulez
  SudokuPlusException.java
  SudokuPlusMain.java
  SudokuPlusPuzzle.java
