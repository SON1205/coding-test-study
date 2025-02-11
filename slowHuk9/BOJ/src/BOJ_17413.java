import java.util.ArrayDeque;
import java.util.Scanner;

public class BOJ_17413 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		StringBuilder sb = new StringBuilder();
		ArrayDeque<Character> deque = new ArrayDeque<>();

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '<') {
				int j = i;
				while (s.charAt(j) != '>') {
					sb.append(s.charAt(j));
					j++;
				}
				sb.append('>');
				i = j;
			} else if (s.charAt(i) == ' ') {
				sb.append(" ");
			} else {
				int j = i;
				while (j < s.length() && s.charAt(j) != ' ' && s.charAt(j) != '<') {
					deque.addLast(s.charAt(j));
					j++;
				}
				int k = deque.size();
				for (int q = 0; q < k; q++) {
					sb.append(deque.pollLast());
				}
				i = j - 1;
			}
		}

		System.out.println(sb.toString());
	}
}
