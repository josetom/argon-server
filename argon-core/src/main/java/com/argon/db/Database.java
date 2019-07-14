package com.argon.db;

import com.argon.helpers.GlobalUtils;
import com.argon.utils.DatabaseConstants;
import com.argon.utils.Env;
import io.reactivex.Single;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.UpdateResult;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.asyncsql.AsyncSQLClient;
import io.vertx.reactivex.ext.asyncsql.PostgreSQLClient;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.Query;
import org.jooq.SQLDialect;
import org.jooq.conf.ParamType;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;

public class Database {

    private static AsyncSQLClient sqlClient;
    private static DSLContext dsl;

    public static void init(Vertx vertx) {
        JsonObject postgreSQLClientConfig = new JsonObject()
                .put("host", Env.getString(DatabaseConstants.HOST))
                .put("port", Env.get(DatabaseConstants.PORT))
                .put("database", Env.get(DatabaseConstants.DATABASE))
                .put("username", Env.get(DatabaseConstants.USERNAME))
                .put("password", Env.get(DatabaseConstants.PASSWORD));

        sqlClient = PostgreSQLClient.createShared(vertx, postgreSQLClientConfig, "postgres-main-pool");

        final Configuration configuration = new DefaultConfiguration()
                .set(SQLDialect.POSTGRES);
        dsl = DSL.using(configuration);

    }

    public static Single<ResultSet> rxQuery(Query query) {
        GlobalUtils.assertExpression(query != null, "Query cannot be null");
        GlobalUtils.assertExpression(sqlClient != null, "Database client not initialized");
        return sqlClient.rxQuery(query.getSQL(ParamType.INLINED));
    }

    public static Single<UpdateResult> rxUpdate(Query query) {
        GlobalUtils.assertExpression(query != null, "Query cannot be null");
        GlobalUtils.assertExpression(sqlClient != null, "Database client not initialized");
        return sqlClient.rxUpdate(query.getSQL(ParamType.INLINED));
    }

    public static DSLContext sql() {
        GlobalUtils.assertExpression(dsl != null, "DSL not initialized");
        return dsl;
    }

}
