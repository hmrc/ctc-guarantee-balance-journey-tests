package uk.gov.hmrc.test.ui.utils

import play.api.libs.json.Json
import uk.gov.hmrc.test.ui.client.HttpClient
import uk.gov.hmrc.test.ui.messages.Helper.getMessage
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
