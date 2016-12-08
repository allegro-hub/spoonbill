package org.allegro.spoonbill.test;

import org.allegro.spoonbill.test.util.ArrayUtils;

public class DataSetSourceContainer {

    private String[] dataSetSources;

    public String[] getDataSetSources() {
        return dataSetSources;
    }

    public void addAllDataSetSources(String[] sources) {

        if (dataSetSources == null) {
            this.dataSetSources = sources;
        } else {
            this.dataSetSources = ArrayUtils.addElements(this.dataSetSources, sources);
        }
    }

    public void removeDataSetSources(String[] sources) {

        if (dataSetSources != null) {
            this.dataSetSources = ArrayUtils.removeElements(this.dataSetSources, sources);
        }
    }
}
