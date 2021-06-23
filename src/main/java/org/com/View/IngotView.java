package org.com.View;

import org.com.Controller.IngotController;
import org.com.Entity.Ingot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Scanner;

@Component
public class IngotView {

    @Autowired
    private IngotController ingotController;
    Scanner scanner = new Scanner(System.in);

    public void showAllIngotsOnMetalsByDate(){
        System.out.println("\nВведите дату, на которую запращивается цена (формат даты: 2021-06-23):");
        String date = scanner.next();
        List<Ingot> ingots = ingotController.getAllIngotsOnMetalsByDate(date);
        System.out.println("\nСписок цен на драгоценные металлы в виде банковских слитков на " + date + " :\n");
        ingots.forEach(ingot -> System.out.println(ingot.toString()));
    }

    public void showIngotsOnMetalsByIdAndDate(){
        System.out.println("\nВведите Id металла, цену в виде банковских слитков на который вы хотите получить:");
        Long id = scanner.nextLong();
        System.out.println("\nВведите дату, на которую запращивается цена (формат даты: 2021-06-23):");
        String date = scanner.next();
        List<Ingot> ingots = null;
        try {
            ingots = ingotController.getIngotsOnMetalsByIdAndDate(id, date);
        } catch (Exception e){
            System.out.println("\nНекорректно введен Id");
        }
        if (!ingots.isEmpty()) {
            System.out.println("\nСписок цен на конкретный металл в виде банковских слитков на " + date + " :\n");
            ingots.forEach(ingot -> System.out.println(ingot.toString()));
        } else System.out.println("\nСписок цен на металл с таким ID отсутствует");
    }

    public void run() {
        boolean proceed = true;
        while (proceed) {
            System.out.println("\nВыберите действие, которое вы хотите совершить:\n" +
                    "1. Получить цены на драгоценные металлы в виде банковских слитков\n" +
                    "2. Получить цены на конкретный металл в виде банковских слитков\n3. Выйти");
            switch (scanner.nextInt()) {
                case 1:
                    showAllIngotsOnMetalsByDate();
                    break;
                case 2:
                    showIngotsOnMetalsByIdAndDate();
                    break;
                case 3:
                    proceed = false;
                    break;
                default:
                    System.out.println("\nНекорректное число! Введите число от 1 до 3");
            }
            if (proceed) {
                System.out.println("\nХотите продолжить?\n1. Да\n2. Нет");
                switch (scanner.nextInt()) {
                    case 1:
                        break;
                    case 2:
                        proceed = false;
                        break;
                    default:
                        System.out.println("\nНекорректное число! Введите число от 1 до 2");
                }
            }
        }
    }
}
