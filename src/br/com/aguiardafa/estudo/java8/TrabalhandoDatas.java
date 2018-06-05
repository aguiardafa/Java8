package br.com.aguiardafa.estudo.java8;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TrabalhandoDatas {
	public static void main(String[] args) {
		LocalDate hoje = LocalDate.now();
        LocalDate copaDoMundoDaRussia = LocalDate.of(2018, Month.JUNE, 14);
        
        System.out.println(hoje);
        System.out.println("Dia da semana: " + hoje.getDayOfWeek().name());
        System.out.println("Dia da semana: " + hoje.getDayOfWeek().ordinal());
        System.out.println("Mes: " + hoje.getMonthValue());
        System.out.println("Mes: " + hoje.getMonth().name());       
        System.out.println("Ano: " + hoje.getYear());

        //COMPARANDO DATAS
        System.out.println(hoje.isAfter(copaDoMundoDaRussia));
        System.out.println(hoje.isBefore(copaDoMundoDaRussia)); 
        System.out.println(hoje.isEqual(copaDoMundoDaRussia));
        
        //### usando PERIOD ###                     
        Period periodo = Period.between(hoje, copaDoMundoDaRussia);
        System.out.println(periodo.getYears() + " Anos " + periodo.getMonths() + " Meses " + periodo.getDays() + " Dias"); 
        System.out.println("Total de meses completos entre as datas: " + periodo.toTotalMonths());
        
        //### usando ChronoUnit ### 
        long days = ChronoUnit.DAYS.between(hoje, copaDoMundoDaRussia);
        System.out.println(days); //dias total entre as datas
        
        //### Incrementando e decrementando suas datas ###
        System.out.println("Mais 5 dias:" + hoje.plusDays(5));
        System.out.println("Mais 5 semanas:" + hoje.plusWeeks(5));
        System.out.println("Mais 5 anos:" + hoje.plusYears(5));
        System.out.println("Mais 2 meses:" + hoje.plusMonths(2));
        System.out.println("Menos 1 ano:" + hoje.minusYears(1));
        System.out.println("Menos 1 mês:" + hoje.minusMonths(1)); 
        System.out.println("Menos 3 dia: " + hoje.minusDays(3));
        System.out.println("Data Original:" + hoje); //observe que o LocalDate assim como String é imutável
        
        LocalDate proximaCopa = copaDoMundoDaRussia.plusYears(4);
        System.out.println("Próxima Copa: " + proximaCopa);
        
        //### comparando fusohorário ###
        LocalDateTime hora = LocalDateTime.of(2016, Month.APRIL, 4, 22, 30);
        
        ZoneId fusoHorarioDeSaoPaulo = ZoneId.of("America/Sao_Paulo");
        ZonedDateTime horaSaoPaulo = ZonedDateTime.of(hora, fusoHorarioDeSaoPaulo);
        System.out.println(horaSaoPaulo);
         
        ZoneId fusoHorarioDeParis = ZoneId.of("Europe/Paris");
        ZonedDateTime horaParis = ZonedDateTime.of(hora, fusoHorarioDeParis);
        System.out.println(horaParis);
         
        Duration diferencaDeHoras = Duration.between(horaSaoPaulo, horaParis);
        System.out.println("Diferença de fuso horário: " + diferencaDeHoras.getSeconds() / 60 / 60); 
        
        //### Formatando Datas ###
        DateTimeFormatter formatadorBarra = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatadorTraco = DateTimeFormatter.ofPattern("dd-MM-yyyy");
         
        System.out.println("Data com /: " + hoje.format(formatadorBarra));     
        System.out.println("Data com -: " + hoje.format(formatadorTraco));
        
        //### Classe Instant ###
        Instant iInicial = Instant.now();
        try {
               Thread.sleep(1000);
        } catch (InterruptedException e) {
               e.printStackTrace();
        }
        Instant iFinal = Instant.now();
         
        Duration duracao = Duration.between(iInicial, iFinal);
         
        System.out.println("Duração em nanos segundos: " + duracao.toNanos());
        System.out.println("Duração em minutos: " + duracao.toMinutes());
        System.out.println("Duração em horas: " + duracao.toHours());
        System.out.println("Duração em milisegundos: " + duracao.toMillis());
        System.out.println("Duração em dias: " + duracao.toDays()); 
        
	}
}
