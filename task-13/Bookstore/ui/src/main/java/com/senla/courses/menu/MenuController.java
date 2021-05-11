package com.senla.courses.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class MenuController {

    private final Builder builder;
    private final Navigator navigator;

    public void run(){
        Scanner scan = new Scanner(System.in);
        builder.buildMenu();
        navigator.setCurrentMenu(builder.getRootMenu());
        navigator.printMenu();
        Integer index = -1;
        while (!index.equals(0)){
            index = scan.nextInt();
            navigator.navigate(index);
            navigator.printMenu();
        }
        scan.close();
    }
}
