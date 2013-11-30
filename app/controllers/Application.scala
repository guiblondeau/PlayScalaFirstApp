package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models._

object Application extends Controller {

  def index = Action {
    Redirect(routes.Application.tasks)
  }

  val taskForm = Form(
    mapping(
      "label" -> nonEmptyText
    )(TaskForm.apply)(TaskForm.unapply)
  )

  def tasks = Action {
    Ok(views.html.index(Task.all(), taskForm))
  }

  def newTask = Action {implicit request =>
    taskForm.bindFromRequest.fold(
      errors => {
        BadRequest(views.html.index(Task.all(), errors))
      },
      label => {
        Task.create(label)
        Redirect(routes.Application.tasks)
  }
    )
  }

  def deleteTask(id : Long) = TODO
}