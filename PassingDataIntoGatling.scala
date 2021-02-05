import io.gatling.jdbc.Predef._

class PassingDataIntoGatling extends Simulation {

    val target = System.getProperty("target")

    val httpProtocol = http
        .baseUrl("http://" + target)
        .inferHtmlResources()
        .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
        .acceptEncodingHeader("gzip, deflate")
        .acceptLanguageHeader("en-US,en;q=0.5")
        .doNotTrackHeader("1")
        .userAgentHeader("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:63.0) Gecko/20100101 Firefox/63.0")

    val headers_0 = Map(
        "Pragma" -> "no-cache",
        "Upgrade-Insecure-Requests" -> "1")

    val scn = scenario("PassingDataIntoGatling")
        .exec(
            http("request_0").get("/").headers(headers_0)
        )

    setUp(
        scn.inject(
            constantUsersPerSec(10) during (5 seconds)
        ).protocols(httpProtocol)
    )
