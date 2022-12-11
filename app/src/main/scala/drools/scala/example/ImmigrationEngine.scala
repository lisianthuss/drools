package drools.scala.example

import java.util.ArrayList
import scala.collection.JavaConverters.iterableAsScalaIterable

object ImmigrationEngine {

  def determineEligibleApplicants(facts: List[ImmigrationFact]): List[Object] = {
    val eligibleApplicants = new java.util.ArrayList[Applicant]
    val session = Kie.newSession

    session.setGlobal("eligibleApplicants", eligibleApplicants)
    facts map session.insert

    session.fireAllRules()
    session.dispose()

    eligibleApplicants.toArray().toList
  }
}
