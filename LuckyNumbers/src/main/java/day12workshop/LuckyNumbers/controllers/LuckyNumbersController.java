package day12workshop.LuckyNumbers.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/generate")
public class LuckyNumbersController {

    private final static List<String> imgFileNames = Constants.getImgFileName();

    private Random rand = new Random();

    @GetMapping
    public String getRandomNumbers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String count,
            @RequestParam(required = false, name ="list") String listNums,
            Model model) {

        // store the imgFileNames to be binded to model
        List<String> selectedNums = new ArrayList<>();

        model.addAttribute("name", name);
        model.addAttribute("count", count);
        model.addAttribute("listNums", listNums);

        // if listNums is not empty --> takes precedence
        if (listNums != null && !listNums.isEmpty()) {

            String[] idxInString = listNums.split(",");

            for (String s : idxInString) {

                try {
                    String fullPath = Constants.IMG_DIR + imgFileNames.get(Integer.parseInt(s));

                    selectedNums.add(fullPath);

                } catch (NumberFormatException e) {
                    System.err.println("Error retrieving image filename. Invalid number in list provided");
                    e.printStackTrace();
                } catch (IndexOutOfBoundsException e) {
                    System.err.println("Error retrieving image filanem. Number in list > 31");
                    e.printStackTrace();
                }
            }

            model.addAttribute("randNums", selectedNums);
            return "luckyNumbers";

        } else {

            int numGenerated = 0;

            while (numGenerated < Integer.parseInt(count)) {

                int idx = rand.nextInt(imgFileNames.size());

                String fullPath = Constants.IMG_DIR + imgFileNames.get(idx);

                // to avoid duplicates
                if (!selectedNums.contains(fullPath)) {
                    selectedNums.add(fullPath);
                    numGenerated++;
                }
            }


            model.addAttribute("randNums", selectedNums);
            return "luckyNumbers";
        }

    }

}
