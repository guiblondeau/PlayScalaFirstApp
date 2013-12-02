package controllers

import play.api.mvc.{Action, Controller}
import play.api.Routes

/**
 * Created with IntelliJ IDEA.
 * User: guillaume
 * Date: 02/12/13
 * Time: 09:26
 * To change this template use File | Settings | File Templates.
 */
object JsRouter extends Controller{

  def javascriptRoutes = Action { implicit request =>
    import routes.javascript._
    Ok(
      Routes.javascriptRouter("jsRoutes")(
        Application.deleteTask
      )
    ).as("text/javascript")
  }

}
