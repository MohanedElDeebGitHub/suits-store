package service.middleware;

import model.product.Product;
import service.payValidator.PayValidator;
import service.productValidator.ProductValidator;

public class SystemsHandler {
    private String cardInfo;
    private Product product;
    // cardinfo
    // product

    public boolean validateEntireSystem(String cardInfo, Product product){
        boolean isPayValid = PayValidator.isUsable(cardInfo);
        boolean isProductValid = ProductValidator.doesProductExist(product);

        return isPayValid && isProductValid;
    }
}
