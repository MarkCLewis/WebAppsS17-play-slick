package models
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.driver.MySQLDriver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.driver.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Data.schema ++ Series.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Data
   *  @param seriesId Database column series_id SqlType(CHAR), Length(20,false)
   *  @param year Database column year SqlType(INT)
   *  @param period Database column period SqlType(INT)
   *  @param value Database column value SqlType(DOUBLE) */
  case class DataRow(seriesId: String, year: Int, period: Int, value: Double)
  /** GetResult implicit for fetching DataRow objects using plain SQL queries */
  implicit def GetResultDataRow(implicit e0: GR[String], e1: GR[Int], e2: GR[Double]): GR[DataRow] = GR{
    prs => import prs._
    DataRow.tupled((<<[String], <<[Int], <<[Int], <<[Double]))
  }
  /** Table description of table data. Objects of this class serve as prototypes for rows in queries. */
  class Data(_tableTag: Tag) extends Table[DataRow](_tableTag, "data") {
    def * = (seriesId, year, period, value) <> (DataRow.tupled, DataRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(seriesId), Rep.Some(year), Rep.Some(period), Rep.Some(value)).shaped.<>({r=>import r._; _1.map(_=> DataRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column series_id SqlType(CHAR), Length(20,false) */
    val seriesId: Rep[String] = column[String]("series_id", O.Length(20,varying=false))
    /** Database column year SqlType(INT) */
    val year: Rep[Int] = column[Int]("year")
    /** Database column period SqlType(INT) */
    val period: Rep[Int] = column[Int]("period")
    /** Database column value SqlType(DOUBLE) */
    val value: Rep[Double] = column[Double]("value")

    /** Foreign key referencing Series (database name data_ibfk_1) */
    lazy val seriesFk = foreignKey("data_ibfk_1", seriesId, Series)(r => r.seriesId, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table Data */
  lazy val Data = new TableQuery(tag => new Data(tag))

  /** Entity class storing rows of table Series
   *  @param seriesId Database column series_id SqlType(CHAR), PrimaryKey, Length(20,false)
   *  @param areaType Database column area_type SqlType(CHAR)
   *  @param areaCode Database column area_code SqlType(CHAR), Length(15,false)
   *  @param seriesTitle Database column series_title SqlType(VARCHAR), Length(120,true)
   *  @param beginYear Database column begin_year SqlType(INT)
   *  @param beginPeriod Database column begin_period SqlType(INT)
   *  @param endYear Database column end_year SqlType(INT)
   *  @param endPeriod Database column end_period SqlType(INT) */
  case class SeriesRow(seriesId: String, areaType: Char, areaCode: String, seriesTitle: String, beginYear: Int, beginPeriod: Int, endYear: Int, endPeriod: Int)
  /** GetResult implicit for fetching SeriesRow objects using plain SQL queries */
  implicit def GetResultSeriesRow(implicit e0: GR[String], e1: GR[Char], e2: GR[Int]): GR[SeriesRow] = GR{
    prs => import prs._
    SeriesRow.tupled((<<[String], <<[Char], <<[String], <<[String], <<[Int], <<[Int], <<[Int], <<[Int]))
  }
  /** Table description of table series. Objects of this class serve as prototypes for rows in queries. */
  class Series(_tableTag: Tag) extends Table[SeriesRow](_tableTag, "series") {
    def * = (seriesId, areaType, areaCode, seriesTitle, beginYear, beginPeriod, endYear, endPeriod) <> (SeriesRow.tupled, SeriesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(seriesId), Rep.Some(areaType), Rep.Some(areaCode), Rep.Some(seriesTitle), Rep.Some(beginYear), Rep.Some(beginPeriod), Rep.Some(endYear), Rep.Some(endPeriod)).shaped.<>({r=>import r._; _1.map(_=> SeriesRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column series_id SqlType(CHAR), PrimaryKey, Length(20,false) */
    val seriesId: Rep[String] = column[String]("series_id", O.PrimaryKey, O.Length(20,varying=false))
    /** Database column area_type SqlType(CHAR) */
    val areaType: Rep[Char] = column[Char]("area_type")
    /** Database column area_code SqlType(CHAR), Length(15,false) */
    val areaCode: Rep[String] = column[String]("area_code", O.Length(15,varying=false))
    /** Database column series_title SqlType(VARCHAR), Length(120,true) */
    val seriesTitle: Rep[String] = column[String]("series_title", O.Length(120,varying=true))
    /** Database column begin_year SqlType(INT) */
    val beginYear: Rep[Int] = column[Int]("begin_year")
    /** Database column begin_period SqlType(INT) */
    val beginPeriod: Rep[Int] = column[Int]("begin_period")
    /** Database column end_year SqlType(INT) */
    val endYear: Rep[Int] = column[Int]("end_year")
    /** Database column end_period SqlType(INT) */
    val endPeriod: Rep[Int] = column[Int]("end_period")
  }
  /** Collection-like TableQuery object for table Series */
  lazy val Series = new TableQuery(tag => new Series(tag))
}
