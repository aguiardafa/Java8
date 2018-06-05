package br.com.aguiardafa.estudo.java8;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ExemploStream {

	public static void main(String[] args) {
		List<Curso> cursos = new ArrayList<Curso>();
		cursos.add(new Curso("Python", 45));
		cursos.add(new Curso("JavaScript", 150));
		cursos.add(new Curso("Java 8", 113));
		cursos.add(new Curso("C", 55));
		cursos.add(new Curso("Cobol", 45));
		
		//### trabalhando com List forma JAVA 8 - usando MÉTODO DEFAULT
		cursos.sort(Comparator.comparing(Curso::getAlunos));
		//cursos.sort(Comparator.comparingInt(c -> c.getAlunos()));
		cursos.forEach(c -> System.out.println(c.getAlunos()));
		
		//### usando STREAM
		cursos.stream()
			.filter(c -> c.getAlunos() > 50)
			.sorted(Comparator.comparing(Curso::getAlunos))
			.forEach(c -> System.out.println(c.getAlunos()));
		
		cursos.stream()
		.filter(c -> c.getAlunos() > 50)
		.sorted(Comparator.comparing(Curso::getAlunos))
		.map(Curso::getNome)
		.forEach(System.out::println);
		
		//recurso de stream para trabalhar com PARALELISMO
		// ou seja, possibilitará paralelizar o seu processamento
		cursos.parallelStream()
		   .filter(c -> c.getAlunos() > 50)
		   .sorted(Comparator.comparing(Curso::getAlunos))
		   .map(Curso::getAlunos)
		   .forEach(System.out::println);
		
		// Operações terminais em cima do MapToInt
		//média de alunos
		System.out.println(cursos.stream().mapToInt(Curso::getAlunos).average().getAsDouble());
		//soma
		Integer somaAlunoCursosIniciandoJ = cursos.stream().filter(c -> c.getNome().startsWith("J"))
                .mapToInt(Curso::getAlunos).sum();
		//maior valor e menor valor
		Integer maiorQuantAlunos = cursos.stream().filter(c -> c.getNome().startsWith("J"))
				.mapToInt(Curso::getAlunos).max(). getAsInt();
		Integer menorQuantAlunos = cursos.stream().filter(c -> c.getNome().startsWith("J"))
				.mapToInt(Curso::getAlunos).min().getAsInt();
		
		//### MANEIRA OTIMIZADA - IntSummaryStatistics
		// maneira de recuperar vários dados de um Stream após serem filtrados e mapeados
		IntSummaryStatistics intSummStat  = cursos.stream().filter(c -> c.getNome().startsWith("J"))
		                 .mapToInt(Curso::getAlunos).summaryStatistics(); //filtra e mapeia uma unica vez
		System.out.println(intSummStat.getSum());
		System.out.println(intSummStat.getMax());
		System.out.println(intSummStat.getMin());
		System.out.println(intSummStat.getAverage());
		
		//### CONVERTE EM COLEÇÃO - método collect() juntamente com a classe Collectors
		// podemos converter um Stream para os tipos List, Set ou Map. 
		List<Curso> list = cursos.stream()
									.filter(c -> c.getAlunos() > 50)
									.sorted(Comparator.comparing(Curso::getAlunos))
									.collect(Collectors.toList());
		//### AGRUPAR ELEMENTOS - método da classe Collectors é groupingBy()
		Map<Integer, List<Curso>> map = 
				cursos.stream().collect(Collectors.groupingBy(Curso::getAlunos));
		//imprime o cursos agrupado com 45 alunos        
		map.get(45).forEach(c -> System.out.println(c.getNome()));
		
		//### OPTIONAL
		Optional<Curso> optCurso = cursos.stream().filter(c -> c.getAlunos() > 50).findFirst();
        //forma tradicional de acessar objetos
		if (optCurso.isPresent()) {
			Curso c = optCurso.get();
		    System.out.println(c.getAlunos());
		}
		//métodos disponíveis
		optCurso.ifPresent(c -> System.out.println(c.getAlunos()));
		optCurso.orElseThrow(IllegalStateException::new);
		optCurso.orElse(new Curso("C#", 28));
		//forma de interface fluente, utilizando dos recurso do OPTIONAL
		cursos.stream()
			.filter(c -> c.getAlunos() > 50)
			.findFirst()
			.ifPresent(c -> System.out.println(c.getAlunos()));

	}

}
