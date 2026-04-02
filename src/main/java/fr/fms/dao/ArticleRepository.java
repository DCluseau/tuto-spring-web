package fr.fms.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.fms.entities.Article;
import fr.fms.entities.Cart;
import fr.fms.entities.Category;

public interface ArticleRepository extends JpaRepository<Article, Long> {
	Page<Article> findByDescriptionContains(String description, Pageable pageable);
	Page<Article> findByCategory(Category category, Pageable pageable);
	Page<Article> findByCarts(Cart cart, Pageable pageable);
}
