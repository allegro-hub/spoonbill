package org.allegro.spoonbill.autoconfigure;

import javax.sql.DataSource;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass({ DataSource.class, DataSourceDatabaseTester.class, DatabaseConfig.class, IDatabaseConnection.class,
		HsqldbDataTypeFactory.class })
public class SpoonbillAutoConfiguration {

	@Bean
	public DataSourceDatabaseTester databaseTester(DataSource datasource) {
		return new HsqlDataSourceDatabaseTester(datasource);
	}

	public class HsqlDataSourceDatabaseTester extends DataSourceDatabaseTester {

		public HsqlDataSourceDatabaseTester(DataSource datasource) {
			super(datasource);
		}

		@Override
		public IDatabaseConnection getConnection() throws Exception {

			IDatabaseConnection connection = super.getConnection();
			connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new HsqldbDataTypeFactory());

			return connection;
		}
	}
}
