package animal_project.Model;

import java.util.List;

public abstract class PetAnimal implements Interface {
  protected String type;
  protected String nickname;
  protected String dateBirth;
  protected String color;
  protected List<String> commands;

  public PetAnimal(String type, String nickname, String dateBirth, String color, List<String> commands) {
    this.type = type;
    this.nickname = nickname;
    this.dateBirth = dateBirth;
    this.color = color;
    this.commands = commands;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getNickname() {
    return nickname;
  }

  public String getDateBirth() {
    return dateBirth;
  }

  public String getColor() {
    return color;
  }

  public List<String> getCommands() {
    return commands;
  }

  @Override
    public void getInfo() {
        System.out.printf("Type: %s Nickname: %s Birth: %s Color: %s Commands: %l  \n",
                          this.type, this.nickname, this.dateBirth, this.color, this.commands);
    }
}