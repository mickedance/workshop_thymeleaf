package com.example.workshop_thymeleaf.controller;

import com.example.workshop_thymeleaf.model.dto.ProductForm;
import com.example.workshop_thymeleaf.model.dto.ProductView;
import com.example.workshop_thymeleaf.service.CategoryService;
import com.example.workshop_thymeleaf.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/product")
public class ProductController {
    private CategoryService categoryService;
    private ProductService productService;

    @Autowired
    public ProductController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/")
    public String main(Model model) {
        return list(model);
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("productViews", productService.findAll());
        return "product/product-list";
    }

    @GetMapping("/form")
    public String form(Model model) {
        ProductForm form = new ProductForm();
        model.addAttribute("form", form);
        model.addAttribute("categories", categoryService.findAll());
        return "product/product-form";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("form") ProductForm productForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("categories", categoryService.findAll());
        if (bindingResult.hasErrors()) {
            return "product/product-form";
        }

        productService.create(productForm);

        redirectAttributes.addFlashAttribute("message", "Product  " + productForm.getName() + " successfully created");
        redirectAttributes.addFlashAttribute("alertClass", "alert alert-info");
        System.out.println("no errors");
        return "redirect:list";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        if (!productService.deleteById(id)) {
            return "redirect:/product/list";
        }
        redirectAttributes.addFlashAttribute("message", "Product  Id " + id + " successfully deleted");
        redirectAttributes.addFlashAttribute("alertClass", "alert alert-info");
        return "redirect:/product/list";
    }

    @GetMapping("/details/{id}")
    public String detailsById(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes, Model model) {
        ProductView productView = productService.findById(id);
        System.out.println("details: " + productView);
        if (productView == null) {
            redirectAttributes.addFlashAttribute("message", "Product  Id " + id + " not found");
            redirectAttributes.addFlashAttribute("alertClass", "alert alert-danger");
            return "redirect:/product/list";
        }
        model.addAttribute("details", productView);
        return "product/product-details";
    }

    @GetMapping("/update/{id}")
    public String updateById(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes, Model model) {
        ProductView productView = productService.findById(id);
        System.out.println("productView: "+ productView);
        if (productView == null) {
            redirectAttributes.addFlashAttribute("message", "Product  Id " + id + " could not be updated/found");
            redirectAttributes.addFlashAttribute("alertClass", "alert alert-danger");
            return "redirect:/product/list";
        }
        ProductForm form = new ProductForm();
        form.setPrice(productView.getPrice());
        form.setName(productView.getName());
        form.setCategoryId(String.valueOf(productView.getCategoryView().getId()));
        form.setId(id);
        model.addAttribute("form", form);
        model.addAttribute("categories", categoryService.findAll());

        return "product/product-form";
    }
    @PostMapping("/updateProduct")
    public String updateProduct(@Valid @ModelAttribute("form") ProductForm productForm, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("message", "Product  Id " + productForm.getId() + " could not be updated");
            redirectAttributes.addFlashAttribute("alertClass", "alert alert-danger");
            return "product/product-form";
        }
        redirectAttributes.addFlashAttribute("message", "Product  Id " + productForm.getId() + " updated");
        redirectAttributes.addFlashAttribute("alertClass", "alert alert-info");


        productService.Update(productForm);

        return "redirect:/product/list";
    }
}
