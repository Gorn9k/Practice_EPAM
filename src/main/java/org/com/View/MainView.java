package org.com.View;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class MainView {

    @Autowired
    private MetalView metalView;
    @Autowired
    private IngotView ingotView;

    public void runMainView(){
        Scanner scanner = new Scanner(System.in);
        boolean proceed = true;
        while (proceed) {
            System.out.println("\nВыберите опцию для операции (введите число):\n" +
                    "1. Получение перечня драгоценных металлов\n" +
                    "2. Получение цен покупки и продажи Национальным банком Республики Беларусь драгоценных " +
                    "металлов в виде мерных слитков на определенную дату\n3. Выход");
            switch (scanner.nextInt()) {
                case 1:
                    metalView.run();
                    break;
                case 2:
                    ingotView.run();
                    break;
                case 3:
                    proceed = false;
                    break;
                default:
                    System.out.println("\nНекорректное число! Введите число от 1 до 3");
            }
        }
    }
}
