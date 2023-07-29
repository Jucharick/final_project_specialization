-- ------------------------------------------------------------
-- 7. В подключенном MySQL репозитории создать базу данных “Друзья
-- человека”
DROP DATABASE IF EXISTS Human_friends;
CREATE DATABASE Human_friends;
USE Human_friends;



-- ------------------------------------------------------------
-- 8. Создать таблицы с иерархией из диаграммы в БД
-- создаю таблицу class_animal
CREATE TABLE class_animal
(
  Id INT AUTO_INCREMENT PRIMARY KEY, 
  Class_name VARCHAR(20)
);

-- заполняю таблицу class_animal
INSERT INTO class_animal (Class_name) VALUES ('class_pet'),('class_pack');  



-- создаю таблицу class_pet
CREATE TABLE class_pet
(
  Id INT AUTO_INCREMENT PRIMARY KEY, 
  Type_of_animal VARCHAR(20),
  Class_animal_id INT,
  FOREIGN KEY (Class_animal_id) REFERENCES class_animal (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- заполняю таблицу class_pet
INSERT INTO class_pet (Type_of_animal, Class_animal_id) VALUES ('Cat', 1);
INSERT INTO class_pet (Type_of_animal, Class_animal_id) VALUES ('Dog', 1);
INSERT INTO class_pet (Type_of_animal, Class_animal_id) VALUES ('Hamster', 1);



-- создаю таблицу class_pack
CREATE TABLE class_pack
(
  Id INT AUTO_INCREMENT PRIMARY KEY, 
  Type_of_animal VARCHAR(20),
  Class_animal_id INT,
  FOREIGN KEY (Class_animal_id) REFERENCES class_animal (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- заполняю таблицу class_pack
INSERT INTO class_pack (Type_of_animal, Class_animal_id) VALUES ('Horse', 2);
INSERT INTO class_pack (Type_of_animal, Class_animal_id) VALUES ('Donkey', 2);
INSERT INTO class_pack (Type_of_animal, Class_animal_id) VALUES ('Camel', 2);



-- создаю таблицу cat
CREATE TABLE cat
(
  Id INT AUTO_INCREMENT PRIMARY KEY, 
  Nickname VARCHAR(20),
  Birthday DATE,
  Color VARCHAR(10),
  Commands VARCHAR(50),
  Type_of_animal_id INT,
  FOREIGN KEY (Type_of_animal_id) REFERENCES class_pet (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- создаю таблицу dog
CREATE TABLE dog
(
  Id INT AUTO_INCREMENT PRIMARY KEY, 
  Nickname VARCHAR(20),
  Birthday DATE,
  Color VARCHAR(10),
  Commands VARCHAR(50),
  Type_of_animal_id INT,
  FOREIGN KEY (Type_of_animal_id) REFERENCES class_pet (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- создаю таблицу hamster
CREATE TABLE hamster
(
  Id INT AUTO_INCREMENT PRIMARY KEY, 
  Nickname VARCHAR(20),
  Birthday DATE,
  Color VARCHAR(10),
  Commands VARCHAR(50),
  Type_of_animal_id INT,
  FOREIGN KEY (Type_of_animal_id) REFERENCES class_pet (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- создаю таблицу horse
CREATE TABLE horse
(
  Id INT AUTO_INCREMENT PRIMARY KEY, 
  Nickname VARCHAR(20),
  Birthday DATE,
  Color VARCHAR(10),
  Commands VARCHAR(50),
  Type_of_animal_id INT,
  FOREIGN KEY (Type_of_animal_id) REFERENCES class_pack (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- создаю таблицу donkey
CREATE TABLE donkey
(
  Id INT AUTO_INCREMENT PRIMARY KEY, 
  Nickname VARCHAR(20),
  Birthday DATE,
  Color VARCHAR(10),
  Commands VARCHAR(50),
  Type_of_animal_id INT,
  FOREIGN KEY (Type_of_animal_id) REFERENCES class_pack (Id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- создаю таблицу camel
CREATE TABLE camel
(
  Id INT AUTO_INCREMENT PRIMARY KEY, 
  Nickname VARCHAR(20),
  Birthday DATE,
  Color VARCHAR(10),
  Commands VARCHAR(50),
  Type_of_animal_id INT,
  FOREIGN KEY (Type_of_animal_id) REFERENCES class_pack (Id) ON DELETE CASCADE ON UPDATE CASCADE
);



-- ------------------------------------------------------------
-- 9. Заполнить низкоуровневые таблицы именами(животных), командами которые они выполняют и датами рождения
INSERT INTO cat (Nickname, Birthday, Color, Commands, Type_of_animal_id) VALUES ('Мурка','2022-01-01', 'red', 'сидеть, лежать', 1);
INSERT INTO dog (Nickname, Birthday, Color, Commands, Type_of_animal_id) VALUES ('Жучка','2023-01-01', 'grey', 'сидеть, лежать', 2);
INSERT INTO hamster (Nickname, Birthday, Color, Commands, Type_of_animal_id) VALUES ('Буу','2020-07-01', 'grey', 'грызть', 3);

INSERT INTO horse (Nickname, Birthday, Color, Commands, Type_of_animal_id) VALUES ('Бусинка','2022-09-01', 'black', 'вперед', 1);
INSERT INTO donkey (Nickname, Birthday, Color, Commands, Type_of_animal_id) VALUES ('Иа','2021-11-01', 'white', 'вперед, неси', 2);
INSERT INTO camel (Nickname, Birthday, Color, Commands, Type_of_animal_id) VALUES ('Верблюд','2019-07-01', 'brown', 'вперед, лежать', 3);



-- ------------------------------------------------------------
-- 10. Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой
-- питомник на зимовку. Объединить таблицы лошади, и ослы в одну таблицу

DELETE FROM camel;

SELECT * FROM horse
UNION 
SELECT * FROM donkey;



-- ------------------------------------------------------------
-- 11.Создать новую таблицу “молодые животные” в которую попадут все
-- животные старше 1 года, но младше 3 лет и в отдельном столбце с точностью
-- до месяца подсчитать возраст животных в новой таблице
CREATE TABLE all_animal 
AS
  SELECT * FROM cat 
  UNION 
  SELECT * FROM dog 
  UNION 
  SELECT * FROM hamster
  UNION 
  SELECT * FROM horse
  UNION 
  SELECT * FROM donkey;

SELECT * FROM all_animal;

CREATE TABLE yang_animal 
AS
  SELECT Nickname, Birthday, Color, Commands, TIMESTAMPDIFF(YEAR, Birthday, CURDATE()) AS Age
  FROM all_animal 
  WHERE Birthday BETWEEN ADDDATE(curdate(), INTERVAL -3 YEAR) AND ADDDATE(CURDATE(), INTERVAL -1 YEAR);
 
SELECT * FROM yang_animal;

DROP TABLE all_animal;


-- ------------------------------------------------------------
-- 12. Объединить все таблицы в одну, при этом сохраняя поля, указывающие на
-- прошлую принадлежность к старым таблицам.

SELECT c.Nickname, c.Birthday, c.Color, c.Commands, pe.Type_of_animal
FROM cat c
LEFT JOIN class_pet pe ON pe.Id = c.Type_of_animal_id
UNION
SELECT do.Nickname, do.Birthday, do.Color, do.Commands, pe.Type_of_animal
FROM dog do
LEFT JOIN class_pet pe ON pe.Id = do.Type_of_animal_id
UNION
SELECT ha.Nickname, ha.Birthday, ha.Color, ha.Commands, pe.Type_of_animal
FROM hamster ha
LEFT JOIN class_pet pe ON pe.Id = ha.Type_of_animal_id
UNION
SELECT h.Nickname, h.Birthday, h.Color, h.Commands, p.Type_of_animal
FROM horse h
LEFT JOIN class_pack p ON p.Id = h.Type_of_animal_id
UNION 
SELECT d.Nickname, d.Birthday, d.Color, d.Commands, p.Type_of_animal
FROM donkey d
LEFT JOIN class_pack p ON p.Id = d.Type_of_animal_id

