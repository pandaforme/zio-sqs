val mainScala = "2.12.8"
val allScala  = Seq("2.11.12", mainScala)

organization := "dev.zio"
homepage := Some(url("https://github.com/zio/zio-sqs"))
name := "zio-sqs"
licenses := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0"))
scalaVersion := mainScala
parallelExecution in Test := false
fork in Test := true
pgpPublicRing := file("/tmp/public.asc")
pgpSecretRing := file("/tmp/secret.asc")
releaseEarlyWith := SonatypePublisher
scmInfo := Some(
  ScmInfo(url("https://github.com/zio/zio-sqs/"), "scm:git:git@github.com:zio/zio-sqs.git")
)
developers := List(
  Developer(
    "ghostdogpr",
    "Pierre Ricadat",
    "ghostdogpr@gmail.com",
    url("https://github.com/ghostdogpr")
  )
)

libraryDependencies ++= Seq(
  "dev.zio"                %% "zio"                   % "1.0.0-RC9",
  "dev.zio"                %% "zio-streams"           % "1.0.0-RC9",
  "software.amazon.awssdk" % "sqs"                    % "2.6.4",
  "org.scalatest"          %% "scalatest"             % "3.0.8" % "test",
  "org.elasticmq"          %% "elasticmq-rest-sqs"    % "0.14.7" % "test",
  "org.elasticmq"          %% "elasticmq-core"        % "0.14.7" % "test",
  "com.danielasfregola"    %% "random-data-generator" % "2.7" % "test",
  compilerPlugin("org.typelevel" %% "kind-projector"     % "0.10.2"),
  compilerPlugin("com.olegpy"    %% "better-monadic-for" % "0.3.0")
)

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding",
  "UTF-8",
  "-explaintypes",
  "-Yrangepos",
  "-feature",
  "-Xfuture",
  "-language:higherKinds",
  "-language:existentials",
  "-unchecked",
  "-Xlint:_,-type-parameter-shadow",
  "-Ywarn-numeric-widen",
  "-Ywarn-unused",
  "-Ywarn-value-discard"
) ++ (CrossVersion.partialVersion(scalaVersion.value) match {
  case Some((2, 11)) =>
    Seq(
      "-Yno-adapted-args",
      "-Ypartial-unification",
      "-Ywarn-inaccessible",
      "-Ywarn-infer-any",
      "-Ywarn-nullary-override",
      "-Ywarn-nullary-unit"
    )
  case Some((2, 12)) =>
    Seq(
      "-Xsource:2.13",
      "-Yno-adapted-args",
      "-Ypartial-unification",
      "-Ywarn-extra-implicit",
      "-Ywarn-inaccessible",
      "-Ywarn-infer-any",
      "-Ywarn-nullary-override",
      "-Ywarn-nullary-unit",
      "-opt-inline-from:<source>",
      "-opt-warnings",
      "-opt:l:inline"
    )
  case _ => Nil
})

fork in run := true

crossScalaVersions := allScala

addCommandAlias("fmt", "all scalafmtSbt scalafmt test:scalafmt")
addCommandAlias("check", "all scalafmtSbtCheck scalafmtCheck test:scalafmtCheck")
