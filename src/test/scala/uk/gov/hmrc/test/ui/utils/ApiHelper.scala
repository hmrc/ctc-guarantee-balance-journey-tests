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

package uk.gov.hmrc.test.ui.utils

import play.api.libs.json.Json
import uk.gov.hmrc.test.ui.client.HttpClient
import uk.gov.hmrc.test.ui.messages._
import uk.gov.hmrc.test.ui.pages.Page.balanceId

import java.time.LocalDateTime

object ApiHelper {

  private def headers: Seq[(String, String)] = Seq(
    ("Accept", "application/vnd.hmrc.1.0+json"),
    ("Authorization", World.bearerToken)
  )

  def completeBalanceRequest(eoriNumber: String, grn: String): Unit = {
    val url = s"${getMessage("test_support_local_uri")}/balances/$balanceId"

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

    HttpClient.post(url, json, headers)
  }

}
