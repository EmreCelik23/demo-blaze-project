package performance

import com.intuit.karate.gatling.PreDef.{karateFeature, karateProtocol}
import io.gatling.core.Predef._

import scala.concurrent.duration.{Duration, SECONDS}

class performanceSimulation extends Simulation {

  val protocol = {
    karateProtocol()
  }

  before(
    println("Simulation is about to start!")
  )

  val userCount = System.getProperty("userCount")
  val duration = System.getProperty("duration")

  val getPosts = scenario("Deneme")
    .exec(karateFeature("classpath:features/viewProducts.feature@load"))

  setUp(
    getPosts.inject(
      rampUsers(userCount.toInt) during Duration(duration.toInt, SECONDS)
    ).protocols(protocol)
  )

  after(
    println("Simulation is ended!")
  )

  // mvn clean test-compile gatling:test -D"gatling.simulationClass=performance.performanceSimulation" -D"userCount=1" -D"duration=1"

}