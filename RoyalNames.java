import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;


public class RoyalNumbers {
    private static final Map<Character, Integer> mapRank;
    static
    {
    	mapRank = new HashMap<Character, Integer>();
    	mapRank.put('I', 1);
    	mapRank.put('V', 2);
    	mapRank.put('X', 3);
    	mapRank.put('L', 4);
    	mapRank.put('C', 5);
    	mapRank.put('D', 6);
    	mapRank.put('M', 7);
    }
    
    private static final Map<Character, Integer> mapRoman;
    static
    {
    	mapRoman = new HashMap<Character, Integer>();
    	mapRoman.put('I', 1);
    	mapRoman.put('V', 5);
    	mapRoman.put('X', 10);
    	mapRoman.put('L', 50);
    	mapRoman.put('C', 100);
    	mapRoman.put('D', 500);
    	mapRoman.put('M', 1000);
    }

	static class Person{
		String name;
		int num;
		Person(String name, int num){
			this.name = name;
			this.num =num;
		}
	}
	
	public static String[] getSortedList(String[] names){
		if(names.length < 2) return names;
		Person[] persons = new Person[names.length];
		HashMap<Person, String> personToName = new HashMap<>();
		String[] res = new String[names.length];
		
		for(int i = 0; i < names.length; i++) {
			String[] name = names[i].split(" ");
			int num = romanToInt(name[1]);
			Person p = new Person(name[0], num);
			persons[i] = p;
			personToName.put(p, names[i]);
		}
		Arrays.sort(persons, new Comparator<Person>(){
			@Override
			public int compare(Person p1, Person p2){
				String s1 = p1.name;
				String s2 = p2.name;
				if(s1.compareTo(s2) != 0){
					return s1.compareTo(s2);
				}
				else{
					return p1.num - p2.num;
				}
			}
		});
		
		for(int i = 0; i < persons.length; i++){
			res[i] = personToName.get(persons[i]);
		}
		return res;
	}
	
	private static int romanToInt(String s){
		int result = 0;
		for(int i = 0; i < s.length() - 1; i++){
			char curr = s.charAt(i);
			char next = s.charAt(i + 1);
			int sign = (mapRank.get(curr) >= mapRank.get(next)) ? 1 : -1;
			result += sign * mapRoman.get(curr); 
		}
		result += mapRoman.get(s.charAt(s.length() - 1));
		return result;
	}
	
	public static void main(String[] args) {
		String[] names = { "A IX", "A VII", "A I", "A XX"};
		String[] result = getSortedList(names);
		for(String s: result){
			System.out.println(s);
		}
		String roman = "MCMLIV";
		System.out.println(romanToInt(roman));
	}
}
