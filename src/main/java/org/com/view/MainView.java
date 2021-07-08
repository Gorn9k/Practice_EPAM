package org.com.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
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
        while (proceed) {
            if (!inSystem) {
                System.out.println("\nВыберите опцию для операции (введите число):\n" +
                        "1. Войти в систему\n" +
                        "2. Получение перечня драгоценных металлов\n" +
                        "3. Получение цен покупки и продажи Национальным банком Республики Беларусь драгоценных " +
                        "металлов в виде мерных слитков на определенную дату\n4. Выход");
            } else {
                System.out.println("\nВыберите опцию для операции (введите число):\n" +
                        "1. Управление пользователем(ями)\n" +
                        "2. Получение перечня драгоценных металлов\n" +
                        "3. Получение цен покупки и продажи Национальным банком Республики Беларусь драгоценных " +
                        "металлов в виде мерных слитков на определенную дату\n4. Выход");
            }
            try {
                switch (scanner.nextInt()) {
                    case 1:
                        if (inSystem)
                            inSystem = !userView.run();
                        else
                            inSystem = userView.login(context);
                        break;
                    case 2:
                        metalView.run();
                        break;
                    case 3:
                        ingotView.run();
                        break;
                    case 4:
                        proceed = false;
                        break;
                    default:
                        System.out.println("\nНекорректное число! Введите число от 1 до 4");
                }
            }
            catch (AuthenticationCredentialsNotFoundException exception){
                System.out.println("\nДля того чтобы выполнить это действие необходимо войти в систему!");
            }
        }
    }
}
