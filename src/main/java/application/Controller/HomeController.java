package application.Controller;

import application.data.model.Product;
import application.data.service.ProductService;
import application.data.service.ProductServiceIpml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

@Controller
@RequestMapping(path = "/")
public class HomeController {

    @Autowired // tu dong noi vao khoi tao
    private ProductService productService;
    @Autowired
    private ProductServiceIpml productServiceIpml;

    private String[] images = {
            "http://demo80008.webthienthan.net/image/cache/catalog/san pham/hoaqua_4-500x500-241x241.jpg",
            "http://demo80008.webthienthan.net/image/cache/catalog/san pham/hoaqua_3-500x500-241x241.jpg",
            "http://demo80008.webthienthan.net/image/cache/catalog/san pham/hoaqua_2-500x500-241x241.jpg",
            "http://demo80008.webthienthan.net/image/cache/catalog/san pham/hoaqua_2-500x500-241x241.jpg",
            "http://demo80008.webthienthan.net/image/cache/catalog/san pham/hoaqua_4-500x500-241x241.jpg"
    };




    // danh cho admin
    @GetMapping(path = "/admin")
    public String admin(Model model){


        long totalProducts = productService.getTotalProducts();
        if (totalProducts <= 0){
            ArrayList<Product> listProducts = new ArrayList<>();
            Random random = new Random();
            for (int i = 1 ; i<= 100 ; i++){
                Product p = new Product();
                p.setCreatedDate(new Date());
                p.setName("Product" +i);
                p.setShortDesc("Description for product" +i);
                p.setImage(images[random.nextInt(images.length)]);
                listProducts.add(p);
            }
            productService.addNewListProducts(listProducts);
            model.addAttribute("messege","oke"+listProducts.size());

        }
        else {
            model.addAttribute("messege", "no"+totalProducts);
        }
        model.addAttribute("contacts" , productServiceIpml.fillAll());

        return "admin";
    }
    @GetMapping(path = "/")
    public String index(Model model, @RequestParam(value = "pageSize" , required = false) String ps ,
                        @RequestParam(value = "pageNumber" , required = false) String pn){
        try {
            int pageSize = Integer.parseInt(ps);
            int pageNumber = Integer.parseInt(pn);
            if (pageSize>0 && pageNumber>= 0){
                model.addAttribute("paginableItem" , productService.getListProducts(pageSize,pageNumber));

            }else {
                model.addAttribute("paginableItem" , productService.getListProducts(10,0));
            }
        }catch (Exception e){
            model.addAttribute("paginableItem" , productService.getListProducts(10 , 0));
        }
        return "index";
    }
    @GetMapping("/admin/create")
    public String create(Model model) {
        model.addAttribute("contact", new Product());
        return "form";
    }
    @PostMapping("/admin/save")
    public String save(@Valid Product contact, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "form";
        }
        contact.setCreatedDate(new Date());
        productServiceIpml.save(contact);

        redirect.addFlashAttribute("success", "Saved contact successfully!");
        return "redirect:/admin";
    }
    @GetMapping("/admin/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("contact", productServiceIpml.findOne(id));
        System.out.println(id);
        return "form";

    }
    @GetMapping("/admin/search")
    public String search(@RequestParam("q") String q, Model model) {
        if (q.equals("")) {
            return "redirect:/admin";
        }

        model.addAttribute("contacts", productServiceIpml.search(q));
        return "admin";
    }
    @GetMapping("/admin/{id}/delete")
    public String delete(@PathVariable int id, RedirectAttributes redirect) {
        productServiceIpml.delete(id);

        redirect.addFlashAttribute("success", "Deleted contact successfully!");
        return "redirect:/admin";
    }
}