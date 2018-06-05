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

		// 1ª - nova forma JAVA 8 - usando MÉTODO DEFAULT
		// com método DEFAULT "forEach" da interface List, que recebe um consumer
		Consumer<String> consumer = new ImprimeNaLinha();
		palavras.forEach(consumer); // recebe um consumer (nova interface do java 8)
		
		// 2ª forma - com classe anônima
		//classes anônimas: usamos com frequência para implementar listeners e callbacks 
		//que não terão reaproveitamento
		Consumer<String> consumidor = new Consumer<String>() { //é dado new direto na interface
			// e dentro dos parênteses implementamos o método
			@Override
		    public void accept(String s) {
		        System.out.println(s);
		    }
		};
		palavras.forEach(consumidor);
		
		// 3ª forma - sem uso de variável 
		// (passando a classe anônima diretamente para o forEach)
		palavras.forEach(new Consumer<String>() {
		    public void accept(String s) {
		        System.out.println(s);
		    }
		});
		
		// 4ª forma JAVA 8 - usando LAMBDA
		// (como a interface só tem um método, não precisa escrever o nome do método, nem dar new
		//  Apenas declarar os argumentos e o bloco a ser executado, separados por ->
		palavras.forEach((String s) -> {
		    System.out.println(s);
		});
		
		// 5ª forma - mais sucinta usando todo poder de inferencia da LAMBDA
		palavras.forEach(s -> System.out.println(s));
		
		// 6ª forma JAVA 8 - usando METHOD REFERENCE
		// (a lambda só pode ser escrita como method reference, 
		//  caso a invocação do método necessita dos mesmos parâmetros que o lambda)
		palavras.forEach(System.out::println);
		
		
		// ######  ORDENANDO EM LIST  ######
		// com uso da classe utilitária Collections
		Collections.sort(palavras); // usando comparador padrão do string
		System.out.println(palavras);

		Comparator<String> novoComparador = new ComparadorPorTamanho();
		Collections.sort(palavras, novoComparador); // usando comparador novo
		System.out.println(palavras);

		// 1ª - nova forma JAVA 8 - usando MÉTODO DEFAULT
		palavras.sort(novoComparador); // usando o metodo DEFAULT "sort" da interface List, que implementa o sort
		System.out.println(palavras);
		
		// 2ª forma - com classe anônima / sem uso de variável 
		palavras.sort(new Comparator<String>() {
		    public int compare(String s1, String s2) {
		        if (s1.length() < s2.length())
		            return -1;
		        if (s1.length() > s2.length())
		            return 1;
		        return 0;
		    }
		});
		
		// 3ª forma JAVA 8 - usando LAMBDA
		// (como temos mais de um argumento, esses devem ser conservados entre parenteses
		//  como temos mais de um statement no bloco do método, deve ser conservado entre chaves
		palavras.sort((s1, s2) -> {
		    if (s1.length() < s2.length())
		        return -1;
		    if (s1.length() > s2.length())
		        return 1;
		    return 0;
		});
		
		// 4ª forma - mais sucinta usando todo poder de inferencia da LAMBDA / recurso de API Integer
		palavras.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));
		//ou fugindo fo método compare
		palavras.sort((s1, s2) -> s1.length() - s2.length());
		// ou usando o Método default estatico de comparator
		// que é uma fabrica de comparador, que recebe a lambda com o critério de comparação
		palavras.sort(Comparator.comparing(s -> s.length()));
		
		// 5ª forma JAVA 8 - usando METHOD REFERENCE
		// (Fazemos uma referência ao método (method reference))
		palavras.sort(Comparator.comparing(String::length));
		// ou com import estatico: import static java.util.Comparator.*;
		palavras.sort(comparing(String::length));
		
		//TEstando outras formas de comparar
		palavras.sort(Comparator.comparing(String::hashCode));
		System.out.println(palavras);
		palavras.sort((s1, s2) -> s1.compareTo(s2)); //compara pelo valor ASCII
		System.out.println(palavras);
		palavras.sort((s1, s2) -> s1.compareToIgnoreCase(s2)); //compara ordem alfabética ignorando CASE SENSITIVE
		System.out.println(palavras);
		palavras.sort(String.CASE_INSENSITIVE_ORDER); //similar ao método compareToIgnoreCase()
		System.out.println(palavras);
	}
}

//criar classes isoladas não é muito aconselhado, somente se for reutilizar o recurso em vários trechos
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