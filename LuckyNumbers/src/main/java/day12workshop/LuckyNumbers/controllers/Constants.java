package day12workshop.LuckyNumbers.controllers;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    
    public final static String IMG_DIR = "/images/numbers/";

    public static List<String> getImgFileName() {

        List<String> imgs = new ArrayList<>();

        for (int i = 0; i < 31; i++) {

            String fileName = "number%d.jpg".formatted(i);
            imgs.add(fileName);
        }

        return imgs;
        
    }
}
