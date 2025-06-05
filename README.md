### Java App - Nasa Images

**Executando o projeto**

- Crie um projeto maven: New java project > Maven > maven-archetype
- Instale e configure o maven

```bash
sudo apt update
sudo apt install maven

export M2_HOME=/opt/maven
export PATH=${M2_HOME}/bin:${PATH}
```

- Faça o build e execute o projeto

```bash
mvn compile
mvn exec:java -Dexec.mainClass="com.nasaimages.App" | ou apenas mvn exec:java
```