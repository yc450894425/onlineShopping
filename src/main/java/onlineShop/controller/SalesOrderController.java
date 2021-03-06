package onlineShop.controller;

import onlineShop.model.Cart;
import onlineShop.model.Customer;
import onlineShop.model.SalesOrder;
import onlineShop.service.CartService;
import onlineShop.service.SalesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SalesOrderController {

    @Autowired
    private SalesOrderService salesOrderService;

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/order/{cartId}", method = RequestMethod.GET)
    public String createOrder(@PathVariable(value = "cartId") int cartId) {

        SalesOrder salesOrder = new SalesOrder();
        Cart cart = cartService.getCartById(cartId);
        salesOrder.setCart(cart);
        Customer customer = cart.getCustomer();
        salesOrder.setCustomer(customer);
        salesOrder.setBillingAddress(customer.getBillingAddress());
        salesOrder.setShippingAddress(customer.getShippingAddress());
        salesOrderService.addSalesOrder(salesOrder);
        return "redirect:/checkout?cartId=" + cartId;
    }
}
