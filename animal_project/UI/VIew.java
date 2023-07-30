package animal_project.UI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import animal_project.Controller.Controller;
import animal_project.Model.PetСounter;

public class VIew {

  public void Menu() {
    boolean flag = true;
    try (Scanner in = new Scanner(System.in, "UTF-8")) {
      while (flag) {
        System.out.println("\n1 - Завести новое животное\n2 - Список команд, которые выполняет животное\n3 - Добавить новую команду\n4 - Удалить запись о животном\n5 - Выход");
        String key = in.next();
        switch (key){
          case "1":
            // 1 - Завести новое животное
            String petType = choicePetType(in);
            in.nextLine();
            System.out.println("Введите имя животного: ");
            String nickname = in.nextLine();
            System.out.println("Дату рождения животного в формате (дд.мм.гггг): ");
            String dateBirth = in.nextLine();
            System.out.println("Введите окрас животного: ");
            String color  = in.nextLine();
            System.out.println("Введите комманды, которые знает животное (через запятую): ");
            String comandString  = in.nextLine();
            String  delimeter = "\\,"; // Разделитель
            String[] subStr = comandString.split(delimeter);
            List<String> commands  = new ArrayList<>();
            for (int i = 0; i < subStr.length; i++) {
              commands.add(subStr[i]);
            }
            if (!petType.isEmpty() && !nickname.isEmpty() && !dateBirth.isEmpty()) {
              try(PetСounter counter = new PetСounter()){
                Controller.put(Controller.createPet(petType, nickname, dateBirth, color, commands));
                counter.add();
                System.out.println("Животное добавлено");
              } catch (Exception e) {
                System.out.println("Ошибка при добавлении животного.");
              }
            } else {
              System.out.println("Не все обязательные поля заполнены. Животное не добавлено в реестр.");
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
            flag = false;
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
