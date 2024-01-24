package com.example.kiemtragiuaki.controller;

import com.example.kiemtragiuaki.model.Product;
import com.example.kiemtragiuaki.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    IProductRepository repository;

    @GetMapping()
    public String showHomePage(Model model) {
        List<Product> lists = repository.findAll();
        model.addAttribute("listProducts", lists);
        return "/list";
    }

    @GetMapping("/add-product")
    public String showProduct(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);

        return "/create";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute("product") Product product, RedirectAttributes redirectAttributes) {
        int id = (int) (Math.random() * 100);
        product.setId(id);
        repository.save(product);
        redirectAttributes.addFlashAttribute("message", "Create successfully");
        return "redirect:/products";
    }

    @GetMapping("/edit-product/{id}")
    public ModelAndView edit(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("listProducts", repository.findById(id).get());
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        repository.deleteById(id);
        return "redirect:/products";
    }
}

