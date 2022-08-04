package com.example.workshop_thymeleaf.controller;

import com.example.workshop_thymeleaf.model.dto.CategoryForm;
import com.example.workshop_thymeleaf.model.dto.CategoryView;
import com.example.workshop_thymeleaf.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {


    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }
    @GetMapping("/")
    public String main(Model model){
        return view(model);
    }
    @GetMapping("/list")
    public String view(Model model){
        System.out.println("view from controller");
        List<CategoryView> categoryViews =  categoryService.findAll();
        System.out.println(categoryViews);
        model.addAttribute("categoryViews", categoryViews);
        return "category/category-list";
    }

    @GetMapping("/form")
    public String form( Model model ){
        CategoryForm form = new CategoryForm();
        model.addAttribute("form", form);
        return "category/category-form";
    }
    @PostMapping("/add")
    public String addNewCategory(@ModelAttribute("form") @Valid CategoryForm categoryForm, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "category/category-form";
        }
        redirectAttributes.addFlashAttribute("message", "Catagory  " +categoryForm.getName() +" successfully created");
        redirectAttributes.addFlashAttribute("alertClass", "alert alert-info");
        categoryService.create(categoryForm);
        return "redirect:list";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes){
        boolean deleted = categoryService.deleteById(id);
        if(deleted){
            redirectAttributes.addFlashAttribute("message", "Catagory Id "  +" successfully deleted");
            redirectAttributes.addFlashAttribute("alertClass", "alert danger-info");
        }else{
            redirectAttributes.addFlashAttribute("message", "Catagory Id " + " could not be deleted");
            redirectAttributes.addFlashAttribute("alertClass", "alert warning-warning");


        }
        return "redirect:/category/list";

    }
}
