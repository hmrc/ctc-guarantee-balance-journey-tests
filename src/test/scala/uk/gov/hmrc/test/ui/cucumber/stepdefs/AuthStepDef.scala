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

import org.openqa.selenium.By._
import uk.gov.hmrc.test.ui.messages.Helper._
import uk.gov.hmrc.test.ui.pages.Page
import uk.gov.hmrc.test.ui.utils.World

class AuthStepDef extends BaseStepDef {

  Given("I get a bearer token") { () =>
    val url: String = getMessage("local_auth_login_stub")
    Page.navigateTo(url)

    Page.findElementBy(name(getPath("auth_redirection_url"))).sendKeys(getMessage("local_auth_redirect_url"))
    Page.findElementBy(name(getPath("enrolment_key"))).sendKeys(getMessage("enrolment_key"))
    Page.findElementBy(id(getPath("identifier_name"))).sendKeys(getMessage("identifier_name"))
    Page.findElementBy(id(getPath("identifier_value"))).sendKeys(getMessage("identifier_value"))

    Page.submit()

    World.bearerToken = Page.findElementBy(cssSelector(getPath("token_locator"))).getText
  }

  And("""^I sign out$""") { () =>
    Page.signOut()
  }

}
