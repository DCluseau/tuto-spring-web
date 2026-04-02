package fr.fms.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CartRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Cart;
import fr.fms.entities.Category;

@Controller
public class ArticleController {
	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	CartRepository cartRepository;
	
	public ArticleController() {}
	
	@GetMapping("/index")
	public String index(Model model, @RequestParam(name="page", defaultValue = "0") int page,
			@RequestParam(name="keyword", defaultValue = "")String kw) {
		Page<Article> articles = articleRepository.findByDescriptionContains(kw, PageRequest.of(page,  5));
		List<Category> categories = categoryRepository.findAll();
		model.addAttribute("listArticle", articles.getContent());
		model.addAttribute("pages", new int[articles.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("keyword", kw);
		model.addAttribute("listCategory", categories);
		
		return "articles";
	}
	
	@GetMapping("/category")
	public String category(Model model, @RequestParam(name="page", defaultValue = "0") int page, @RequestParam(name="category", defaultValue="")String cat) {
		Category category = categoryRepository.findByName(cat);
		Page<Article> articles = articleRepository.findByCategory(category, PageRequest.of(page,  5));
		List<Category> categories = categoryRepository.findAll();
		model.addAttribute("listArticle", articles.getContent());
		model.addAttribute("pages", new int[articles.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("listCategory", categories);
		model.addAttribute("category", cat);
		
		return "category";
	}
	
	@GetMapping("/delete")
	public String delete(Long id, int page, String keyword) {
		articleRepository.deleteById(id);
		return "redirect:/index?page="+page+"&keyword="+keyword;
	}
	
	@GetMapping("/edit")
	public String edit(Model model, Long id) {
		Article article = articleRepository.getById(id);
		model.addAttribute(article);
		model.addAttribute("categories", categoryRepository.findAll());
		return "edit";
	}
	
	@GetMapping("/article")
	public String article(Model model) {
		model.addAttribute("article", new Article());
		model.addAttribute("categories", categoryRepository.findAll());
		return "article";
	}
	
	@PostMapping("/save")
	public String save(Model model, @Valid Article article, Long categoryId, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "article";
		}
		Category category = categoryRepository.findById(categoryId).get();
		article.setCategory(category);
		articleRepository.save(article);
		return "redirect:/index";
	}
	
	@PostMapping("/update")
	public String update(Model model, @Valid Article article, Long categoryId, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "edit";
		}
		Category category = categoryRepository.findById(categoryId).get();
		article.setCategory(category);
		articleRepository.save(article);
		return "redirect:/index";
	}
	
	@GetMapping("/cart")
	public String cart(Model model, @RequestParam(name="page", defaultValue = "0") int page) {
		Cart cart = cartRepository.findById(1L).get();
		Page<Article> articles = articleRepository.findByCarts(cart, PageRequest.of(page,  5));
		List<Category> categories = categoryRepository.findAll();
		model.addAttribute("listArticle", articles.getContent());
		model.addAttribute("pages", new int[articles.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("listCategory", categories);
		
		return "cart";
	}
	
	@GetMapping("/addtocart")
	public String addToCart(Model model, Long id) {
		Article article = articleRepository.findById(id).get();
		Cart cart = cartRepository.findById(1L).get();
		article.addToCart(cart);
		articleRepository.save(article);
		return "redirect:/cart";
	}
	
	@GetMapping("/removefromcart")
	public String removeFromCart(Model model, Long id) {
		Article article = articleRepository.findById(id).get();
		Cart cart = cartRepository.findById(1L).get();
		article.removeFromCart(cart);
		articleRepository.save(article);
		return "redirect:/cart";
	}

}
