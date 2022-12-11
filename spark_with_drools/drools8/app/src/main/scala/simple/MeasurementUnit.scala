
package simple

import java.util.HashSet;
import java.util.Set;

import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStore;
import org.drools.ruleunits.api.RuleUnitData;

class MeasurementUnit() extends RuleUnitData {
    var controlSet = Set[String]
    var measurements: DataStore[Measurement]

    def MeasurementUnit(): Unit = {
        DataSource.createStore()
    }
    def getMeasurements(): DataStore[Measurement] = {
        return measurements
    }
    def getControlSet(): Set[String] = {
        return controlSet
    }
}

/*
public class MeasurementUnit implements RuleUnitData {

    private final DataStore<Measurement> measurements;
    private final Set<String> controlSet = new HashSet<>();

    public MeasurementUnit() {
        this(DataSource.createStore());
    }

    public MeasurementUnit(DataStore<Measurement> measurements) {
        this.measurements = measurements;
    }

    public DataStore<Measurement> getMeasurements() {
        return measurements;
    }

    public Set<String> getControlSet() {
        return controlSet;
    }
}
*/