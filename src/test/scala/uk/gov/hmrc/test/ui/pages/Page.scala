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

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.{By, WebElement}
import play.api.libs.json.Json
import uk.gov.hmrc.test.ui.client.HttpClient
import uk.gov.hmrc.test.ui.driver.BrowserDriver
import uk.gov.hmrc.test.ui.messages.Helper.getMessage
import uk.gov.hmrc.test.ui.utils.World

import java.time.LocalDateTime
import scala.collection.convert.ImplicitConversions._

object Page extends BrowserDriver {

  def currentUrl: String = driver.getCurrentUrl

  def balanceId: String = {
    val pattern                  = "(.+)/ctc-guarantee-balance/(.+)/(.+)".r
    val pattern(_, balanceId, _) = currentUrl
    balanceId
  }

  def completeBalanceRequest(eoriNumber: String, grn: String): Unit = {
    val json = Json.parse(s"""
        |{
        |  "taxIdentifier": "$eoriNumber",
        |  "guaranteeReference": "$grn",
        |  "completedAt": "${LocalDateTime.now()}",
        |  "response": {
        |    "status": "SUCCESS",
        |    "balance": 1000,
        |    "currency": "GBP"
        |  }
        |}
        |""".stripMargin)

    val headers = Seq(
      ("Accept", "application/vnd.hmrc.1.0+json"),
      ("Authorization", World.bearerToken)
    )

    HttpClient.post(s"${getMessage("test_support_local_uri")}/balances/$balanceId", json, headers)
  }

  private def click(by: By): Unit = findElementBy(by).click()

  def submit(): Unit   = click(By.cssSelector("*[type='submit']"))
  def continue(): Unit = click(By.className("govuk-button"))
  def signOut(): Unit  = click(By.className("hmrc-sign-out-nav__link"))

  def findElementBy(by: By): WebElement        = driver.findElement(by)
  def findElementsBy(by: By): List[WebElement] = driver.findElements(by).toList

  def navigateTo(url: String): Unit = driver.navigate().to(url)

  def clickLinkByText(text: String): Unit =
    findElementsBy(By.tagName("a")).find(_.getText == text).foreach(_.click())

  def pageHeading: String = findElementBy(By.tagName("h1")).getText

  private def fillInput(by: By, text: String): Unit = {
    val input = driver.findElement(by)
    input.clear()
    if (text != null && text.nonEmpty) input.sendKeys(text)
  }

  def fillInputById(id: String, text: String): Unit = fillInput(By.id(id), text)

}
