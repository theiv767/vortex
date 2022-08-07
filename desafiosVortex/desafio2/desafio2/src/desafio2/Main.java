package desafio2;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int numStars = 0;
		int star1, star2;
		boolean flag = true;
		System.out.print("informe o número de estrelas: ");
		
		
		while (flag) {
			numStars = sc.nextInt();
			if(numStars >= 4 && numStars <=8){ 
				flag = false;
			}else{
				System.out.print("número de estrelas inválido, escolha outro valor: ");
				
			}
		}
		System.out.print("informe a primeira estrela observada: ");
		star1 = sc.nextInt();
		System.out.print("informe a segunda estrela observada: ");
		star2 = sc.nextInt();
		
		System.out.println("---------------------------------\n");
		Constellations c = new Constellations(numStars, numStars);
		showMatrix(c);
		System.out.println(connectionTest(c, star1, star2));
		
		sc.close();
		
	}

	
	
	
	public static void showMatrix(Constellations c) {
		System.out.println("[\n");
		for (int i = 0; i < c.getRows(); i++) {
			System.out.print("  [ ");
			for (int j = i * c.getCols(); j < (i * c.getCols()) + c.getCols(); j++) {
				System.out.print(c.getElements()[j] + ", ");
			}
			System.out.println("], ");
		}
		System.out.print("\n], ");

	}
	
	public static String connectionTest(Constellations c, int star1, int star2) {
		
		if(c.getElements()[star1*c.getCols() + star2] == 0) {
			return "não há ligação";
			
		}else {
			return "há ligação";
			
		}
		
	}
}
