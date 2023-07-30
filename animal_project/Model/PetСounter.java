package animal_project.Model;

import java.io.FileWriter;
import java.io.IOException;

public class PetСounter implements AutoCloseable {
  private int counter = 0;
  private boolean isClose;
  private FileWriter fileWriter; // Внешний ресурс - файловый поток

  public PetСounter() throws IOException {
    this.counter = 0;
    this.isClose = false;
    fileWriter = new FileWriter("registry.txt"); // Создаем файловый поток для записи в файл "registry.txt"
  }

  public PetСounter (int count) throws IOException{
    this.isClose = true;
    this.counter = count;
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
      // Выполняем освобождение ресурса - закрываем файловый поток
      fileWriter.close();

      isClose = true;
      System.out.println("Ресурс закрыт. Значение счетчика: " + counter);
    } else {
      // Бросаем исключение, если метод close() вызывается повторно или ресурс уже закрыт
      throw new IllegalStateException("Ресурс уже закрыт.");
    }
  }
}


