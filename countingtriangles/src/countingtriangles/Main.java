package countingtriangles;

import java.io.*; 
import java.util.*;
// Add any extra import statements you may need here


class Main {

  class Sides{
    int a;
    int b;
    int c;
    Sides(int a,int b,int c){
      this.a = a;
      this.b = b;
      this.c = c; 
    }
  }
  
  // Add any helper functions you may need here
  int getHash(Sides s)
  {
     return s.a*s.a + s.b*s.b +s.c*s.c;
  }
  boolean isEqual( Sides s1 , Sides s2)
  {
     if ( (s1.a == s2.a ) && (s1.b == s2.b ) && (s1.c == s2.c ) 
       || (s1.a == s2.a ) && (s1.b == s2.c ) && (s1.c == s2.b ) 
       || (s1.a == s2.b ) && (s1.b == s2.a ) && (s1.c == s2.c )
       || (s1.a == s2.b ) && (s1.b == s2.c ) && (s1.c == s2.a )
       || (s1.a == s2.c ) && (s1.b == s2.b ) && (s1.c == s2.a )
       || (s1.a == s2.c ) && (s1.b == s2.a ) && (s1.c == s2.b ) )
       return true;
    
    return false;
       
  }
  
  int countDistinctTriangles(ArrayList<Sides> arr) {
    // Write your code here
    Map<Integer, List<Sides>> map = new HashMap<>();
    for(Sides s : arr){
       int hash  = getHash(s);
       if(map.containsKey(hash))
       {
           List<Sides> sides = map.get(hash);
           boolean isCounted = false;
           for(Sides t : sides)
           {
              if(isEqual(s, t)){
                isCounted = true;
                 break;
               
              }
                
           }
         if(!isCounted)
           sides.add(s);
       }
       else 
       {
          List<Sides> sides = new ArrayList<>();
          sides.add(s);
         map.put(hash, sides);
         
       }
    }
    int numTriangles  = 0;
    for(Map.Entry<Integer, List<Sides>> me : map.entrySet())
    {
        numTriangles+=me.getValue().size();
    }
    return numTriangles;
  }













  // These are the tests we use to determine if the solution is correct.
  // You can add your own at the bottom, but they are otherwise not editable!
  int test_case_number = 1;
  
  void check(int expected, int output) {
    boolean result = (expected == output);
    char rightTick = '\u2713';
    char wrongTick = '\u2717';
    if (result) {
      System.out.println(rightTick + " Test #" + test_case_number);
    }
    else {
      System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
      printInteger(expected); 
      System.out.print(" Your output: ");
      printInteger(output);
      System.out.println();
    }
    test_case_number++;
  }
  
  void printInteger(int n) {
    System.out.print("[" + n + "]");
  }
  
  public void run() {
    ArrayList<Sides> arr_1 = new ArrayList<>();
    arr_1.add(new Sides(7, 6, 5));
    arr_1.add(new Sides(5, 7, 6));
    arr_1.add(new Sides(8, 2, 9));
    arr_1.add(new Sides(2, 3, 4));
    arr_1.add(new Sides(2, 4, 3));
    int expected_1 = 3;
    int output_1 = countDistinctTriangles(arr_1);
    check(expected_1, output_1);

    ArrayList<Sides> arr_2 = new ArrayList<>();
    arr_2.add(new Sides(3, 4, 5));
    arr_2.add(new Sides(8, 8, 9));
    arr_2.add(new Sides(7, 7, 7));
    int expected_2 = 3;
    int output_2 = countDistinctTriangles(arr_2);
    check(expected_2, output_2);
    
    // Add your own test cases here
    
  }
  
  public static void main(String[] args) {
    new Main().run();
  }
}