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
import ctc.utils.RichString

class WhenStepDef extends BaseStepDef {

  When("""^I click the (.+) link$""") { (linkText: String) =>
    Page.clickLinkByText(linkText)
    Thread.sleep(5000)
  }

  When("""^I submit (.+) on the (.+) page$""") { (answer: String, page: String) =>
    val id = page.toCamelCase()
    Page.fillInputById(id, answer)
    Page.continue()
  }

  When("""^I click the (Try again) button$""") { (_: String) =>
    Page.tryAgain()
  }
}
