package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User User1 = new User("User1", "Lastname1", "user1@mail.ru");
      User User2 = new User("User2", "Lastname2", "user2@mail.ru");
      User User3 = new User("User3", "Lastname3", "user3@mail.ru");
      User User4 = new User("User4", "Lastname4", "user4@mail.ru");


      Car reno = new Car("reno", 10);
      Car subaru = new Car("subaru", 14);
      Car bmw = new Car("bmw", 11);
      Car tank = new Car("tank", 8);

      User1.setCar(reno);
      User2.setCar(subaru);
      User3.setCar(bmw);
      User4.setCar(tank);

      userService.add(User1);
      userService.add(User2);
      userService.add(User3);
      userService.add(User4);

      List<User> users = userService.listUsers();
      for (User user : users) {

         System.out.println(user + " "+ user.getCar());
      }
      System.out.println(userService.getUserByCar("tank",8));
      context.close();
   }



}
