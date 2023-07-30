package animal_project.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import animal_project.Model.Cat;
import animal_project.Model.Dog;
import animal_project.Model.Hamster;
import animal_project.Model.PetAnimal;
import animal_project.Model.PetСounter;
import animal_project.UI.MainMenu;

public class Controller {

  public static void createPet (String petType, String nickname, String dateBirth, String color, List<String> commands) {
    if (!nickname.isEmpty() && !dateBirth.isEmpty() && !color.isEmpty()) {
      try (PetСounter counter = new PetСounter()) {
        counter.add(); 
        switch (petType) {
          case "Cat":
            Cat newCat = new Cat(petType, nickname, dateBirth, color, commands);
            break;
          case "Dog":
            Dog newDog = new Dog(petType, nickname, dateBirth, color, commands);
            break;
          case "Hamster":
            Hamster newHamster = new Hamster(petType, nickname, dateBirth, color, commands);
            break;
          default:
            System.out.println("Введено некоррекное значение");
            return;
        }
      }
      catch (IOException e) {
          System.out.printf("Ошибка: %s", e.getMessage());
      }
    }
  }
}

