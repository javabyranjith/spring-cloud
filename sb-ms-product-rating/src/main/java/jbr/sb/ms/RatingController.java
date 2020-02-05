package jbr.sb.ms;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jbr.sb.ms.model.Rating;

@RestController
@RequestMapping("/product/rating")
public class RatingController {

  @RequestMapping("/{productid}")
  public Rating ratingById(@PathVariable("productid") String productid) {
    return new Rating("100", "4");
  }
}
