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

      Car car1 = new Car("Car one", 10);
      Car car2 = new Car("Car two", 20);
      Car car3 = new Car("Car three", 30);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Василий", "Пупкин", "mail_1@mail.ru", car1);
      User user2 = new User("Пупок", "Васильев", "mail_2@mail.ru", car2);
      User user3 = new User("Kek", "Lolov", "mail_3@mail.ru", car3);
      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      List<User> users = userService.listUsers();

      for (User user4 : users) {
         System.out.println("Id = "+user4.getId());
         System.out.println("First Name = "+user4.getFirstName());
         System.out.println("Last Name = "+user4.getLastName());
         System.out.println("Email = "+user4.getEmail());
         System.out.println();
      }

      System.out.println(userService.getbyCar(car1));

      context.close();
   }
}
