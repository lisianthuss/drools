/*
 * This Scala source file was generated by the Gradle 'init' task.
 */
package simple

object App {
    def main(args: Array[String]): Unit = {
        println("Creating RuleUnit")
        //MeasurementUnit measurementUnit = new MeasurementUnit();
        var measurementUnit = new MeasurementUnit()

        RuleUnitInstance<MeasurementUnit> instance = RuleUnitProvider.get().createRuleUnitInstance(measurementUnit);
        try {
            println("Insert data")
            measurementUnit.getMeasurements().add(new Measurement("color", "red"));
            measurementUnit.getMeasurements().add(new Measurement("color", "green"));
            measurementUnit.getMeasurements().add(new Measurement("color", "blue"));

            println("Run query. Rules are also fired")
            List<Measurement> queryResult = instance.executeQuery("FindColor").toList("$m");
            //List<Measurement> queryResult = instance.executeQuery("FindColor").stream().map(tuple -> (Measurement) tuple.get("$m")).collect(toList());

            assertEquals(3, queryResult.size());
            assertTrue("contains red", measurementUnit.getControlSet().contains("red"));
            assertTrue("contains green", measurementUnit.getControlSet().contains("green"));
            assertTrue("contains blue", measurementUnit.getControlSet().contains("blue"));
        } finally {
            instance.close();
        }
    }
}


/*
package simple

import simple.model._

import java.util.List;
import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.broadcast.Broadcast;
//import org.apache.spark.sql.SparkSession

import org.kie.api.io._
import org.kie.internal.builder._
import org.kie.internal.io._

import collection.JavaConverters._
import org.slf4j.LoggerFactory


object App {
    val logger = LoggerFactory.getLogger(App.getClass())
    def main(args : Array[String]): Unit = {
        analyze(model1, "KB-People.drl")
    }

    def model1 = {
        val martine = Someone(name = "Martine", age = 30, nicknames = List("titine", "titi").asJava, attributes = Map("hairs" -> "brown").asJava)
        val martin = Someone(name = "Martin", age = 40, nicknames = List("tintin", "titi").asJava, attributes = Map("hairs" -> "black").asJava)
        val jack = Someone(name = "Jack", age = 12, nicknames = List("jacquouille").asJava, attributes = Map("eyes" -> "blue").asJava)
        val martineCar = Car(martine, "Ford", 2010, Color.blue)
        val martinCar = Car(martin, "GM", 2010, Color.black)
        val martinCar2 = Car(martin, "Ferrari", 2012, Color.red)
        val martinCar3 = Car(martin, "Porshe", 2011, Color.red)

        val martinHome = Home(martine, None)
        val jackHome = Home(jack, Some(Address("221B Baker Street", "London", "England")))

        List(
            martine,
            martin,
            jack,
            martineCar,
            martinCar,
            martinCar2,
            martinCar3,
            martinHome,
            jackHome
        )
    }

    def using[R, T <% {def dispose()}](getres: => T)(doit: T => R): R = {
        val res = getres
        try doit(res) finally res.dispose
    }

    def analyze(model: List[Any], kb: String) = {

        val config = KnowledgeBuilderFactory.newKnowledgeBuilderConfiguration()
        config.setProperty("drools.dialect.mvel.strict", "false")
        val kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder(config)

        val res = ResourceFactory.newClassPathResource(kb)
        kbuilder.add(res, ResourceType.DRL)

        val errors = kbuilder.getErrors();
        if (errors.size() > 0) {
            for (error <- errors.asScala) logger.error(error.getMessage())
            throw new IllegalArgumentException("Problem with the Knowledge base");
        }

        val kbase = kbuilder.newKieBase()

        val results = using(kbase.newKieSession()) { session =>
            session.setGlobal("logger", LoggerFactory.getLogger(kb))
            model.foreach(session.insert(_))
            session.fireAllRules()
            session.getObjects()
        }

        results
    }

}
*/