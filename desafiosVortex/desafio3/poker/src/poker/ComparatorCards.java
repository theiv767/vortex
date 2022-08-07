package poker;

import java.util.Comparator;

public class ComparatorCards implements Comparator<String> {
	int maior1 = 1; // c1 maior que c2
	int maior2 = -1; // c2 é maior que c1

	@Override
	public int compare(String c1, String c2) {
		String[] value = c1.split("");
		String naipe = value[value.length - 1];
		String digit1 = c1.split(naipe)[0];
		value = c2.split("");
		naipe = value[value.length - 1];
		String digit2 = c2.split(naipe)[0];

		if (digit1.matches("[2-9]") && digit2.matches("[2-9]")) {
			Integer intd1 = Integer.valueOf(digit1);
			Integer intd2 = Integer.valueOf(digit2);

			return Integer.compare(intd1, intd2);
		} else if (digit1.matches("[2-9]") && (digit2.matches("10") || digit2.matches("[A-Z]"))) {
			return maior2;

		} else if (digit1.matches("10") && digit2.matches("[2-9]")) {
			return maior1;

		} else if (digit1.matches("10") && digit2.matches("[A-Z]")) {
			return maior2;

		} else if (digit1.matches("[A-Z]") && (digit2.matches("[2-9]") || digit2.matches("10"))) {
			return maior1;

		} else if (digit1.matches("[A-Z]") && digit2.matches("[A-Z]")) {
			if (digit1.equals("J")) {
				return maior2;

			}else if(digit2.equals("J")) {
				return maior1;
				
				
			}
			
			else if (digit1.equals("Q")) {
				if (digit2.equals("J")) {
					return maior1;
				} else {
					return maior2;
				}

			}else if(digit2.equals("Q")) {
				if (digit1.equals("J")) {
					return maior2;
				} else {
					return maior1;
				}
				
			}
			
			
			else if (digit1.equals("K")) {
				if (digit2.equals("J") || digit2.equals("Q")) {
					return maior1;
				} else {
					return maior2;
				}

			}else if(digit2.equals("K")) {
				if (digit1.equals("J") || digit1.equals("Q")) {
					return maior2;
				} else {
					return maior1;
				}
				
			}
			
			
			else if (digit1.equals("A")) {
				return maior2;

			}else if(digit2.equals("A")) {
				return maior2;	
			}
		}
		
		return 0;
	}

}
