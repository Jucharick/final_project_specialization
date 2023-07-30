package animal_project.Model;

import java.util.ArrayList;

public class Hamster extends PetAnimal{
  public Hamster() {
    super("Hamster", "", "", "", new ArrayList<>());
  }

  @Override
  public String getName() {
    return "Hamster";
  }
}
