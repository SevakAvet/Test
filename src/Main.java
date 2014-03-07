
import java.util.*;public class Main
{
	public static void main(String[] args)
	{
		List<String> l = Arrays.asList("123", "124", "125");
		// it's work!!!
		Collections.sort(l, new Comparator<String>(){
			@Override
			public int compare(String a, String b){
				return b.compareTo(a);
			};
		});
		
		System.out.println(l);
	}
}
