Gabriel Pridham
N01383793
COP 3404
Project 1

Purpose:

	Creating a Hash table that is capable of storing a value and handles collision.
	For the purpose of having a symbol table to be used by the assembler

General Description:
		Compile: make
		Execute: ./p1 filename
		
		filename = user entered txt file.
 
		Reads in a text file with an expected format of a String followed by a value 
		or a string followed by nothing.
			
			EX: moss 25
			    moss
			    eno 12
		
		If the string is followed by a value, then hash the function, if string already exist
		in hash table, then do NOT override the value.
		
		If string is NOT followed by a value, then hash the function, if string already exist
		in hash table, then print out information.

		Data is first stored into an ArrayList, and the Hash table size is 3 x ArrayList.size() 
		which insures the Hash table is big enough to avoid to much collision. 

		Collision is dealt with by creating a linked list within each hash cell, therefore if 
		different strings hash to same location, they share the same hash cell and are linked 
		together.
		
		
	
	
