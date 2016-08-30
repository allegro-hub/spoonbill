package org.allegro.spoonbill.test.interceptor;

import java.util.Stack;

import org.allegro.spoonbill.test.DataSetSourceContainer;
import org.allegro.spoonbill.test.exception.ApplicationContextNotFoundException;
import org.dbunit.IDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.spockframework.runtime.extension.AbstractMethodInterceptor;
import org.spockframework.runtime.extension.IMethodInvocation;
import org.spockframework.runtime.model.FieldInfo;
import org.spockframework.runtime.model.SpecInfo;
import org.springframework.context.ApplicationContext;

public class JsonDataSetInterceptor extends AbstractMethodInterceptor {

    private SpecInfo spec;
    private IDatabaseTester databaseTester;
    private Stack<IDataSet> dataSets;
    private DataSetSourceContainer container;

    public JsonDataSetInterceptor(SpecInfo spec, DataSetSourceContainer container) {

        this.spec = spec;
        this.container = container;

        spec.addSetupInterceptor(this);
        spec.addCleanupInterceptor(this);

        this.dataSets = new Stack<IDataSet>();
    }

    @Override
    public void interceptSetupMethod(IMethodInvocation invocation) throws Throwable {

        if (this.container.getDataSetSources() == null) {
            return;
        }

        ApplicationContext applicationContext = getContext(invocation);
        this.databaseTester = (IDatabaseTester) applicationContext.getBean("databaseTester");
        for (String dataSource : this.container.getDataSetSources()) {

            IDataSet dataSet =
                    new org.allegro.spoonbill.test.dataset.JsonDataSets(applicationContext.getResource(dataSource).getFile());
            databaseTester.setDataSet(dataSet);
            databaseTester.onSetup();
            dataSets.push(dataSet);
        }

        invocation.proceed();
    }

    @Override
    public void interceptCleanupMethod(IMethodInvocation invocation) throws Throwable {

        int stackSize = dataSets.size();
        IDatabaseConnection connection = databaseTester.getConnection();

        for (int seq = 0; seq < stackSize; seq++) {

            IDataSet dataSet = dataSets.pop();
            DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
            DatabaseOperation.DELETE_ALL.execute(connection, dataSet);
        }

        connection.close();
        databaseTester.onTearDown();

        invocation.proceed();
    }

    private ApplicationContext getContext(IMethodInvocation invocation) {

        FieldInfo field = spec.getAllFields().stream().filter(f -> f.getName().equals("applicationContext")).findFirst()
                .orElseThrow(() -> new ApplicationContextNotFoundException());

        return (ApplicationContext) field.readValue(invocation.getInstance());
    }
}
