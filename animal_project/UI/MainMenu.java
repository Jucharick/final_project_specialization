package animal_project.UI;

import java.util.Scanner;

import org.w3c.dom.css.Counter;

import animal_project.Controller.Controller;
import animal_project.Model.Cat;
import animal_project.Model.PetAnimal;
import animal_project.Model.Сounter;

public class MainMenu {

  public void Menu() {
    int count = 0;
    boolean flag = true;
    try (Scanner in = new Scanner(System.in)) {
      while (flag) {
        System.out.println("\n1 - Завести новое животное\n2 - Список команд, которые выполняет животное\n3 - Добавить новую команду\n4 - Удалить запись о животном\n5 - Выход");
        String key = in.next();
        switch (key){
          case "1":
            // 1 - Завести новое животное
            String petType = choicePetType(in);
            if (petType != null) {
              try {
                Controller.createPet(petType);
                count = Counter.add();
                System.out.println("ОК");
              } catch (UncorrectDataException e) {
                  System.out.println(e.getMessage());
              }
            }
            break;
          case "2":
            // 2 - Список команд, которые выполняет животное
            break;
          case "3":
            // 3 - Добавить новую команду
            break;
          case "4":
            // 4 - Удалить запись о животном
            break;
          case "5":
            // 5 - Выход
            break;
          default:
            System.out.println("Вы ввели некорректное значение");
        }
      }
    }
  }

  private String choicePetType(Scanner in) {
    System.out.println("Какое животное добавить:\n1 - Cat\n2 - Dog\n3 - Hamster\n0 - Return");
    while (true) {
      String key = in.next();
      switch (key) {
        case "1":
          return "Cat";
        case "2":
          return "Dog";
        case "3":
          return "Hamster";
        case "0":
            return null;
        default:
          System.out.println("Вы ввели некоррекное значение, введите число от 0 до 3");
          break;
      }
    }
  }
}
