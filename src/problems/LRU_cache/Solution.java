import java.util.*;

class LRUCache {

    private class Node {
        int key;
        int value;
        Node next;
        Node prev;
        Node(int key) { this.key = key; }
    }

    private int capacity;
    private Node lastUsedKey;
    private Node mostRecentlyUsedKey;
    private Map<Integer, Node> keyToNode;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        keyToNode = new HashMap<>();
    }

    public int get(int key) {
        // Make sure the key is a valid key before updating and retrieving.
        if(!keyToNode.containsKey(key)) return -1;
        // Get the key node for the key.
        Node keyNode = keyToNode.get(key);
        // Remove the key node from the list, and add it back in at the end.
        removeKeyNode(keyNode);
        addKeyNode(keyNode);
        return keyNode.value;
    }   
    
    public void put(int key, int value) {
        // Get the key node if it already exists. Otherwise, create a new key node.
        Node updatedKeyNode = keyToNode.getOrDefault(key, new Node(key));
        updatedKeyNode.value = value;
        // Move the updated key node to the back of the list.
        if(keyToNode.containsKey(updatedKeyNode.key)) removeKeyNode(updatedKeyNode);
        addKeyNode(updatedKeyNode);
        // If the cache is now over capacity, then remove the last used key node.
        if(keyToNode.size() > capacity) removeKeyNode(lastUsedKey);
    }

    private void removeKeyNode(Node keyNode) {
        // Update list end pointers if needed.
        if(mostRecentlyUsedKey == keyNode) mostRecentlyUsedKey = keyNode.prev;
        if(lastUsedKey == keyNode) lastUsedKey = keyNode.next;
        // Update the key node's neighbors.
        if(keyNode.prev != null) keyNode.prev.next = keyNode.next;
        if(keyNode.next != null) keyNode.next.prev = keyNode.prev;
        keyNode.prev = null;
        keyNode.next = null;
        // Remove key node from the node map.
        keyToNode.remove(keyNode.key);
    }

    private void addKeyNode(Node keyNode) {
        // Update list end pointers if needed.
        if(lastUsedKey == null) lastUsedKey = keyNode;
        if(mostRecentlyUsedKey != null) mostRecentlyUsedKey.next = keyNode;
        // Add the key node to the end of the list.
        keyNode.prev = mostRecentlyUsedKey;
        mostRecentlyUsedKey = keyNode;
        // Add the key node to the node map.
        keyToNode.put(keyNode.key, keyNode);
    }

}
