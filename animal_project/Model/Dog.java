package animal_project.Model;

import java.util.ArrayList;

public class Dog extends PetAnimal{
  public Dog(String type) {
    super("Dog", "", "", "", new ArrayList<>());
  }

  @Override
  public String getName() {
    return "Dog";
  }
}
