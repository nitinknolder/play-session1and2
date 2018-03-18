package controllers

import javax.inject._

import play.api.data.Forms._
import play.api.data._
import play.api.i18n._
import play.api.mvc._

/**
  * def index(name: String) = Action { implicit request: Request[AnyContent] =>
  * //    Ok(views.html.index(name))
  * Redirect(routes.HomeController.index1(name))
  * }
  * def index1(name: String) = Action { implicit request: Request[AnyContent] =>
  * *
  * Ok("Hello" + name)
  * }
  */

/**
  * def index(name: String) = Action { implicit request: Request[AnyContent] =>
  */
/** Ok (views.html.index (name) )
  * Redirect (routes.HomeController.index1 (name) )
  * *
  * }
  * def index1 (name: String) = Action {
  * implicit request: Request[AnyContent] =>
  * *
  * Ok ("Hello" + name)
  *
  * }
  */


/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
case class Student (name: String, email: String, password: String,confirmPassword: String)

@Singleton
class HomeController @Inject () (cc: ControllerComponents) extends AbstractController (cc) with I18nSupport {
  implicit val messages: MessagesApi = cc.messagesApi


  /**
    * Create an Action to render an HTML page.
    *
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  /**
    * def index(name: String) = Action { implicit request: Request[AnyContent] =>
    */
  /** Ok (views.html.index (name))
    * Redirect (routes.HomeController.index1 (name))
    */


  /** def index1 (name: String) = Action {
    * implicit request: Request[AnyContent] =>
    * *
    * Ok ("Hello" + name)
    *
    * }
    */

  /** def index() = Action {implicit request: Request[AnyContent] =>
    * val students = Student("nitin","nitinarora1519@gmail.com")
    * Ok(views.html.index(students))
    * } */

  val userForm = Form (mapping (
    "name" -> nonEmptyText,
    "email" -> email,
    "password" -> nonEmptyText,
    "confirmPassword" -> nonEmptyText
  )(Student.apply)(Student.unapply)

    verifying ("Passwords must match", verify => verify.password == verify.confirmPassword))

  def index () = Action { implicit request: Request[AnyContent] => {

    Ok (views.html.index (userForm))
  }

  }

  def userProfile () = Action { implicit request => {
    Ok (views.html.redirect ("test"))

  }
  }

  def userPost () = Action { implicit request: Request[AnyContent] => {
    userForm.bindFromRequest.fold (
      formWithErrors => {
        BadRequest (views.html.index (formWithErrors))
      },
      userData => {
        Redirect (routes.HomeController.userProfile ())
          .withSession ("Name" -> userData.name, "Email" -> userData.email)
          .flashing ("Success" -> "Congrats!!! Now Run Your Dream Solution")

      })

  }
  }
}
