package org.com.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class MainView {

    @Autowired
    private MetalView metalView;
    @Autowired
    private IngotView ingotView;
    @Autowired
    private UserView userView;

    public void runMainView(ConfigurableApplicationContext context){
        Scanner scanner = new Scanner(System.in);
        boolean proceed = true;
        boolean inSystem = false;
        System.out.println("\nЗдравствуйте!");
        while (proceed) {
            if (!inSystem) {
                System.out.println("\nДля продолжения работы с приложением необходимо войти в систему. " +
                        "Выберите опцию для операции (введите число):\n" +
                        "1. Войти в систему\n" +
                        "2. Завершить работу приложения");
            } else {
                System.out.println("\nВыберите опцию для операции (введите число):\n" +
                        "1. Войти в профиль\n" +
                        "2. Просмотреть варианты получения перечня драгоценных металлов\n" +
                        "3. Просмотреть варианты получения списка цен на покупки и продажи Национальным банком Республики Беларусь драгоценных " +
                        "металлов в виде мерных слитков на определенную дату\n4. Завершить работу приложения");
            }
            switch (scanner.nextInt()) {
                case 1:
                    if (inSystem)
                        inSystem = !userView.run();
                    else
                        inSystem = userView.login(context);
                    break;
                case 2:
                    if (inSystem)
                        metalView.run();
                    else
                        proceed = false;
                    break;
                case 3:
                    if (inSystem)
                        ingotView.run();
                    else
                        System.out.println("\nНекорректное число! Введите число от 1 до 2.");
                    break;
                case 4:
                    if (inSystem)
                        proceed = false;
                    else
                        System.out.println("\nНекорректное число! Введите число от 1 до 2.");
                    break;
                default:
                    if (inSystem)
                        System.out.println("\nНекорректное число! Введите число от 1 до 4.");
                    else
                        System.out.println("\nНекорректное число! Введите число от 1 до 2.");
            }
        }
    }
}
