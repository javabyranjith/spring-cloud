package jbr.sb.ms;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import jbr.sb.ms.model.Product;
import jbr.sb.ms.model.Rating;
import jbr.sb.ms.model.Wishlist;

@RestController
@RequestMapping("/user/wishlist")
public class WishlistController {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private WebClient.Builder webClient;

  @RequestMapping("/{userid}")
  public List<Wishlist> getUserWishlistUsingRestTemplate(@PathVariable("userid") String userid) {

    List<Product> products = way2(userid);
    return products.stream()
        .map(prod ->
          {
            Product product = restTemplate.getForObject("http://product-info-service/product/info/" + prod.getId(),
                Product.class);
            Rating rating = restTemplate.getForObject("http://product-rating-service/product/rating/" + prod.getId(),
                Rating.class);

            return new Wishlist(prod.getId(), product.getName(), product.getPrice(), rating.getRating());
          })
        .collect(Collectors.toList());
  }

  private List<Product> way2(String userid) {
    ResponseEntity<List<Product>> response = restTemplate.exchange("http://product-info-service/product/" + userid,
        HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() {
        });
    return response.getBody();
  }

  private List<Product> way1(String userid) {
    ResponseEntity<Product[]> response = restTemplate.getForEntity("http://localhost:6060/product/" + userid,
        Product[].class);
    return Arrays.asList(response.getBody());
  }

  // @RequestMapping("/{userid}")
  public List<Wishlist> getUserWishlistUsingWebClient(@PathVariable("userid") String userid) {
    List<Wishlist> wishlists = Arrays.asList(new Wishlist("100", "", "", ""), new Wishlist("200", "", "", ""),
        new Wishlist("300", "", "", ""));

    return wishlists.stream()
        .map(prod ->
          {
            Product product = webClient.build()
                .get()
                .uri("http://localhost:6060/product/info/" + prod.getProductid())
                .retrieve()
                .bodyToMono(Product.class)
                .block();

            Rating rating = webClient.build()
                .get()
                .uri("http://localhost:7070/product/rating/" + prod.getProductid())
                .retrieve()
                .bodyToMono(Rating.class)
                .block();

            return new Wishlist(prod.getProductid(), product.getName(), product.getPrice(), rating.getRating());
          })
        .collect(Collectors.toList());

  }

}
