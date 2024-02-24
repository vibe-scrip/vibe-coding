package Hashing;

import java.util.HashMap;

public class Itinerary {
      public static void main(String[] args){
          HashMap<String,String> tickets = new HashMap<>();
          tickets.put("Chennai","Bangaluru");
          tickets.put("Mumbai", "Delhi");
          tickets.put("Delhi", "Goa");
          tickets.put("Goa", "Chennai");

          String start = getStart(tickets);  

          while(tickets.containsKey(start)){   
               System.out.print(start + " -> ");
               start = tickets.get(start);
          }

          System.err.println(start);
          
      }

      public static String getStart(HashMap<String,String> tickets){
            HashMap<String,String> revMap = new HashMap<>(); // reverse the tickets HashMap

            for(String key : tickets.keySet()){   // tick.keySet() returns set of keys
                  revMap.put(tickets.get(key), key);  // assign value to key and key to value
            }

            for(String key : tickets.keySet()){   
                if(!revMap.containsKey(key)){   // if revMap doesn't contain key 
                    return key;                //  return that key
                }
            }
            return null;     // else return null 
      }
}
