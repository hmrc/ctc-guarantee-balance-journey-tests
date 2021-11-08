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

package uk.gov.hmrc.test.ui

import java.io.FileInputStream
import java.util.Properties

package object messages {

  def getMessage(key: String): String = messageResource.getProperty(key, key)

  def getPath(key: String): String = pathResource.getProperty(key, key)

  private val messageResource: Properties =
    getPropertiesForFile("messages.properties")

  private val pathResource: Properties =
    getPropertiesForFile("page_paths.properties")

  private def getPropertiesForFile(file: String): Properties = {
    val messageStream = new FileInputStream(s"./src/test/scala/uk/gov/hmrc/test/ui/messages/$file")
    val properties    = new Properties()
    properties.load(messageStream)
    properties
  }

}
