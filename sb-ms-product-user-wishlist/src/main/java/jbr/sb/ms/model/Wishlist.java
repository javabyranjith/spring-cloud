package jbr.sb.ms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wishlist {
  private String productid;
  private String productname;
  private String productPrice;
  private String productRating;
}
