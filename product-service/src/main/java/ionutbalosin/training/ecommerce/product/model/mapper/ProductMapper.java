package ionutbalosin.training.ecommerce.product.model.mapper;

import ionutbalosin.training.ecommerce.product.api.model.ProductCreateDto;
import ionutbalosin.training.ecommerce.product.api.model.ProductUpdateDto;
import ionutbalosin.training.ecommerce.product.model.Product;
import java.time.LocalDateTime;
import java.util.UUID;

/*
 * (c) 2022 Ionut Balosin
 * Website: www.ionutbalosin.com
 * Twitter: @ionutbalosin
 *
 * For the full copyright and license information, please view the LICENSE file that was distributed with this source code.
 */
public class ProductMapper {

  public Product map(ProductCreateDto newProduct) {
    return new Product()
        .name(newProduct.getName())
        .brand(newProduct.getBrand())
        .category(newProduct.getCategory())
        .price(newProduct.getPrice().floatValue())
        .currency(newProduct.getCurrency().toString())
        .quantity(newProduct.getQuantity())
        .dateIns(LocalDateTime.now())
        .usrIns("anonymous")
        .stat("A");
  }

  public Product map(UUID productId, ProductUpdateDto productUpdate) {
    return new Product()
        .id(productId)
        .quantity(productUpdate.getQuantity())
        .price(productUpdate.getPrice().floatValue())
        .usrUpd("anonymous")
        .dateUpd(LocalDateTime.now());
  }
}
