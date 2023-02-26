/**
 * Stores the key and value
 * @param <KeyType> the key used to store this object into a hash table
 * @param <ValueType> the value being stored in the hash table
 */

public class KeyValuePairs <KeyType, ValueType> {
    private KeyType key;
    private ValueType value;

    public KeyValuePairs(KeyType key, ValueType value) {
        this.key = key;
        this.value = value;
    }

    public KeyType getKey() {
        return key;
    }

    public ValueType getValue() {
        return value;
    }
}