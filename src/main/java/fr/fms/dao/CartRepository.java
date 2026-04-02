package fr.fms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.fms.entities.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	
}
