package com.shubham.controller;

import com.shubham.model.Product;
import com.shubham.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Controller
public class ProductController {

    private ProductService productService;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }


    // SHOW PRODUCTS

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("products", productService.getAllProduct());
        return "products";
    }


    @RequestMapping("product/{id}")
    public String showProduct(@PathVariable Integer id, Model model){

        model.addAttribute("product",productService.getProductById(id));
        return "view-product";

    }

    @RequestMapping("product/edit/{id}")
    public String editProduct(@PathVariable Integer id, Model model) {

        model.addAttribute("edit1",productService.getProductById(id));
        return "edit-product";

    }

    @RequestMapping("product/delete/{id}")
    public String deleteProduct(@PathVariable Integer id){
        System.out.println(id);
        productService.deleteProducts(id);
        return "redirect:/products";

    }

    @RequestMapping(value = "/product/update-product")
    @Transactional
    public String updateproduct(@RequestParam("pid") Integer pid,@RequestParam("product_name") String product_name,@RequestParam("product_price") double product_price,@RequestParam("product_description") String product_description, Model model){

        System.out.println(pid);
        System.out.println("product_name" +product_name);
        System.out.println("product_price" +product_price);
        System.out.println("product_description" +product_description);

        Query query = entityManager.createQuery("UPDATE Product u set u.product_name=:product_name,u.product_description=:product_description,u.product_price=:product_price where u.pid=:pid");
        query.setParameter("product_name", product_name);
        query.setParameter("product_price", product_price);
        query.setParameter("product_description", product_description);
        query.setParameter("pid", pid);

        int i = query.executeUpdate();
        System.out.println(i);
        if(i>0){
            return "redirect:/product/" + pid;
        } else {
            return "error-500";
        }


    }

    @RequestMapping("product/new")
    public String newProduct(Model model){
        model.addAttribute("product", new Product());
        return "productforms";
    }

    @RequestMapping(value = "product", method = RequestMethod.POST)
    public String saveProduct(Product product){
        productService.saveProduct(product);
        return "redirect:/product/" + product.getPid();
    }


}
