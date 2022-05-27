package com.example.green.Controller;

import com.example.green.Database.GreenDB;
import com.example.green.Model.Calculate;
import com.example.green.Model.Climate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WebController {

    GreenDB db = new GreenDB();

    @PostMapping("/hello")
    public void test(@RequestParam String arduino) {
        System.out.println(arduino);

    }

    @GetMapping("/")
    public String index(Model model) {

        double price = db.getPrice();
        model.addAttribute("priceOutput", price);

        return "index";
    }

    @PostMapping("/")
    public String indexPrice(@RequestParam double priceInput, Model model) {

        model.addAttribute("priceInput", priceInput);
        db.setNewPrice(priceInput);
        double price = db.getPrice();
        model.addAttribute("priceOutput", price);

        return "index";
    }

    @GetMapping("/temperature")
    public String showTemperature(Model model) {
        Calculate foo = new Calculate();
        List<Climate> area_A = db.getAllDataArea("A");
        List<Climate> area_B = db.getAllDataArea("B");
        List<Climate> area_C = db.getAllDataArea("C");

        Climate latest_A = area_A.get(area_A.size() - 1);
        Climate latest_B = area_B.get(area_B.size() - 1);
        Climate latest_C = area_C.get(area_C.size() - 1);

        List<Climate> average_A = foo.getAverage(db.getAllDataArea("A"));
        List<Climate> average_B = foo.getAverage(db.getAllDataArea("B"));
        List<Climate> average_C = foo.getAverage(db.getAllDataArea("C"));

        model.addAttribute("latest_A", latest_A);
        model.addAttribute("latest_B", latest_B);
        model.addAttribute("latest_C", latest_C);
        model.addAttribute("average_array_A", average_A);
        model.addAttribute("average_array_B", average_B);
        model.addAttribute("average_array_C", average_C);

        return "temperature";
    }

    @GetMapping("/humidity")
    public String showHumidity(Model model) {
        Calculate foo = new Calculate();
        List<Climate> area_A = db.getAllDataArea("A");
        List<Climate> area_B = db.getAllDataArea("B");
        List<Climate> area_C = db.getAllDataArea("C");

        Climate latest_A = area_A.get(area_A.size() - 1);
        Climate latest_B = area_B.get(area_B.size() - 1);
        Climate latest_C = area_C.get(area_C.size() - 1);

        List<Climate> average_A = foo.getAverage(area_A);
        List<Climate> average_B = foo.getAverage(area_B);
        List<Climate> average_C = foo.getAverage(area_C);

        model.addAttribute("latest_A", latest_A);
        model.addAttribute("latest_B", latest_B);
        model.addAttribute("latest_C", latest_C);
        model.addAttribute("average_array_A", average_A);
        model.addAttribute("average_array_B", average_B);
        model.addAttribute("average_array_C", average_C);
        return "humidity";
    }

    @GetMapping("/report")
    public String showReport(Model model) {
        List<Climate> area_A = db.getAllDataArea("A");
        List<Climate> area_B = db.getAllDataArea("B");
        List<Climate> area_C = db.getAllDataArea("C");
        Climate latest_A = area_A.get(area_A.size() - 1);
        Climate latest_B = area_B.get(area_B.size() - 1);
        Climate latest_C = area_C.get(area_C.size() - 1);

        double price = db.getPrice();
        model.addAttribute("price", price);
        model.addAttribute("latest_A", latest_A);
        model.addAttribute("latest_B", latest_B);
        model.addAttribute("latest_C", latest_C);
        model.addAttribute("array", db.getAllArrayData());
        return "report";
    }
}
