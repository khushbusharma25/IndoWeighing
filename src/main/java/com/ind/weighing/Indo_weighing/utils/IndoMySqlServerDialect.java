
package com.ind.weighing.Indo_weighing.utils;

import java.sql.Types;

import org.hibernate.dialect.MySQL55Dialect;
import org.hibernate.type.StandardBasicTypes;

public class IndoMySqlServerDialect extends MySQL55Dialect{
	public IndoMySqlServerDialect() {
		registerHibernateType(Types.NCHAR, StandardBasicTypes.CHARACTER.getName());
		registerHibernateType(Types.NCHAR, 1, StandardBasicTypes.CHARACTER.getName());
		registerHibernateType(Types.NCHAR, 255, StandardBasicTypes.STRING.getName());
		registerHibernateType(Types.NVARCHAR, StandardBasicTypes.STRING.getName());
		registerHibernateType(Types.LONGNVARCHAR, StandardBasicTypes.TEXT.getName());
		registerHibernateType(Types.NCLOB, StandardBasicTypes.CLOB.getName());
	}

}
