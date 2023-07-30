package animal_project.Model;

import java.io.FileWriter;
import java.io.IOException;

public class PetСounter implements AutoCloseable {
  private int counter = 0;
  private boolean isClose;

  public PetСounter() throws IOException {
    this.counter = 0;
    this.isClose = false;
  }

  public int add() throws IOException{
    if (isClose) {
      throw new IOException("Счётчик закрыт");
    }
    return counter++;
  }

  public int getCount(){
    return counter;
  }

  @Override
  public void close() throws IOException {
    if (!isClose) {
      isClose = true;
      //System.out.println("Значение счетчика: " + counter);
    } else {
      throw new IllegalStateException("Счетчик закрыт.");
    }
  }
}


