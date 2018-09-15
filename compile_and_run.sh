#!/bin/bash

javac -d ./classes -classpath ./classes ./src/com/rulez/*.java 

java -classpath ./classes com.rulez.SudokuPlusMain ./data/sampleInput_4x4.txt

java -classpath ./classes com.rulez.SudokuPlusMain ./data/sampleInput_9x9.txt

java -classpath ./classes com.rulez.SudokuPlusMain ./data/sampleInput_broken_row.txt

java -classpath ./classes com.rulez.SudokuPlusMain ./data/sampleInput_broken_column.txt
