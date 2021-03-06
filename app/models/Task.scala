package models

import anorm._
import anorm.SqlParser._
import play.api.db.DB
import play.api.Play.current
import play.Logger

case class Task(id : Long, label : String)
case class TaskForm(label : String)

object Task {

  val task = {
    get[Long]("id") ~
      get[String]("label") map{
      case id~label => Task(id,label)
    }
  }

  def all() : List[Task] = DB.withConnection {  implicit c =>
    SQL("select * from task").as(task *)
  }

  def create(task : TaskForm){
    Logger.info("create")
    DB.withConnection { implicit c =>
      SQL("insert into task (label) values ({label})").on(
        'label -> task.label
      ).executeUpdate()
    }
  }

  def delete(id : Long) {
    Logger.info(""+id)
    DB.withConnection { implicit c =>
      SQL("delete from task where id = {id}").on(
        'id -> id
      ).executeUpdate()
    }
  }

}


