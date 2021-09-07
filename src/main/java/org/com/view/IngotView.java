package org.com.view;

import feign.FeignException;
import org.com.controller.IngotController;
import org.com.entity.Ingot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
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
        List<Ingot> ingots;
        try {
            ingots = ingotController.getAllIngotsOnMetalsByDate(date);
        } catch (FeignException feignException){
            System.out.println("\nСписок цен на драгоценные металлы в виде банковских слитков на данный период отсутствует либо дата введена неверно");
            return;
        }
        if (!ingots.isEmpty()) {
            System.out.println("\nСписок цен на драгоценные металлы в виде банковских слитков на " + date + " :\n");
            ingots.forEach(ingot -> System.out.println(ingot.toString()));
        } else System.out.println("\nСписок цен на металл на этот период отсутствует");
    }

    public void showIngotsOnMetalsByIdAndDate(){
        System.out.println("\nВведите Id металла, цену в виде банковских слитков на который вы хотите получить:");
        Long id = null;
        try {
            id = scanner.nextLong();
        } catch (InputMismatchException inputMismatchException){
            System.out.println("\nНекорректно введен Id");
            scanner.nextLine();
            return;
        }
        System.out.println("\nВведите дату, на которую запрашивается цена (формат даты: 2021-06-23):");
        String date = scanner.next();
        List<Ingot> ingots;
        try {
            ingots = ingotController.getIngotsOnMetalsByIdAndDate(id, date);
        } catch (FeignException feignException){
            System.out.println("\nНекорректно введена дата");
            return;
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
                    "2. Получить цены на конкретный металл в виде банковских слитков\n3. Вернуться в главное меню");
            try {
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
            } catch (InputMismatchException inputMismatchException){
                System.out.println("\nОшибка! Необходимо ввести число.");
                scanner.nextLine();
            } catch (AccessDeniedException accessDeniedException){
                System.out.println("\nОшибка доступа");
            }
            if (proceed) {
                System.out.println("\nХотите продолжить?\n1. Да\n2. Нет");
                try {
                    switch (scanner.nextInt()) {
                        case 1:
                            break;
                        case 2:
                            proceed = false;
                            break;
                        default:
                            System.out.println("\nНекорректное число! Введите число от 1 до 2");
                    }
                } catch (InputMismatchException inputMismatchException){
                    System.out.println("\nОшибка! Необходимо ввести число.");
                    scanner.nextLine();
                    return;
                }
            }
        }
    }
}
