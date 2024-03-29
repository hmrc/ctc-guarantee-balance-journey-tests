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

package ctc.stepdefs

import ctc.pages.Page

class ThenStepDef extends BaseStepDef {

  Then("""^I should be on the (.+) page$""") { (pageHeading: String) =>
    Page.pageTitle shouldBe s"$pageHeading - Check your transit guarantee balance - GOV.UK"
  }

  Then("""^I should see a confirmation of my balance$""") { () =>
    Page.currentUrl should include("balance")
  }

  Then("""^I (should|shouldn't) see a (.+) link$""") { (assertion: String, linkText: String) =>
    Page.findByLinkText(linkText).length match {
      case x if x > 0 => assert(assertion == "should" && x == 1)
      case _          => assert(assertion == "shouldn't")
    }
  }

}
