import sbt._

object Dependencies {

  val test: Seq[ModuleID] = Seq(
    "uk.gov.hmrc"         %% "webdriver-factory"      % "0.23.0",
    "org.scalatest"       %% "scalatest"              % "3.2.0",
    "org.scalatestplus"   %% "selenium-3-141"         % "3.2.0.0",
    "com.vladsch.flexmark" % "flexmark-all"           % "0.35.10",
    "io.cucumber"         %% "cucumber-scala"         % "6.9.1",
    "io.cucumber"          % "cucumber-junit"         % "6.9.1",
    "junit"                % "junit"                  % "4.12",
    "com.novocode"         % "junit-interface"        % "0.11",
    "com.typesafe"         % "config"                 % "1.3.2",
    "com.typesafe.play"   %% "play-json"              % "2.9.2",
    "com.typesafe.play"   %% "play-ahc-ws-standalone" % "2.1.3"
  ).map(_ % Test)

}
