mvn clean package -DskipTests;
git add .;
git commit -am "new jar";
git push heroku heroku;
