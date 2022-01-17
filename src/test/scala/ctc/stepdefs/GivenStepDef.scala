/*
 * Copyright 2022 HM Revenue & Customs
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

package ctc.stepdefs

import ctc.pages.Page
import ctc.utils.ConfigHelper._
import ctc.utils.{ApiHelper, MongoHelper, World}
import org.openqa.selenium.By.cssSelector

class GivenStepDef extends BaseStepDef {

  Given("""^I clear my cookies""") { () =>
    Page.clearCookies()
  }

  Given("""^I login with identifier value (.*)$""") { (identifierValue: String) =>
    Page.authenticate(identifierValue)
    World.bearerToken = Page.findElementBy(cssSelector("[data-session-id='authToken']")).getText
    Page.navigateTo(getValue("local_manage_transit_movements_url"))
  }

  Given("""^The balance request completes for EORI number (.+) and GRN (.+)$""") { (eoriNumber: String, grn: String) =>
    ApiHelper.completeBalanceRequest(eoriNumber, grn)
  }

  Given("""^The details do not match for EORI number (.+) and GRN (.+)$""") { (eoriNumber: String, grn: String) =>
    ApiHelper.detailsDoNotMatch(eoriNumber, grn, 12)
  }

  Given("""^The guarantee type not accepted for EORI number (.+) and GRN (.+)$""") {
    (eoriNumber: String, grn: String) =>
      ApiHelper.incorrectGuaranteeBalanceDetails(eoriNumber, grn, 14)
  }

  Given("""^The balance request is removed$""") { () =>
    MongoHelper.deleteBalanceRequest()
  }
}
