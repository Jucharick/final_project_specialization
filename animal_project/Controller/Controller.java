package animal_project.Controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import animal_project.Model.Cat;
import animal_project.Model.Dog;
import animal_project.Model.Hamster;
import animal_project.Model.PetAnimal;
import animal_project.Model.PetСounter;

public class Controller {

  public static PetAnimal createPet (String petType, String nickname, String dateBirth, String color, List<String> commands) {
    if (!nickname.isEmpty() && !dateBirth.isEmpty() && !color.isEmpty()) {
      try (PetСounter counter = new PetСounter()) {
        counter.add(); 
        switch (petType) {
          case "Cat":
            Cat newCat = new Cat(petType, nickname, dateBirth, color, commands);
            return newCat;
          case "Dog":
            Dog newDog = new Dog(petType, nickname, dateBirth, color, commands);
            return newDog;
          case "Hamster":
            Hamster newHamster = new Hamster(petType, nickname, dateBirth, color, commands);
            return newHamster;
          default:
            System.out.println("Введено некоррекное значение");
            return null;
        }
      }
      catch (IOException e) {
          System.out.printf("Ошибка: %s", e.getMessage());
      }
    }
    return null;
  }

  // запись в файл 
  public static void put(PetAnimal animal) {
    FileWriter file = null;
    try {
      file = new FileWriter("animal_project/registry.txt", true);
      file.append(animal.getStr());
    } catch (IOException ex) {
      System.err.println("Ошибка: " + ex);
    } finally {
      if (file != null) {
        try {
          file.close();
        } catch (IOException ex) {
          System.err.println("Ошибка: " + ex);
        }
      }
    }
  }

}

