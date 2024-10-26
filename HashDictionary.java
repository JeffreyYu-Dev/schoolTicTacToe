// hours spent: 1

import java.util.LinkedList;

public class HashDictionary implements DictionaryADT {

  // size of table
  private int size;
  private LinkedList<Data>[] table = null;

  public HashDictionary(int size) {

    // define size in our class
    this.size = size;

    // define the size of the array of type LinkedList<Data>
    this.table = new LinkedList[size];

    // intialize each spot with linkedLists
    for (int i = 0; i < size; i++) {

      table[i] = new LinkedList<>();
    }
  }

  // Todo: when putting in data when a collision happens use seperate chaining.

  // Must return 1 if there was a collision and 0 if no collions

  // there are like 3 cases here

  // case 1: return 0 if no collision
  // case 2: return 1 if collision
  // case 3: throw new dictionaryException if its already in there
  public int put(Data record) throws DictionaryException {

    // Turn the data config into hash
    String config = record.getConfiguration();
    int configKey = hash(config);

    // visualization of the table

    /* table = [
    <data1, data2, data3>,
    <data4, data5, data6>,
    <data7, data8, data9>
    ]

    each data will have config, and score;

    < > is a linked list

    */

    // check if an item is in the location already if there is add it and return
    // 1;

    // we also have to check if there things we are adding are the exact same
    // tho
    if (!table[configKey].isEmpty()) {
      // checks if the record is already in the linkedList
      for (int i = 0; i < table[configKey].size(); i++) {
        if (table[configKey].get(i).getConfiguration().equals(config)) {
          // throw an exception duh.
          throw new DictionaryException();
        }
      }
      // adding record to linked List(seperate chaining)
      table[configKey].add(record);
      return 1;
    }

    // we don't need this maybe cuz we know the list is not empty when we do the
    // check above

    // check if an item is there already if not add to table
    // if (table[configKey].isEmpty()) {
    // }

    // add record to the table
    table[configKey].add(record);
    return 0;
  }

  // Removes the record with the given config from the dictionary. Must throw a
  // DictionaryException if no record in the hash table stores config
  public void remove(String config) throws DictionaryException {

    // get key
    int configKey = hash(config);

    // grab the row from the table[configKey]
    LinkedList<Data> row = table[configKey];

    // loop through the linked list, grab each configuration from the data and
    // check if it's equal to the config key
    for (int i = 0; i < row.size(); i++) {

      if (row.get(i).getConfiguration().equals(config)) {
        row.remove(i);
        return;
      }
    }

    // throw exception if record doesn't exist in the table
    throw new DictionaryException();
  }

  // return the score from the given config string
  public int get(String config) {
    // get key
    int configKey = hash(config);

    // grab the row from the table[configKey]
    LinkedList<Data> row = table[configKey];

    // loop through the linked list, grab each configuration from the data and
    // check if it's equal to the config key then return the score if in table
    for (int i = 0; i < row.size(); i++) {
      if (row.get(i).getConfiguration().equals(config)) {
        return row.get(i).getScore();
      }
    }

    // return -1 when not in table
    return -1;
  }

  // THIS IS SLOW IF THE DICTIONARY IS SUPER LARGE (but we have no choice)
  // returns the number of records in the entire dictionary
  public int numRecords() {
    int totalObjects = 0;

    // LOOP THROUGH WHOLE TABLE  :( big sad
    for (int i = 0; i < this.size; i++) {

      // slightly optizmize the speed of the looping by skipping the index if
      // the linked list is empty
      if (table[i].isEmpty()) {
        continue;
      }

      // sum up the total number of objects in each linked list from each index
      totalObjects += table[i].size();
    }

    return totalObjects;
  }

  // The hashing function for turning strings into an integer used for our keys;
  private int hash(String config) {

    // the current value of the hash
    int currentHash = 0;

    // Todo: size has to be prime; size is the length of the table

    // Todo: I understand now. x can be any value but the 33,37,39, and 41 are
    // recommended as they help evenly distribute the values in the table

    // options for x {33, 37, 39, 41};
    // for some reason 10 is the best out of all of these options
    int x = 10;

    // loop through each character and convert to hash using the polynomial
    for (int i = 0; i < config.length(); i++) {
      currentHash = (currentHash * x + (int)config.charAt(i)) % this.size;
    }

    return currentHash;
  }

  // function was used for testing this class
  // public static void main(String[] args) {}
}
