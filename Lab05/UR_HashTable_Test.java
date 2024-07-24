// Zhenhao Zhang zzh133@u.rochester.edu


public class UR_HashTable_Test {
    public static void main(String[] args) {
        UR_HashTable<String, Integer> hashTable = new UR_HashTable<>();

        // Test put and get
        hashTable.put("one", 1);
        hashTable.put("two", 2);
        hashTable.put("three", 3);
        assert (hashTable.get("one") == 1);
        assert (hashTable.get("two") == 2);
        assert (hashTable.get("three") == 3);
        System.out.println("Put and Get tests passed!");

        // Test update value
        hashTable.put("one", 11);
        assert (hashTable.get("one") == 11);
        System.out.println("Update value test passed!");

        // Test delete
        hashTable.delete("two");
        assert (hashTable.get("two") == null);
        System.out.println("Delete test passed!");

        // Test contains
        assert (hashTable.contains("three"));
        assert (!hashTable.contains("two"));
        System.out.println("Contains test passed!");

        // Test resize by inserting more items
        for (int i = 0; i < 10; i++) {
            hashTable.put("key" + i, i);
        }
        assert (hashTable.get("key5") == 5);
        System.out.println("Resize test passed!");

        // Test the iterator
        int count = 0;
        for (String key : hashTable) {
            count++;
        }
        assert (count == 12);
        System.out.println("All tests passed!");
    }
}
