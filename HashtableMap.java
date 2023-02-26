import java.util.NoSuchElementException;
import java.util.LinkedList;

// Implement a Load Factor Check
// Implement a helper method for extending the size of the HashTable
// Check in with TA to see if the constructor is implemented properly

public class HashtableMap <KeyType, ValueType> implements MapADT<KeyType, ValueType> {
    // Single array that stores value types
    // Uses linked list for chaining
    // Uses
    protected LinkedList<KeyValuePairs<KeyType,ValueType>>[] hashTable;

    // Tracks the number of valueTypes in the hashtable
    private int size = 0;

    /**
     * Creates a hash table that holds n number of key-value pairs
     * @param capacity the number of key value pairs the table will hold
     */
    @SuppressWarnings ("unchecked")
    public HashtableMap(int capacity) {
        // Creates a raw linked list array
        LinkedList[] hashTable = new LinkedList[capacity];
        // Casts this array to hold the generic ValueType
        this.hashTable = (LinkedList<KeyValuePairs<KeyType,ValueType>>[]) hashTable;

        // Adds in the chains for the hashTable
        for(int i = 0; i < capacity; i++) {
            this.hashTable[i] = new LinkedList<KeyValuePairs<KeyType,ValueType>>();
        }

    }

    /**
     * Creates a hashtable that can hold 20 key-value pairs
     */
    public HashtableMap() { // default capacity of 20
        this(20);
    }

    /**
     * Maps the provided valueType to a location in the hashTable, specified by the provided keyType
     *
     * @param key the location where the value will be stored
     * @param value what will be stored
     * @return true after successfully storing a new key-value pair and false if the key has been used or is null
     */
    @Override
    public boolean put(KeyType key, ValueType value) {
        // checks if the key is null
        if(key == null) {
            // Return false if it is
            return false;
        }

        // Check if the key already exists in the HashTable
        if(containsKey(key)) {
            // Return false if the key is already contained in the HashTable
            return false;
        }

        // gets the index
        int index = Math.abs(key.hashCode()) % hashTable.length;

        // adds the value into the list
        hashTable[index].add(new KeyValuePairs<KeyType, ValueType>(key, value));
        // increments the size
        size++;

        // Checks if the hashTable needs to be resized
        // Resizes when load factor is >= 75%
        double loadFactor = 1.0 * size/hashTable.length;
        if(loadFactor >= 0.75) {
            // Expands the hashtable's capacity
            resizeHashTable();
        }

        return true;
    }

    /**
     * Resizes the hashTable to double its capacity and copy over the the keyPairValues
     */
    @SuppressWarnings ("unchecked")
    private void resizeHashTable () {
        // Creates a new hashTable with double the capacity
        LinkedList[] tempHash = new LinkedList[hashTable.length * 2];
        // Casts this array to hold the generic ValueType
        LinkedList<KeyValuePairs<KeyType, ValueType>>[] newHashTable =
                (LinkedList<KeyValuePairs<KeyType,ValueType>>[]) tempHash;

        // Adds in the chains for the hashTable
        for(int i = 0; i < newHashTable.length; i++) {
            newHashTable[i] = new LinkedList<KeyValuePairs<KeyType,ValueType>>();
        }

        // Iterates through the entire hashTable and rehashes the keyPairValues to the new hashTable
        for(int i = 0; i < hashTable.length; i++) {
            for(KeyValuePairs<KeyType, ValueType> pair: hashTable[i]) {
                // finds the new key hash
                int index = Math.abs(pair.getKey().hashCode()) % newHashTable.length;
                // adds the value into the chain
                newHashTable[index].add(pair);
            }
        }
        hashTable = newHashTable;
    }

    /**
     * Returns the value that the key is mapped to
     * @param key the keyType that will be used to find the value
     * @return the value associated to the key
     * @throws NoSuchElementException if the value cannot be found
     */
    @Override
    public ValueType get(KeyType key) throws NoSuchElementException {
        // locates the chain that the key is mapped to
        int index = Math.abs(key.hashCode()) % hashTable.length;

        // Iterates through the chain
        for(KeyValuePairs<KeyType, ValueType> pair: hashTable[index]) {
            // Checks if the key from the pair equals the key provided
            if(pair.getKey().equals(key)) {
                return pair.getValue();
            }
        }

        throw new NoSuchElementException("Value associated to key cannot be found");
    }


    /**
     * Provides the number of elements stored in the hashTable
     * @return the number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Determines if the key provided has already been used in the HashTable
     * @param key the key that will be checked with
     * @return true if the key has been used, false otherwise
     */
    @Override
    public boolean containsKey(KeyType key) {
        // locates the chain that the key is mapped to
        int index = Math.abs(key.hashCode()) % hashTable.length;

        // Iterates through the chain
        for(KeyValuePairs<KeyType, ValueType> pair: hashTable[index]) {
            // Checks if the key from the pair equals the key provided
            if(pair.getKey().equals(key)) {
                return true;
            }
        }

        // If the loop has not found the key, return false
        return false;
    }

    /**
     * Removes the value in the HashTable that is associated with the provided key
     * @param key the key associated with a value in the HashTable
     * @return the removed value or null if the value was not found
     */
    @Override
    public ValueType remove(KeyType key) {
        // Find the index the key is associated with
        int index = Math.abs(key.hashCode()) % hashTable.length;

        // Search through the linked list and check for the provided key
        for(KeyValuePairs<KeyType, ValueType> pair : hashTable[index]) {
            if(pair.getKey().equals(key)) {
                // stores the found value
                ValueType found = pair.getValue();
                // remove the value from the chain
                hashTable[index].remove(pair);
                // return the value
                size--;
                return found;
            }
        }

        // if the loop exits without returning a value, the hashTable does not contain the key
        return null;
    }

    /**
     * Clears the keyValuePairs from the HashTable while keeping the capacity
     */
    @Override
    public void clear() {
        // iterates through the array and clears each linkedlist
        for(int i = 0; i < hashTable.length; i++) {
            hashTable[i].clear();
        }
        // sets size to 0
        size = 0;
    }
}
