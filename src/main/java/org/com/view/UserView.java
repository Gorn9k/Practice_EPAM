package org.com.view;

import org.com.controller.UserController;
import org.com.entity.User;
import org.com.security.AuthenticationManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class UserView {

    @Autowired
    private UserController userController;
    Scanner scanner = new Scanner(System.in);
    AtomicBoolean exception = new AtomicBoolean(false);

    public void showAllUsers(){
        List<User> users = userController.getAllUsers();
        System.out.println("\nСписок пользователей:\n");
        users.forEach(user -> System.out.println(user.toString()));
    }

    public void showUserById(){
        System.out.println("\nВведите Id пользователя, которого хотите найти:");
        try {
            System.out.println(userController.getUserById(scanner.nextLong()).toString());
        } catch (Exception e){
            System.out.println("\nНекорректно введен Id или же пользователь с таким идентификатором отсутствует");
        }
    }

    public void save_User(){
        List<User> users = userController.getAllUsers();
        System.out.println("\nВведите имя пользователя:");
        String login = scanner.next();
        System.out.println("Введите пароль:");
        String password = scanner.next();
        users.forEach(user -> {
            if (user.getLogin().equals(login)) {
                System.out.println("\nОшибка! Такое имя уже существует.");
                exception.set(true);
                return;
            }
            if (new BCryptPasswordEncoder().matches(password, user.getPassword())){
                System.out.println("\nОшибка! Такой пароль уже существует.");
                exception.set(true);
                return;
            }
        });
        if (!exception.get()){
            System.out.println("\nВыберите роль (права доступа) пользователя:\n1. Admin\n2. User");
            switch (scanner.nextInt()){
                case 1:
                    userController.saveUser(User.builder()
                                .login(login)
                                .password(password)
                                .authority("ROLE_ADMIN")
                                .build());
                case 2:
                    userController.saveUser(User.builder()
                                .login(login)
                                .password(password)
                                .authority("ROLE_USER")
                                .build());
            }
            System.out.println("\nРегистрация пользователя прошла успешно!");
        }
    }

    public void edit_User(){
        List<User> users;
        System.out.println("\nВыберите действие:" +
                "\n1. Изменить имя пользователя" +
                "\n2. Изменить пароль" +
                "\n3. Изменить имя и пароль" +
                "\n4. Выйти");
        String login = null;
        String password = null;
        switch (scanner.nextInt()) {
            case 1:
                System.out.println("\nВведите новое имя пользователя:");
                login = scanner.next();
                users = userController.getAllUsers();
                String finalLogin = login;
                users.forEach(user -> {
                    if (user.getLogin().equals(finalLogin)) {
                        System.out.println("\nОшибка! Такое имя уже существует.");
                        exception.set(true);
                        return;
                    }
                });
                break;
            case 2:
                System.out.println("\nВведите новый пароль:");
                password = scanner.next();
                users = userController.getAllUsers();
                String finalPassword = password;
                users.forEach(user -> {
                    if (new BCryptPasswordEncoder().matches(finalPassword, user.getPassword())){
                        System.out.println("\nОшибка! Такой пароль уже существует.");
                        exception.set(true);
                        return;
                    }
                });
                break;
            case 3:
                System.out.println("\nВведите новое имя пользователя:");
                login = scanner.next();
                System.out.println("Введите новый пароль:");
                password = scanner.next();
                users = userController.getAllUsers();
                String finalLogin1 = login;
                String finalPassword1 = password;
                users.forEach(user -> {
                    if (user.getLogin().equals(finalLogin1)) {
                        System.out.println("\nОшибка! Такое имя уже существует.");
                        exception.set(true);
                        return;
                    }
                    if (new BCryptPasswordEncoder().matches(finalPassword1, user.getPassword())){
                        System.out.println("\nОшибка! Такой пароль уже существует.");
                        exception.set(true);
                        return;
                    }
                });
                break;
            case 4:
                break;
            default:
                System.out.println("\nНекорректное число! Введите число от 1 до 4");
        }
        if (!exception.get()) {
            userController.editUser(User.builder()
                    .id(userController.getUserByLogin(
                            SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()).getId())
                    .login(login)
                    .password(password)
                    .build());
            logout();
            System.out.println("\nВаши данные успешно изменены!" +
                    " Вы были выброшены из системы для вступления измененных данных в силу. Если хотите - зайдите повторно.");
        }
    }

    public void delete_User(){
        System.out.println("\nВведите Id пользователя, которого хотите удалить:");
        try {
            userController.deleteUser(scanner.nextLong());
            System.out.println("\nУдаление прошло успешно!");
        } catch (Exception e){
            System.out.println("\nНекорректно введен Id или же пользователь с таким идентификатором отсутствует");
        }
    }

    public void showInfoAboutYourAcc(){
        System.out.println("\nВаши данные:\n" + userController.getUserByLogin(
                SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()).toString());
    }

    public boolean login(ConfigurableApplicationContext context){
        System.out.println("\nВведите имя пользователя:");
        String login = scanner.next();
        System.out.println("Введите пароль:");
        String password = scanner.next();
        AuthenticationManager authenticationManager = context.getBean(AuthenticationManagerImpl.class);
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
        } catch (UsernameNotFoundException e) {
            System.out.println("\nОшибка! Пользователя с таким именем не существует.");
            return false;
        } catch (BadCredentialsException e) {
            System.out.println("\nОшибка! Пользователя с таким паролем не существует.");
            return false;
        }
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(authentication);
        if(sc.getAuthentication().isAuthenticated()) {
            System.out.println("\nLogin successful!\nHello, " + login + "!");
            return true;
        }
        else {
            System.out.println("\nBad credentials!");
            return false;
        }
    }

    public void logout(){
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    public boolean run() {
        boolean proceed = true;
        boolean wasLogout = false;
        while (proceed) {
            exception.set(false);
            if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().equals("[ROLE_ADMIN]")) {
                System.out.println("\nВыберите действие, которое вы хотите совершить:" +
                        "\n1. Получить информацию вашего аккаунта" +
                        "\n2. Изменить данные вашего аккаунта" +
                        "\n3. Получить список пользователей" +
                        "\n4. Получить пользователя по Id" +
                        "\n5. Создать пользователя" +
                        "\n6. Удалить пользователя" +
                        "\n7. Выйти" +
                        "\n8. Выйти из системы");
                switch (scanner.nextInt()) {
                    case 1:
                        showInfoAboutYourAcc();
                        break;
                    case 2:
                        edit_User();
                        if (!exception.get()) {
                            wasLogout = true;
                            proceed = false;
                        }
                        break;
                    case 3:
                        showAllUsers();
                        break;
                    case 4:
                        showUserById();
                        break;
                    case 5:
                        save_User();
                        break;
                    case 6:
                        delete_User();
                        break;
                    case 7:
                        proceed = false;
                        break;
                    case 8:
                        logout();
                        wasLogout = true;
                        proceed = false;
                        break;
                    default:
                        System.out.println("\nНекорректное число! Введите число от 1 до 7");
                }
            }
            else {
                System.out.println("\nВыберите действие, которое вы хотите совершить:" +
                        "\n1. Получить информацию вашего аккаунта" +
                        "\n2. Изменить данные вашего аккаунта" +
                        "\n3. Выйти" +
                        "\n4. Выйти из системы");
                switch (scanner.nextInt()) {
                    case 1:
                        showInfoAboutYourAcc();
                        break;
                    case 2:
                        edit_User();
                        if (!exception.get()) {
                            wasLogout = true;
                            proceed = false;
                        }
                        break;
                    case 3:
                        proceed = false;
                        break;
                    case 4:
                        logout();
                        wasLogout = true;
                        proceed = false;
                        break;
                    default:
                        System.out.println("\nНекорректное число! Введите число от 1 до 4");
                }
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
        return wasLogout;
    }
}
