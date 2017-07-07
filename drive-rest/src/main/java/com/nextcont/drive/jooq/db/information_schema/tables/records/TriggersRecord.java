/*
 * This file is generated by jOOQ.
*/
package com.nextcont.drive.jooq.db.information_schema.tables.records;


import com.nextcont.drive.jooq.db.information_schema.tables.Triggers;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record22;
import org.jooq.Row22;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TriggersRecord extends TableRecordImpl<TriggersRecord> implements Record22<String, String, String, String, String, String, String, Long, String, String, String, String, String, String, String, String, Timestamp, String, String, String, String, String> {

    private static final long serialVersionUID = 348645670;

    /**
     * Setter for <code>information_schema.TRIGGERS.TRIGGER_CATALOG</code>.
     */
    public void setTriggerCatalog(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>information_schema.TRIGGERS.TRIGGER_CATALOG</code>.
     */
    public String getTriggerCatalog() {
        return (String) get(0);
    }

    /**
     * Setter for <code>information_schema.TRIGGERS.TRIGGER_SCHEMA</code>.
     */
    public void setTriggerSchema(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>information_schema.TRIGGERS.TRIGGER_SCHEMA</code>.
     */
    public String getTriggerSchema() {
        return (String) get(1);
    }

    /**
     * Setter for <code>information_schema.TRIGGERS.TRIGGER_NAME</code>.
     */
    public void setTriggerName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>information_schema.TRIGGERS.TRIGGER_NAME</code>.
     */
    public String getTriggerName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>information_schema.TRIGGERS.EVENT_MANIPULATION</code>.
     */
    public void setEventManipulation(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>information_schema.TRIGGERS.EVENT_MANIPULATION</code>.
     */
    public String getEventManipulation() {
        return (String) get(3);
    }

    /**
     * Setter for <code>information_schema.TRIGGERS.EVENT_OBJECT_CATALOG</code>.
     */
    public void setEventObjectCatalog(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>information_schema.TRIGGERS.EVENT_OBJECT_CATALOG</code>.
     */
    public String getEventObjectCatalog() {
        return (String) get(4);
    }

    /**
     * Setter for <code>information_schema.TRIGGERS.EVENT_OBJECT_SCHEMA</code>.
     */
    public void setEventObjectSchema(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>information_schema.TRIGGERS.EVENT_OBJECT_SCHEMA</code>.
     */
    public String getEventObjectSchema() {
        return (String) get(5);
    }

    /**
     * Setter for <code>information_schema.TRIGGERS.EVENT_OBJECT_TABLE</code>.
     */
    public void setEventObjectTable(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>information_schema.TRIGGERS.EVENT_OBJECT_TABLE</code>.
     */
    public String getEventObjectTable() {
        return (String) get(6);
    }

    /**
     * Setter for <code>information_schema.TRIGGERS.ACTION_ORDER</code>.
     */
    public void setActionOrder(Long value) {
        set(7, value);
    }

    /**
     * Getter for <code>information_schema.TRIGGERS.ACTION_ORDER</code>.
     */
    public Long getActionOrder() {
        return (Long) get(7);
    }

    /**
     * Setter for <code>information_schema.TRIGGERS.ACTION_CONDITION</code>.
     */
    public void setActionCondition(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>information_schema.TRIGGERS.ACTION_CONDITION</code>.
     */
    public String getActionCondition() {
        return (String) get(8);
    }

    /**
     * Setter for <code>information_schema.TRIGGERS.ACTION_STATEMENT</code>.
     */
    public void setActionStatement(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>information_schema.TRIGGERS.ACTION_STATEMENT</code>.
     */
    public String getActionStatement() {
        return (String) get(9);
    }

    /**
     * Setter for <code>information_schema.TRIGGERS.ACTION_ORIENTATION</code>.
     */
    public void setActionOrientation(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>information_schema.TRIGGERS.ACTION_ORIENTATION</code>.
     */
    public String getActionOrientation() {
        return (String) get(10);
    }

    /**
     * Setter for <code>information_schema.TRIGGERS.ACTION_TIMING</code>.
     */
    public void setActionTiming(String value) {
        set(11, value);
    }

    /**
     * Getter for <code>information_schema.TRIGGERS.ACTION_TIMING</code>.
     */
    public String getActionTiming() {
        return (String) get(11);
    }

    /**
     * Setter for <code>information_schema.TRIGGERS.ACTION_REFERENCE_OLD_TABLE</code>.
     */
    public void setActionReferenceOldTable(String value) {
        set(12, value);
    }

    /**
     * Getter for <code>information_schema.TRIGGERS.ACTION_REFERENCE_OLD_TABLE</code>.
     */
    public String getActionReferenceOldTable() {
        return (String) get(12);
    }

    /**
     * Setter for <code>information_schema.TRIGGERS.ACTION_REFERENCE_NEW_TABLE</code>.
     */
    public void setActionReferenceNewTable(String value) {
        set(13, value);
    }

    /**
     * Getter for <code>information_schema.TRIGGERS.ACTION_REFERENCE_NEW_TABLE</code>.
     */
    public String getActionReferenceNewTable() {
        return (String) get(13);
    }

    /**
     * Setter for <code>information_schema.TRIGGERS.ACTION_REFERENCE_OLD_ROW</code>.
     */
    public void setActionReferenceOldRow(String value) {
        set(14, value);
    }

    /**
     * Getter for <code>information_schema.TRIGGERS.ACTION_REFERENCE_OLD_ROW</code>.
     */
    public String getActionReferenceOldRow() {
        return (String) get(14);
    }

    /**
     * Setter for <code>information_schema.TRIGGERS.ACTION_REFERENCE_NEW_ROW</code>.
     */
    public void setActionReferenceNewRow(String value) {
        set(15, value);
    }

    /**
     * Getter for <code>information_schema.TRIGGERS.ACTION_REFERENCE_NEW_ROW</code>.
     */
    public String getActionReferenceNewRow() {
        return (String) get(15);
    }

    /**
     * Setter for <code>information_schema.TRIGGERS.CREATED</code>.
     */
    public void setCreated(Timestamp value) {
        set(16, value);
    }

    /**
     * Getter for <code>information_schema.TRIGGERS.CREATED</code>.
     */
    public Timestamp getCreated() {
        return (Timestamp) get(16);
    }

    /**
     * Setter for <code>information_schema.TRIGGERS.SQL_MODE</code>.
     */
    public void setSqlMode(String value) {
        set(17, value);
    }

    /**
     * Getter for <code>information_schema.TRIGGERS.SQL_MODE</code>.
     */
    public String getSqlMode() {
        return (String) get(17);
    }

    /**
     * Setter for <code>information_schema.TRIGGERS.DEFINER</code>.
     */
    public void setDefiner(String value) {
        set(18, value);
    }

    /**
     * Getter for <code>information_schema.TRIGGERS.DEFINER</code>.
     */
    public String getDefiner() {
        return (String) get(18);
    }

    /**
     * Setter for <code>information_schema.TRIGGERS.CHARACTER_SET_CLIENT</code>.
     */
    public void setCharacterSetClient(String value) {
        set(19, value);
    }

    /**
     * Getter for <code>information_schema.TRIGGERS.CHARACTER_SET_CLIENT</code>.
     */
    public String getCharacterSetClient() {
        return (String) get(19);
    }

    /**
     * Setter for <code>information_schema.TRIGGERS.COLLATION_CONNECTION</code>.
     */
    public void setCollationConnection(String value) {
        set(20, value);
    }

    /**
     * Getter for <code>information_schema.TRIGGERS.COLLATION_CONNECTION</code>.
     */
    public String getCollationConnection() {
        return (String) get(20);
    }

    /**
     * Setter for <code>information_schema.TRIGGERS.DATABASE_COLLATION</code>.
     */
    public void setDatabaseCollation(String value) {
        set(21, value);
    }

    /**
     * Getter for <code>information_schema.TRIGGERS.DATABASE_COLLATION</code>.
     */
    public String getDatabaseCollation() {
        return (String) get(21);
    }

    // -------------------------------------------------------------------------
    // Record22 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row22<String, String, String, String, String, String, String, Long, String, String, String, String, String, String, String, String, Timestamp, String, String, String, String, String> fieldsRow() {
        return (Row22) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row22<String, String, String, String, String, String, String, Long, String, String, String, String, String, String, String, String, Timestamp, String, String, String, String, String> valuesRow() {
        return (Row22) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return Triggers.TRIGGERS.TRIGGER_CATALOG;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Triggers.TRIGGERS.TRIGGER_SCHEMA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Triggers.TRIGGERS.TRIGGER_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return Triggers.TRIGGERS.EVENT_MANIPULATION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return Triggers.TRIGGERS.EVENT_OBJECT_CATALOG;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return Triggers.TRIGGERS.EVENT_OBJECT_SCHEMA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return Triggers.TRIGGERS.EVENT_OBJECT_TABLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field8() {
        return Triggers.TRIGGERS.ACTION_ORDER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field9() {
        return Triggers.TRIGGERS.ACTION_CONDITION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field10() {
        return Triggers.TRIGGERS.ACTION_STATEMENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field11() {
        return Triggers.TRIGGERS.ACTION_ORIENTATION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field12() {
        return Triggers.TRIGGERS.ACTION_TIMING;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field13() {
        return Triggers.TRIGGERS.ACTION_REFERENCE_OLD_TABLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field14() {
        return Triggers.TRIGGERS.ACTION_REFERENCE_NEW_TABLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field15() {
        return Triggers.TRIGGERS.ACTION_REFERENCE_OLD_ROW;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field16() {
        return Triggers.TRIGGERS.ACTION_REFERENCE_NEW_ROW;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field17() {
        return Triggers.TRIGGERS.CREATED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field18() {
        return Triggers.TRIGGERS.SQL_MODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field19() {
        return Triggers.TRIGGERS.DEFINER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field20() {
        return Triggers.TRIGGERS.CHARACTER_SET_CLIENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field21() {
        return Triggers.TRIGGERS.COLLATION_CONNECTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field22() {
        return Triggers.TRIGGERS.DATABASE_COLLATION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getTriggerCatalog();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getTriggerSchema();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getTriggerName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getEventManipulation();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getEventObjectCatalog();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getEventObjectSchema();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getEventObjectTable();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value8() {
        return getActionOrder();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value9() {
        return getActionCondition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value10() {
        return getActionStatement();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value11() {
        return getActionOrientation();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value12() {
        return getActionTiming();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value13() {
        return getActionReferenceOldTable();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value14() {
        return getActionReferenceNewTable();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value15() {
        return getActionReferenceOldRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value16() {
        return getActionReferenceNewRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value17() {
        return getCreated();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value18() {
        return getSqlMode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value19() {
        return getDefiner();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value20() {
        return getCharacterSetClient();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value21() {
        return getCollationConnection();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value22() {
        return getDatabaseCollation();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TriggersRecord value1(String value) {
        setTriggerCatalog(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TriggersRecord value2(String value) {
        setTriggerSchema(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TriggersRecord value3(String value) {
        setTriggerName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TriggersRecord value4(String value) {
        setEventManipulation(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TriggersRecord value5(String value) {
        setEventObjectCatalog(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TriggersRecord value6(String value) {
        setEventObjectSchema(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TriggersRecord value7(String value) {
        setEventObjectTable(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TriggersRecord value8(Long value) {
        setActionOrder(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TriggersRecord value9(String value) {
        setActionCondition(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TriggersRecord value10(String value) {
        setActionStatement(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TriggersRecord value11(String value) {
        setActionOrientation(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TriggersRecord value12(String value) {
        setActionTiming(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TriggersRecord value13(String value) {
        setActionReferenceOldTable(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TriggersRecord value14(String value) {
        setActionReferenceNewTable(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TriggersRecord value15(String value) {
        setActionReferenceOldRow(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TriggersRecord value16(String value) {
        setActionReferenceNewRow(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TriggersRecord value17(Timestamp value) {
        setCreated(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TriggersRecord value18(String value) {
        setSqlMode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TriggersRecord value19(String value) {
        setDefiner(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TriggersRecord value20(String value) {
        setCharacterSetClient(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TriggersRecord value21(String value) {
        setCollationConnection(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TriggersRecord value22(String value) {
        setDatabaseCollation(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TriggersRecord values(String value1, String value2, String value3, String value4, String value5, String value6, String value7, Long value8, String value9, String value10, String value11, String value12, String value13, String value14, String value15, String value16, Timestamp value17, String value18, String value19, String value20, String value21, String value22) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        value13(value13);
        value14(value14);
        value15(value15);
        value16(value16);
        value17(value17);
        value18(value18);
        value19(value19);
        value20(value20);
        value21(value21);
        value22(value22);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TriggersRecord
     */
    public TriggersRecord() {
        super(Triggers.TRIGGERS);
    }

    /**
     * Create a detached, initialised TriggersRecord
     */
    public TriggersRecord(String triggerCatalog, String triggerSchema, String triggerName, String eventManipulation, String eventObjectCatalog, String eventObjectSchema, String eventObjectTable, Long actionOrder, String actionCondition, String actionStatement, String actionOrientation, String actionTiming, String actionReferenceOldTable, String actionReferenceNewTable, String actionReferenceOldRow, String actionReferenceNewRow, Timestamp created, String sqlMode, String definer, String characterSetClient, String collationConnection, String databaseCollation) {
        super(Triggers.TRIGGERS);

        set(0, triggerCatalog);
        set(1, triggerSchema);
        set(2, triggerName);
        set(3, eventManipulation);
        set(4, eventObjectCatalog);
        set(5, eventObjectSchema);
        set(6, eventObjectTable);
        set(7, actionOrder);
        set(8, actionCondition);
        set(9, actionStatement);
        set(10, actionOrientation);
        set(11, actionTiming);
        set(12, actionReferenceOldTable);
        set(13, actionReferenceNewTable);
        set(14, actionReferenceOldRow);
        set(15, actionReferenceNewRow);
        set(16, created);
        set(17, sqlMode);
        set(18, definer);
        set(19, characterSetClient);
        set(20, collationConnection);
        set(21, databaseCollation);
    }
}