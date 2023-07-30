package animal_project.Model;

import java.util.ArrayList;

public class Cat extends PetAnimal{
  public Cat() {
    super("Cat", "", "", "", new ArrayList<>());
  }

  @Override
  public String getName() {
    return "Cat";
  }
}