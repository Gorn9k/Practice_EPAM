package org.com.view;

import org.com.controller.MetalController;
import org.com.entity.Metal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Scanner;

@Component
public class MetalView {

    @Autowired
    private MetalController metalController;
    Scanner scanner = new Scanner(System.in);

    public void showAllMetals(){
        List<Metal> metals = metalController.getAllMetals();
        System.out.println("\nСписок металлов:\n");
        metals.forEach(metal -> System.out.println(metal.toString()));
    }

    public void showMetalById(){
        System.out.println("\nВведите Id металла, который хотите найти:");
        try {
            System.out.println(metalController.getMetalById(scanner.nextLong()).toString());
        } catch (Exception e){
            System.out.println("\nНекорректно введен Id или же металл с таким идентификатором отсутствует");
        }
    }

    public void run() {
        boolean proceed = true;
        while (proceed) {
            System.out.println("\nВыберите действие, которое вы хотите совершить:\n" +
                    "1. Получить полный перечень\n2. Получить информацию о конкретном металле по Id\n" +
                    "3. Выйти");
            try {
                switch (scanner.nextInt()) {
                    case 1:
                        showAllMetals();
                        break;
                    case 2:
                        showMetalById();
                        break;
                    case 3:
                        proceed = false;
                        break;
                    default:
                        System.out.println("\nНекорректное число! Введите число от 1 до 3");
                }
            }
            catch (AccessDeniedException accessDeniedException){
                System.out.println("\nОшибка доступа");
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
