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

package uk.gov.hmrc.test.ui.cucumber.stepdefs

import uk.gov.hmrc.test.ui.messages.Helper._
import uk.gov.hmrc.test.ui.pages.Page
import uk.gov.hmrc.test.ui.utils.RichString

class CommonStepDef extends BaseStepDef {

  When("I redirect to manage my transit movements") { () =>
    Page.navigateTo(getMessage("local_manage_transit_movements"))
  }

  And("""^I click the (.+) link$""") { (linkText: String) =>
    Page.clickLinkByText(linkText)
  }

  Then("""^I should be on the (.+) page$""") { (pageHeading: String) =>
    Page.pageHeading shouldBe pageHeading
  }

  When("""^I submit (.+) on the (.+) page$""") { (answer: String, page: String) =>
    val id = page.toCamelCase()
    Page.fillInputById(id, answer)
    Page.continue()
  }

  And("""^I click the (Continue|Continue waiting) button$""") { (_: String) =>
    Page.continue()
  }

  Then("""^I should see a confirmation of my balance$""") { () =>
    Page.currentUrl should include("balance-confirmation")
  }

}
