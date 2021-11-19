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

package ctc.utils

import play.api.libs.json.Json
import ctc.pages.Page.balanceId

import java.time.LocalDateTime

object ApiHelper {

  private def headers: Seq[(String, String)] = Seq(
    ("Accept", "application/vnd.hmrc.1.0+json"),
    ("Authorization", World.bearerToken)
  )

  def completeBalanceRequest(eoriNumber: String, grn: String): Unit = {
    val url = s"${getValue("test_support_local_url")}/balances/$balanceId"

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

  def detailsDoNotMatch(eoriNumber: String, grn: String, errorType: Int): Unit = {
    val url = s"${getValue("test_support_local_url")}/balances/$balanceId"

    val json = Json.parse(s"""
                             |{
                             |  "taxIdentifier": "$eoriNumber",
                             |  "guaranteeReference": "$grn",
                             |  "completedAt": "${LocalDateTime.now()}",
                             |  "response": {
                             |    "status": "FUNCTIONAL_ERROR",
                             |    "errors": [
                             |      {
                             |        "errorType": $errorType,
                             |        "errorPointer": "Foo.Bar(1).Baz"
                             |      }
                             |    ]
                             |  }
                             |}
                             |""".stripMargin)

    HttpClient.post(url, json, headers)
  }

  def incorrectGuaranteeBalanceDetails(eoriNumber: String, grn: String, errorType: Int): Unit = {
    val url = s"${getValue("test_support_local_url")}/balances/$balanceId"

    val json = Json.parse(s"""
                             |{
                             |  "taxIdentifier": "$eoriNumber",
                             |  "guaranteeReference": "$grn",
                             |  "completedAt": "${LocalDateTime.now()}",
                             |  "response": {
                             |    "status": "FUNCTIONAL_ERROR",
                             |    "errors": [
                             |      {
                             |        "errorType": $errorType,
                             |        "errorPointer" : "GRR(1).GQY(1).Query identifier",
                             |        "errorReason" : "R261"
                             |      }
                             |    ]
                             |  }
                             |}
                             |""".stripMargin)

    HttpClient.post(url, json, headers)
  }
}
