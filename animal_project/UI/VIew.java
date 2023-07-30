package animal_project.UI;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import animal_project.Controller.Controller;
import animal_project.Model.PetAnimal;
import animal_project.Model.PetСounter;

public class VIew {
  private List<PetAnimal> allAnimals = new ArrayList<PetAnimal>();
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
                PetAnimal newAnimal = Controller.createPet(petType, nickname, dateBirth, color, commands);
                if (newAnimal!=null) {
                  allAnimals.add(newAnimal);
                  counter.add();
                  System.out.println("Животное добавлено");
                }
              } catch(Exception e) {
                System.out.println("Ошибка при добавлении животного." + e);
              }
            } else {
              System.out.println("Не все обязательные поля заполнены. Животное не добавлено в реестр.");
            }
            break;
          case "2":
            // 2 - Список команд, которые выполняет животное
            PetAnimal find = findPet(in);
            System.out.println("Животное выполняет следующие команды: " + find.getCommands().toString());
            break;
          case "3":
            // 3 - Добавить новую команду
            PetAnimal findPetToUpd = findPet(in);
            PetAnimal updPet = addNewComand(in,findPetToUpd);
            System.out.println("Команда добавлена.");
            System.out.println("Животное выполняет следующие команды: " + updPet.getCommands().toString());
            break;
          case "4":
            // 4 - Удалить запись о животном
            try {
              delPet(in);
            } catch (Exception e) {
              System.err.println("Ошибка: " + e);
            }
            break;
          case "5":
            // 5 - Выход
            for (int i = 0; i < allAnimals.size(); i++) {
              Controller.put(allAnimals.get(i));
            }
            System.out.println("Изменения сохранены в реестр.");
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

  public PetAnimal findPet(Scanner in) {
    System.out.print("Введите имя животного: ");
    String nickname = in.next();
    for (int i = 0; i < allAnimals.size(); i++) {
      if (allAnimals.get(i).getNickname().equals(nickname)) {
        System.out.println("Найдено животное: " );
        allAnimals.get(i).getInfo();
        return allAnimals.get(i);
      }
    } 
    System.out.println("Такого животного нет.");
    return null;
  }

  public PetAnimal addNewComand (Scanner in, PetAnimal pet) {
    System.out.print("Введите новую команду: ");
    String newComand = in.next();
    List<String> command = pet.getCommands();
    command.add(newComand);
    pet.setCommands(command);
    return pet;
  }

  public void delPet (Scanner in) {
    System.out.print("Введите имя животного для удаления: ");
    String nickname = in.next();
    for (int i = 0; i < allAnimals.size(); i++) {
      if (allAnimals.get(i).getNickname().equals(nickname)) {
        System.out.println("Найдено животное: " );
        allAnimals.get(i).getInfo();
        System.out.println("Вы уверены что хотите удалить? y/n");
        String userAnswer = in.next();
        if (userAnswer.toLowerCase().equals("y")) {
          allAnimals.remove(i);
          System.out.println("Животнное удалено успешно.");
        }
      }
    }
  }
}
