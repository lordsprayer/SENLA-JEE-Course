package com.senla.courses.ui.action.validation;

import com.senla.courses.ui.action.AbstractAction;

import java.util.Scanner;

public class IntNumberValidation {

    public static Integer validation(String str){
        Scanner scan = new Scanner(System.in);
        int id;
        while (true) {
            System.out.println(str);
            try {
                id = Integer.parseInt(scan.next());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод, попробуйте ещё раз");
            }
        }
        return id;
    }
}
