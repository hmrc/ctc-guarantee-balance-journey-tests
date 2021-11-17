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

package ctc.stepdefs

import ctc.pages.Page

class WhenStepDef extends BaseStepDef {

  When("""^I click the (.+) link$""") { (linkText: String) =>
    Page.clickLinkByText(linkText)
  }

  When("""^I submit (.+) as (eori number|guarantee reference number|access code) value$""") {
    (answer: String, _: String) =>
      Page.fillInputById("value", answer)
      Page.continue()
  }

  When("""^I click the (Try again|Start again) button$""") { (_: String) =>
    Page.submit()
  }

  When("""^I click the (Continue|Continue waiting) button$""") { (_: String) =>
    Page.continue()
  }

  When("""^I choose to wait for (.+) seconds$""") { (seconds: Int) =>
    val waitTime = seconds * 1000
    Thread.sleep(waitTime)
  }

  When("""^I click the Change link for (.+)$""") { (changeText: String) =>
    changeText.toLowerCase() match {
      case "eori number"                => Page.changeEoriNumber()
      case "guarantee reference number" => Page.changeGuaranteeReferenceNumber()
      case "access code"                => Page.changeAccessCode()
      case _                            => throw new Exception("Match Error, Wrong text enter : " + changeText)
    }
  }
}
