package com.cloudera.labs.envelope.plan;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.junit.Test;

import com.cloudera.labs.envelope.spark.Contexts;
import com.google.common.collect.Lists;
import com.typesafe.config.ConfigFactory;

import scala.Tuple2;

public class TestDeletePlanner {

  @Test
  public void testPlanner() {
    List<Row> rows = Lists.newArrayList(RowFactory.create("a", 1, false), RowFactory.create("b", 2, true));
    StructType schema = new StructType(new StructField[] {
        new StructField("field1", DataTypes.StringType, false, null),
        new StructField("field2", DataTypes.IntegerType, false, null),
        new StructField("field3", DataTypes.BooleanType, false, null)
    });
    
    Dataset<Row> data = Contexts.getSparkSession().createDataFrame(rows, schema);
    
    BulkPlanner p = new DeletePlanner();
    p.configure(ConfigFactory.empty());
    
    List<Tuple2<MutationType, Dataset<Row>>> planned = p.planMutationsForSet(data);
    
    assertEquals(1, planned.size());
    assertEquals(MutationType.DELETE, planned.get(0)._1());
    assertEquals(data, planned.get(0)._2());
  }

}
