import java.util.ArrayList;
import java.util.List;

public class HashTableSortedSets<KeyType, ValueType extends Comparable<ValueType>> extends
    HashtableMap<KeyType, List<ValueType>> implements IHashTableSortedSets<KeyType, ValueType> {
  /**
   * This add method is different from the put() method in that it appends a single value to the end
   * of the list associated with a given key. If a key does not yet have a list of values associated
   * with it, then a new one will be created when this method is called.
   * 
   * @param key   used to later lookup the list containing this value
   * @param value associated with the previous key
   * @throws IllegalArgumentException if either key or value are null
   */
  @Override
  public void add(KeyType key, ValueType value) {
    // Checks to see if the arguments provided are valid
    if (key == null || value == null) {
      throw new IllegalArgumentException("Provided key or value was null");
    }
    // Get the index
    int index = Math.abs(key.hashCode()) % hashTable.length;
    // Check if index already exists in hashTable
    if (containsKey(key)) {
      // If key does exist, get the key value pair and add it to the list
      for (KeyValuePairs<KeyType, List<ValueType>> pair : hashTable[index]) {
        if (pair.getKey().equals(key)) {
          // Add value to the list
          pair.getValue().add(value);
        }
      }
    } else {
      // If not, create a new key value pair with the value and add it to the linked list
      // Create an empty arraylist before upcasting to a generic list
      // Gets around instantiating a list interface without having to implement its methods
      List<ValueType> newList = new ArrayList<ValueType>();
      // adds the value to the list
      newList.add(value);
      // Creates a keyValuePair and stores it into the HashTable, inherited from HashTableMaps.java
      put(key, (List<ValueType>) newList);
    }
  }
}
