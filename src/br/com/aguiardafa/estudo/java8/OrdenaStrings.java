package br.com.aguiardafa.estudo.java8;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import static java.util.Comparator.*;

public class OrdenaStrings {
	public static void main(String[] args) {
		List<String> palavras = new ArrayList<String>();
		palavras.add("Alura online");
		palavras.add("Editora casa do codigo");
		palavras.add("caelum");

		// ###### INTERAR LIST ######
		// forma java 5
		for (String string : palavras) { // usando for (foreach)
			System.out.println(string);
		}

		// 1� - nova forma JAVA 8 - usando M�TODO DEFAULT
		// com m�todo DEFAULT "forEach" da interface List, que recebe um consumer
		Consumer<String> consumer = new ImprimeNaLinha();
		palavras.forEach(consumer); // recebe um consumer (nova interface do java 8)
		
		// 2� forma - com classe an�nima
		//classes an�nimas: usamos com frequ�ncia para implementar listeners e callbacks 
		//que n�o ter�o reaproveitamento
		Consumer<String> consumidor = new Consumer<String>() { //� dado new direto na interface
			// e dentro dos par�nteses implementamos o m�todo
			@Override
		    public void accept(String s) {
		        System.out.println(s);
		    }
		};
		palavras.forEach(consumidor);
		
		// 3� forma - sem uso de vari�vel 
		// (passando a classe an�nima diretamente para o forEach)
		palavras.forEach(new Consumer<String>() {
		    public void accept(String s) {
		        System.out.println(s);
		    }
		});
		
		// 4� forma JAVA 8 - usando LAMBDA
		// (como a interface s� tem um m�todo, n�o precisa escrever o nome do m�todo, nem dar new
		//  Apenas declarar os argumentos e o bloco a ser executado, separados por ->
		palavras.forEach((String s) -> {
		    System.out.println(s);
		});
		
		// 5� forma - mais sucinta usando todo poder de inferencia da LAMBDA
		palavras.forEach(s -> System.out.println(s));
		
		// 6� forma JAVA 8 - usando METHOD REFERENCE
		// (a lambda s� pode ser escrita como method reference, 
		//  caso a invoca��o do m�todo necessita dos mesmos par�metros que o lambda)
		palavras.forEach(System.out::println);
		
		
		// ######  ORDENANDO EM LIST  ######
		// com uso da classe utilit�ria Collections
		Collections.sort(palavras); // usando comparador padr�o do string
		System.out.println(palavras);

		Comparator<String> novoComparador = new ComparadorPorTamanho();
		Collections.sort(palavras, novoComparador); // usando comparador novo
		System.out.println(palavras);

		// 1� - nova forma JAVA 8 - usando M�TODO DEFAULT
		palavras.sort(novoComparador); // usando o metodo DEFAULT "sort" da interface List, que implementa o sort
		System.out.println(palavras);
		
		// 2� forma - com classe an�nima / sem uso de vari�vel 
		palavras.sort(new Comparator<String>() {
		    public int compare(String s1, String s2) {
		        if (s1.length() < s2.length())
		            return -1;
		        if (s1.length() > s2.length())
		            return 1;
		        return 0;
		    }
		});
		
		// 3� forma JAVA 8 - usando LAMBDA
		// (como temos mais de um argumento, esses devem ser conservados entre parenteses
		//  como temos mais de um statement no bloco do m�todo, deve ser conservado entre chaves
		palavras.sort((s1, s2) -> {
		    if (s1.length() < s2.length())
		        return -1;
		    if (s1.length() > s2.length())
		        return 1;
		    return 0;
		});
		
		// 4� forma - mais sucinta usando todo poder de inferencia da LAMBDA / recurso de API Integer
		palavras.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));
		//ou fugindo fo m�todo compare
		palavras.sort((s1, s2) -> s1.length() - s2.length());
		// ou usando o M�todo default estatico de comparator
		// que � uma fabrica de comparador, que recebe a lambda com o crit�rio de compara��o
		palavras.sort(Comparator.comparing(s -> s.length()));
		
		// 5� forma JAVA 8 - usando METHOD REFERENCE
		// (Fazemos uma refer�ncia ao m�todo (method reference))
		palavras.sort(Comparator.comparing(String::length));
		// ou com import estatico: import static java.util.Comparator.*;
		palavras.sort(comparing(String::length));
		
		//TEstando outras formas de comparar
		palavras.sort(Comparator.comparing(String::hashCode));
		System.out.println(palavras);
		palavras.sort((s1, s2) -> s1.compareTo(s2)); //compara pelo valor ASCII
		System.out.println(palavras);
		palavras.sort((s1, s2) -> s1.compareToIgnoreCase(s2)); //compara ordem alfab�tica ignorando CASE SENSITIVE
		System.out.println(palavras);
		palavras.sort(String.CASE_INSENSITIVE_ORDER); //similar ao m�todo compareToIgnoreCase()
		System.out.println(palavras);
	}
}

//criar classes isoladas n�o � muito aconselhado, somente se for reutilizar o recurso em v�rios trechos
class ComparadorPorTamanho implements Comparator<String> {
	@Override
	public int compare(String arg0, String arg1) {
		if (arg0.length() < arg1.length())
			return -1;
		if (arg0.length() > arg1.length())
			return 1;
		return 0;
	}
}

class ImprimeNaLinha implements Consumer<String> {
	@Override
	public void accept(String arg0) {
		// o que vou fazer toda vez que consumir uma String?
		System.out.println(arg0);
	}

}