/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ctc.pages

import ctc.driver.BrowserDriver
import ctc.utils.ConfigHelper._
import ctc.utils.World
import org.openqa.selenium.By.cssSelector
import org.openqa.selenium.{By, WebElement}

import scala.jdk.CollectionConverters._

object Page extends BrowserDriver {

  def currentUrl: String = driver.getCurrentUrl

  private def click(by: By): Unit             = findElementBy(by).click()
  def clickById(id: String): Unit             = click(By.id(id))
  def clickByLinkText(linkText: String): Unit = click(By.linkText(linkText))

  def clickLinkByIdTextSplit(text: String): Unit = {
    val id = s"change-${text.replace(" ", "-").toLowerCase}"
    clickById(id)
  }

  def continue(): Unit = clickById("continue")
  def submit(): Unit   = clickById("submit")

  def findElementBy(by: By): WebElement        = driver.findElement(by)
  def findElementsBy(by: By): List[WebElement] = driver.findElements(by).asScala.toList

  def findByLinkText(linkText: String): List[WebElement] = findElementsBy(By.linkText(linkText))

  def navigateTo(url: String): Unit = driver.navigate().to(url)

  def clearCookies(): Unit = driver.manage().deleteAllCookies()

  def pageHeading: String = findElementBy(By.tagName("h1")).getText

  def pageTitle: String = driver.getTitle

  private def fillInput(by: By, text: String): Unit = {
    val input = findElementBy(by)
    input.clear()
    if (text != null && text.nonEmpty) input.sendKeys(text)
  }

  def fillInputById(id: String, text: String): Unit = fillInput(By.id(id), text)

  def fillInputByCssSelector(cssSelector: String, text: String): Unit =
    fillInput(By.cssSelector(cssSelector), text)

  def authenticate(identifierValue: String): Unit =
    authenticate {
      fillInputByCssSelector("*[name='enrolment[0].name']", getValue("enrolment_key"))
      fillInputByCssSelector("*[name='enrolment[0].taxIdentifier[0].name']", getValue("identifier_name"))
      fillInputByCssSelector("*[name='enrolment[0].taxIdentifier[0].value']", identifierValue)
    }

  def authenticateNewEnrolment(identifierValue: String): Unit =
    authenticate {
      fillInputByCssSelector("*[name='enrolment[0].name']", getValue("new_enrolment_key"))
      fillInputByCssSelector("*[name='enrolment[0].taxIdentifier[0].name']", getValue("new_identifier_name"))
      fillInputByCssSelector("*[name='enrolment[0].taxIdentifier[0].value']", identifierValue)
    }

  def authenticate(fillAdditionalInputs: => Unit = ()): Unit = {
    navigateTo(getValue("local_auth_login_url"))
    fillInputByCssSelector("*[name='redirectionUrl']", getValue("local_auth_redirect_url"))
    fillAdditionalInputs
    submit()
    World.bearerToken = findElementBy(cssSelector("[data-session-id='authToken']")).getText
  }
}
