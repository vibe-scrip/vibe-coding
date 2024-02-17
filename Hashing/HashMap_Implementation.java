package Hashing;

import java.util.*;

public class HashMap_Implementation{
         static class HashMap<K,V>{    // static nested class with generics class with two type params
            private class Node{        // public class, this represents node in HashMap
                 K key;                // variable key of type K   
                 V value;             // variable value of type V
                 public Node(K key, V value){   // constructor for a node class that takes param key and value   
                       this.key = key;         // assigns the value of 'key' param to the 'key' variable of node object
                       this.value = value;     // assigns the value of 'value' param to the 'value' variable of node object
                 }
            }
         

   @SuppressWarnings("unchecked")      
   private int n;                 // nodes
   private int N;                // buckets
   private LinkedList<Node> buckets[];

   public HashMap(){
       this.N = 4;
       this.buckets = new LinkedList[4];
         for(int i=0;i<4;i++){
            this.buckets[i] = new LinkedList<>();
         }
   }

   private int hashFunction(K key){
           int bi = key.hashCode();
           return Math.abs(bi) % N;   
        }

   private int searchInLL(K key, int bi){
           LinkedList<Node> ll = buckets[bi];
           for(int i =0 ;i < ll.size(); i++)
           {
             if(ll.get(i).key == key){
                  return i;
             }
           }
           return -1;
   }     

   @SuppressWarnings("unchecked")
   private void rehash(){
          LinkedList<Node> oldBucket[] = buckets;
          buckets = new LinkedList[N * 2];
          for(int i = 0 ; i < N*2 ; i++){
               buckets[i] = new LinkedList<>();
          } 
         
          for(int i = 0 ; i < oldBucket.length ; i++){
                LinkedList<Node> ll = oldBucket[i];
                for(int j = 0 ; j < ll.size() ; j++){
                        Node node = ll.get(j);
                        put(node.key, node.value);
                }
          }
   }

   public void put(K key, V value){
        int bi = hashFunction(key);
        int di = searchInLL(key,bi);
        if(di == -1){
             buckets[bi].add(new Node(key, value));
             n++;
        }
        else{
            Node node = buckets[bi].get(di);
            node.value = value;
        }

        double lambda = (double)n/N;
        
        if(lambda > 2.0){
            rehash();
        }

   }

   public V get(K key){
    int bi = hashFunction(key);
    int di = searchInLL(key,bi);
    if(di == -1){
         return null;
    }
    else{
        Node node = buckets[bi].get(di);
        return node.value;
    }
   }

   public ArrayList<K> keySet(){
         ArrayList<K> keys = new ArrayList<>();
         for(int i = 0 ; i < buckets.length ; i++){
            LinkedList<Node> ll = buckets[i];
            for(int j = 0 ; j < ll.size() ; j++){
                Node node = ll.get(j);
                keys.add(node.key);
            }
         }
         return keys;
   }

   public V remove(K key){
     int bi = hashFunction(key);
     int di = searchInLL(key,bi);
     if(di == -1){
          return null;
     }
     else{
         Node node = buckets[bi].remove(di);
         n--;
         return node.value;
     }
   }

   public boolean containsKey(K key){
     int bi = hashFunction(key);
     int di = searchInLL(key,bi);
     if(di == -1){
          return false;
     }
     else{
          return true;
     }  
   }

   public boolean isEmpty(){
       return n == 0;
   }

}



public static void main(String[] args){
        HashMap<Integer,String> map = new HashMap<>();
        map.put(1,"India");
        map.put(2, "Afghanistan");
        map.put(5,"Russia");
        map.put(3,"Mexico");
        map.put(4,"Pakistan");

        ArrayList<Integer> keys = map.keySet();
        for(int i = 0; i < keys.size() ; i++){
               System.out.println(keys.get(i) + " " + map.get(keys.get(i)));   
        }

        map.remove(3);
        System.out.println(map.get(3));

        for(int i = 0; i < keys.size() ; i++){
          System.out.println(keys.get(i) + " " + map.get(keys.get(i)));   
   }

        System.out.println(map.isEmpty());

        if(map.containsKey(3)){
               System.out.println("true");
        }
        else{
          System.out.println("false");
        }

        if(map.containsKey(2)){
          System.out.println("true");
        }
        else{
          System.out.println("false");
        }

         System.out.println(map.hashCode());

         for(int key : map.keySet()){
          System.out.println(key);
         }
          

}
}