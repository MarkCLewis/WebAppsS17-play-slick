package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import slick.dbio.DBIOAction
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import models._
import models.Tables._
import slick.driver.MySQLDriver.api._

@Singleton
class UnemployController @Inject()(dbConfigProvider: DatabaseConfigProvider) extends Controller {
  val dbConfig = dbConfigProvider.get[JdbcProfile]
  
  def index = Action.async { implicit request =>
    val seriesf = dbConfig.db.run(Series.filter(s => Data.filter(d => d.seriesId === s.seriesId).exists).result)
    seriesf.map(series => Ok(views.html.unemploySeries(series))) 
  }
  
  def dataTable(series: String) = Action.async { implicit request =>
    val dataf = dbConfig.db.run(Data.filter(d => d.seriesId === series).result)
    dataf.map(data => Ok(views.html.unemployTable(series, data)))
  }
  
}