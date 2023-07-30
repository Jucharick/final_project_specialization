package animal_project.Controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import animal_project.Model.Cat;
import animal_project.Model.Dog;
import animal_project.Model.Hamster;
import animal_project.Model.PetAnimal;
import animal_project.Model.PetСounter;
import animal_project.UI.VIew;

public class Controller {

  public static String createPet (String petType, String nickname, String dateBirth, String color, List<String> commands) {
    if (!nickname.isEmpty() && !dateBirth.isEmpty() && !color.isEmpty()) {
      try (PetСounter counter = new PetСounter()) {
        counter.add(); 
        switch (petType) {
          case "Cat":
            Cat newCat = new Cat(petType, nickname, dateBirth, color, commands);
            String strCat = newCat.getStr();
            return strCat;
          case "Dog":
            Dog newDog = new Dog(petType, nickname, dateBirth, color, commands);
            String strDog = newDog.getStr();
            return strDog;
          case "Hamster":
            Hamster newHamster = new Hamster(petType, nickname, dateBirth, color, commands);
            String strHam = newHamster.getStr();
            return strHam;
          default:
            System.out.println("Введено некоррекное значение");
            return "";
        }
      }
      catch (IOException e) {
          System.out.printf("Ошибка: %s", e.getMessage());
      }
    }
    return "";
  }

  // запись в файл 
  public static void put(String str) {
    FileWriter file = null;
    try {
      file = new FileWriter("animal_project/registry.txt", true);
      file.append(str);
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

