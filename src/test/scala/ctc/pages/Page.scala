/*
 * Copyright 2021 HM Revenue & Customs
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
import org.openqa.selenium.{By, WebElement}
import ctc.utils.getValue

import scala.collection.convert.ImplicitConversions._

object Page extends BrowserDriver {

  def currentUrl: String = driver.getCurrentUrl

  def balanceId: String = {
    val pattern                  = "(.+)/check-guarantee-balance/(.+)/(.+)".r
    val pattern(_, balanceId, _) = currentUrl
    balanceId
  }

  private def click(by: By): Unit = findElementBy(by).click()

  def continue(): Unit                       = click(By.cssSelector("[data-module='govuk-button']"))
  def changeEoriNumber(): Unit               = click(By.id("change-eori-number"))
  def changeGuaranteeReferenceNumber(): Unit = click(By.id("change-guarantee-reference-number"))
  def changeAccessCode(): Unit               = click(By.id("change-access-code"))
  def submit(): Unit                         = click(By.id("submit"))

  def findElementBy(by: By): WebElement        = driver.findElement(by)
  def findElementsBy(by: By): List[WebElement] = driver.findElements(by).toList

  def navigateTo(url: String): Unit = driver.navigate().to(url)

  def clearCookies(): Unit = driver.manage().deleteAllCookies()

  def clickLinkByText(text: String): Unit = findElementsBy(By.tagName("a")).find(_.getText == text).foreach(_.click())

  def pageHeading: String = findElementBy(By.tagName("h1")).getText

  private def fillInput(by: By, text: String): Unit = {
    val input = driver.findElement(by)
    input.clear()
    if (text != null && text.nonEmpty) input.sendKeys(text)
  }

  def fillInputById(id: String, text: String): Unit = fillInput(By.id(id), text)

  def authenticate(identifierValue: String): Unit = {
    navigateTo(getValue("local_auth_login_url"))
    fillInput(By.cssSelector("*[name='redirectionUrl']"), getValue("local_auth_redirect_url"))
    fillInput(By.cssSelector("*[name='enrolment[0].name']"), getValue("enrolment_key"))
    fillInput(By.cssSelector("*[name='enrolment[0].taxIdentifier[0].name']"), getValue("identifier_name"))
    fillInput(By.cssSelector("*[name='enrolment[0].taxIdentifier[0].value']"), identifierValue)
    submit()
  }
}
