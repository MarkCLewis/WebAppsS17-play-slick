package utility

import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import play.api.Play
import slick.dbio.DBIOAction
import models.Tables._
import slick.driver.MySQLDriver.api._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration.Duration

object PopulateTables extends App {
  val db = Database.forURL("jdbc:mysql://localhost/unemploy", user="mlewis", password="password", driver="com.mysql.jdbc.Driver")
  
  val deletes = db.run(DBIOAction.seq(Series.delete, Data.delete))
  val buildSeries = deletes.flatMap(_ => db.run {
    val source = scala.io.Source.fromFile("la.series")
    val lines = source.getLines.drop(1).toSeq
    Series ++= (for(line <- lines) yield {
      val p = line.split("\t").map(_.trim)
      SeriesRow(p(0), p(1)(0), p(2), p(6), p(8).toInt, p(9).drop(1).toInt, p(10).toInt, p(11).drop(1).toInt)
    })
  } )
  val buildData = buildSeries.flatMap(_ => db.run {
    val source = scala.io.Source.fromFile("la.data.51.Texas")
    val lines = source.getLines.drop(1).toSeq
    Data ++= (for(line <- lines) yield {
      val p = line.split("\t").map(_.trim)
      DataRow(p(0), p(1).toInt, p(2).drop(1).toInt, p(3).toDouble)
    })
  } )
  Await.result(buildData, Duration.Inf)
  
}