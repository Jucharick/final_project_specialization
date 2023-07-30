package animal_project.Model;

import java.util.List;

public class Cat extends PetAnimal{

  public Cat(String type, String nickname, String dateBirth, String color, List<String> commands) {
    super("Cat", nickname, dateBirth, color, commands);
  }

  @Override
  public String getName() {
    return "Cat";
  }
}