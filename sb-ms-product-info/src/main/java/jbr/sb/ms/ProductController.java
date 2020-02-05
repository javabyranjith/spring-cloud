package jbr.sb.ms;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jbr.sb.ms.model.Product;

@RestController
@RequestMapping("/product")
public class ProductController {

  @RequestMapping("/info/{productid}")
  public Product ratingById(@PathVariable("productid") String productid) {
    return allProducts().stream().filter(e -> e.getId().equals(productid)).findAny().get();
  }

  @RequestMapping("/{userid}")
  public List<Product> getProductsForAUser(@PathVariable("userid") String userid) {
    return Arrays.asList(new Product("100", "Samsung", "1000"), new Product("200", "Nokia", "2000"));
  }

  public List<Product> allProducts() {
    return Arrays.asList(new Product("100", "Samsung", "1000"), new Product("200", "Nokia", "2000"),
        new Product("300", "Xiomi", "3000"));
  }

}
