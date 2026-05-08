package pl.school.assetmanagement.adapter.in.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String home() {
        return "index.html";
    }

    @GetMapping("/assets")
    public String assets() {
        return "assetsList.html";
    }

}
