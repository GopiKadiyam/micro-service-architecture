package com.gk.order.controller;

import com.gk.order.model.Order;
import com.gk.order.model.ProductOrderedUsers;
import com.gk.order.model.OrderResponseDTO;
import com.gk.order.model.Product;
import com.gk.order.model.User;
import com.gk.order.service.ProductService;
import com.gk.order.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
@RefreshScope
public class OrderController {

    @Value("${message}")
    public String message;

    @Value("${test}")
    public String test;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    static List<Order> db = new ArrayList<>();

    static {
        db.add(new Order(1000L, 100L, 1L, LocalDate.now()));
        db.add(new Order(1001L, 101L, 2L, LocalDate.now()));
        db.add(new Order(1002L, 102L, 3L, LocalDate.now()));
        db.add(new Order(1003L, 103L, 4L, LocalDate.now()));
        db.add(new Order(1004L, 102L, 2L, LocalDate.now().plusDays(1L)));
        db.add(new Order(1005L, 103L, 2L, LocalDate.now()));
    }

    @GetMapping
    public List<Order> allOrders() {
        return db;
    }

    @GetMapping("/{id}")
    public Order order(@PathVariable("id") Long id) {
        return db.get((int) (id-1000));
    }

    @GetMapping("/user/{id}")
    public List<OrderResponseDTO> userRelatedOrder(@PathVariable("id") Long id) {
        // declare ref variable of return type
        List<OrderResponseDTO> response=new ArrayList<>();

        // mandatory variable validation
        if(null != id){
            // business logic start == business logic depends mapping of returnTypeOfMethod/responseTypeOfAPI's variables to tableColumns/anotherServiceAPICallResponseVariables/onlyCalculation/combinationOfAll
            //to call table/anotherServiceApi we must ask for input of their respective mapping to manager if we don't find logic

            // almost equal table query -- select * from order where userId= :id
            List<Order> orders = db.stream().filter((Order o) -> o.getUserId() == id)
                    .collect(Collectors.toList());

            System.out.println("***order**"+orders);
            for(Order order:orders){

                Product product=productService.product(order.getProductId());
                User user=userService.user(order.getUserId());
                // business logic end

                //mapping of tableColumns/serviceAPICall Response to ACTUAL RESPONSE-DTO
                OrderResponseDTO orderResponseDTO=new OrderResponseDTO();
                orderResponseDTO.setOrderId(order.getOrderId());
                orderResponseDTO.setProductId(order.getProductId());
                orderResponseDTO.setPrice(product.getPrice());
                orderResponseDTO.setProductName(product.getName());
                orderResponseDTO.setUserName(user.getName());
                orderResponseDTO.setOrderDate(order.getOrderDate());
                orderResponseDTO.setUserId(id);
                response.add(orderResponseDTO);
            }
        }
        else {
            // throw error
        }
        //return response
        return response;
    }

    @GetMapping("/product/{productId}")
    public List<ProductOrderedUsers> getUserDetails(@PathVariable("productId") Long productId){
        List<ProductOrderedUsers> productOrderedUsers=new ArrayList<>();
        if(null != productId){
         List<Long> userIds=db.stream().filter((Order o)->o.getProductId()==productId)
                 .map(Order::getUserId).collect(Collectors.toList());
         /*for (Long l:userIds){
            User user= userService.user(l);
             ProductOrderedUsers productOrderedUsers1=new ProductOrderedUsers();
             productOrderedUsers1.setUserId(user.getId());
             productOrderedUsers1.setUserName(user.getName());
             productOrderedUsers1.setCity(user.getCity());
             productOrderedUsers1.setGender(user.getGender());
             productOrderedUsers.add(productOrderedUsers1);
         }*/
            List<User> usersDetails=userService.selectedUsers(userIds);

            for (User user:usersDetails){
                ProductOrderedUsers response=new ProductOrderedUsers();
                response.setUserId(user.getId());
                response.setUserName(user.getName());
                response.setCity(user.getCity());
                response.setGender(user.getGender());
                productOrderedUsers.add(response);
            }
        }
    return productOrderedUsers;
    }

    @GetMapping("/profile-message")
    public String testProfile(){
        return "message  :" +message+"  ---- test : "+test;
    }
}

