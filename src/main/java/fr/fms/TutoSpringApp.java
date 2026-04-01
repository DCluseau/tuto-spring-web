package fr.fms;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

//import fr.fms.dao.ArticleRepository;
//import fr.fms.entities.Article;

@SpringBootApplication
public class TutoSpringApp implements CommandLineRunner {
	
//	@Autowired
//	private ArticleRepository articleRepository;

	public static void main(String[] args) {
		SpringApplication.run(TutoSpringApp.class, args);
	}

	public void run(String...args) throws Exception{
		/*
		 * Create categories and articles
		 */
	
//		articleRepository.save(new Article("Samsung", "S10", 500.0));
//		articleRepository.save(new Article("Samsung", "S9", 350.0));
//		articleRepository.save(new Article("Xiaomi", "MI10", 100.0));
//		
//		articleRepository.save(new Article("Samsung", "GalaxyTab", 450.0));
//		articleRepository.save(new Article("Apple", "Ipad", 450.0));
//		
//		articleRepository.save(new Article("Asus", "R510", 600.0));
		
	}
}
