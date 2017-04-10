package utility

object CodeGen extends App {
  slick.codegen.SourceCodeGenerator.main(
    Array("slick.driver.MySQLDriver", "com.mysql.jdbc.Driver", 
        "jdbc:mysql://localhost/unemploy?user=mlewis&password=password", 
        "/home/mlewis/Documents/Teaching/CSCI3345-S17/Play/play-with-slick/app/", "models", "mlewis", "password")
  )
}