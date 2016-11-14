package org.allegro.spoonbill.test.listener;

import java.util.Map;

import org.allegro.spoonbill.test.DataSetSourceContainer;
import org.spockframework.runtime.AbstractRunListener;
import org.spockframework.runtime.model.FeatureInfo;
import org.spockframework.runtime.model.SpecInfo;

public class JsonDataSetSelector extends AbstractRunListener {

    private Map<String, String[]> dataSetMap;
    private DataSetSourceContainer container;

    public JsonDataSetSelector(DataSetSourceContainer container, Map<String, String[]> dataSetMap) {

        this.container = container;
        this.dataSetMap = dataSetMap;
    }

    @Override
    public void beforeFeature(FeatureInfo feature) {

        // Select the data source of the feature
        String[] dataSetSources = dataSetMap.get(feature.getName());
        addDataSetSourcesToContainer(dataSetSources);
    }

    @Override
    public void afterFeature(FeatureInfo feature) {

        String[] dataSetSources = dataSetMap.get(feature.getName());
        removeDataSetSourcesToContainer(dataSetSources);
    }

    @Override
    public void beforeSpec(SpecInfo spec) {

        String[] dataSetSources = dataSetMap.get(spec.getName());
        addDataSetSourcesToContainer(dataSetSources);
    }

    @Override
    public void afterSpec(SpecInfo spec) {

        String[] dataSetSources = dataSetMap.get(spec.getName());
        removeDataSetSourcesToContainer(dataSetSources);
    }

    private void addDataSetSourcesToContainer(String[] dataSetSources) {

        if (dataSetSources != null) {
            container.addAllDataSetSources(dataSetSources);
        }
    }

    private void removeDataSetSourcesToContainer(String[] dataSetSources) {

        if (dataSetSources != null) {
            container.removeDataSetSources(dataSetSources);
        }
    }

}
