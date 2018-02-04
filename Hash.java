import java.util.*;
import java.io.*;

/*
		

*/

public class Hash
{
	public static void main( String[] args ) throws FileNotFoundException
	{
		
		File file = new File(args[0]);
		Scanner input = new Scanner( file ); // reads file name from console
		String delims = "[ ]+"; // used to parsed data
		
		ArrayList<DataLink> tempList = new ArrayList<DataLink>(); 
			
		int value = 0;
		while( input.hasNextLine())
		{
			String dataString = input.nextLine(); // gets line
			String[] parsedString = dataString.split(delims); // splits the key string and following value
			String key = parsedString[0]; // stores key
			
			// if the key has a value following, then add
			// else -1 means there is no value
			if(parsedString.length > 1)
				value = Integer.parseInt(parsedString[1]);
			else 
				value = -1;
		
			tempList.add(new DataLink(key, value));
		}					
		
		HashTable hashTable = new HashTable( 3 * tempList.size()); // creates hashtable 3 times the number of elements
		DataLink link;
		// prints out user enter data.. FROM FILE.. 
		for(int index = 0; index < tempList.size(); index++)
		{
			link = tempList.get(index);
			int location = hashTable.hashFunc(link.getKey());
			
			if( link.getValue() != -1  ) // if attempting to insert new data
			{
				if ( hashTable.find( link.getKey() )!= null ) // if 
					System.out.println("ERROR " + link.getKey() + " " + "already exists at location " + location + " with value: " + hashTable.find( link.getKey() ).getValue());
				else
					hashTable.insert(tempList.get(index));
			}
			else // if trying to search for data
			{
				if( hashTable.find( link.getKey() ) != null ) // found data inof
					System.out.println( link.getKey() + " stored at location " + location + " with value: " + hashTable.find( link.getKey()).getValue());
				else  // if no data exsist
					System.out.println("ERROR " + link.getKey() + " not found" );
			}
	
			//tempList.get(index).display(); //for debugging
		}
		
		
	}
	
	
}


class DataLink
{
	private String m_searchKey;
	private int m_value;
	private DataLink m_next;
	
	public DataLink(String searchKey, int value)
	{
		m_searchKey = searchKey;
		m_value = value;
		m_next = null;
	}
	
	public DataLink(String searchKey)
	{
		m_searchKey = searchKey;
		m_next = null;
	}
	
	public String getKey()
	{
		//gets user key
		return m_searchKey;
	}
	
	public int getValue()
	{
		return m_value;
	}
	
	public void setNext(DataLink next)
	{
		m_next = next;
	}
	
	public DataLink getNext()
	{
		return m_next;
	}	
	
	public void display()
	{
		System.out.println(m_searchKey + " " +  m_value + " " );
	}
	
} // end of DataLink




class DataLinkList
{
	private DataLink first;

	
	public DataLinkList()
	{
		first = null;
	}
	
	public DataLink getFirst()
	{
		return first;
	}
	
	/*
		Returns DataLink if found
		or null if not
	*/

	public DataLink find(String key) 
	{
		DataLink current = first;
		
		// loop until the key matches with a current node
		// or until there are no more nodes left meaning
		// the key is not found
		while( current != null  ) 
		{
			// return DataLink if key strings are same
			//System.out.println("Comparing: " + current.getKey() + " TO: " + key); // for debugging
			if(current.getKey().compareTo(key) == 0)
			{
				//System.out.println("THis is a dupe");
				//current.setDupe();
				return current;
			}
			current = current.getNext();
		}
		
		return null; // link not found
	}
	
	public void insert( DataLink link)
	{
		if( first == null)
			first = link;
		else // collision
		{
			link.setNext( first );
			first = link;
		}
		
		//first.display();
	}
	
	public void display()
	{
		DataLink current = first;
		
		while(current != null)
		{
			current.display();
			current = current.getNext();
		}
	}

}

class HashTable
{
	private DataLinkList[] hashArray;
	private int m_size;
	
	public HashTable(int size)
	{
		m_size = size;
		hashArray = new DataLinkList[m_size];	
		
		// fills the array
		for(int index = 0; index < m_size; index++)
		{
			hashArray[index] = new DataLinkList();
		}
		
	}
	
	// returns DataLink if key found,
	// return null if not found
	public DataLink find(String key)
	{
		int location = hashFunc( key );
		
		return hashArray[location].find( key );
		
			//System.out.println("ERROR " + key.getKey() + " " + "already exists and location " + location);
		
		// return location of value
		// or return location not found
		// if end of array is reached, wrap aroud to beginning using %
	}
	
	// checks if there is a node already at hash locations
	public boolean collision(int location)
	{
		return hashArray[location].getFirst() != null;
	}
	
	public void insert(DataLink link)
	{
		int location = hashFunc( link.getKey() );
		
		if( collision( location ) )
		{
			System.out.println("COLLISION DETECTED, " + link.getKey() + " is sharing hash address: " +  location + " with: ");
			hashArray[location].display();
		}
			
		hashArray[location].insert( link );
		System.out.println("Inserted: " + link.getKey() + " into hash table at location  " + location + " with value: " + link.getValue());
		
		
		/*
		// if Dulicate key
		if( find( link.getKey() ) != null )
		{
			if( link.getValue() > -1 ) // has value
				System.out.println("ERROR " + link.getKey() + " " + "already exists at location " + location + " with value: " + find( link.getKey() ).getValue());
			else
				System.out.println( link.getKey() + " stored at location " + location + " with value: " + find( link.getKey()).getValue());
		}
		else // if key not found
		{
			if( link.getValue() == -1 ) // user didnt enter in value for key
				System.out.println("ERROR " + link.getKey() + " not found" );
			else
			{
				hashArray[location].insert( link );
				System.out.println("Inserted: " + link.getKey() + " into hash table at location  " + location + " with value: " + link.getValue());
			}
		}
		*/
	}
	
	// NOT USING
	/*
	public boolean duplicate(DataLink link)
	{
		if( find( link.getKey()) != null && link.getValue() != -1 )
			return true;
		else 
			return false;
	}
	
	public void displayMoss()
	{
		hashArray[46].display();
	}
	*/
	// hash search key 
	// return index value
	
	public int hashFunc(String key)
	{
		int location = 0;
		
		for(int index = 0; index < key.length(); index++)
		{
			int letter = key.charAt(index) - 96; // gets character code
			location  = (location * 27 + letter) % m_size;
		}
		
		return location;
	}
	
}

