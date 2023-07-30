package animal_project.Model;

import java.util.List;

public class Hamster extends PetAnimal{

  public Hamster(String type, String nickname, String dateBirth, String color, List<String> commands) {
    super("Hamster", nickname, dateBirth, color, commands);
  }

  @Override
  public String getName() {
    return "Hamster";
  }
}
