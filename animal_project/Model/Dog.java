package animal_project.Model;

import java.util.List;

public class Dog extends PetAnimal{

  public Dog(String type, String nickname, String dateBirth, String color, List<String> commands) {
    super("Dog", nickname, dateBirth, color, commands);
  }

  @Override
  public String getName() {
    return "Dog";
  }
}
