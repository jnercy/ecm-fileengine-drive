/*
 * This file is generated by jOOQ.
*/
package com.nextcont.drive.jooq.db.information_schema.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;

import org.jooq.types.ULong;


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
public class Columns implements Serializable {

    private static final long serialVersionUID = -246806029;

    private String tableCatalog;
    private String tableSchema;
    private String tableName;
    private String columnName;
    private ULong  ordinalPosition;
    private String columnDefault;
    private String isNullable;
    private String dataType;
    private ULong  characterMaximumLength;
    private ULong  characterOctetLength;
    private ULong  numericPrecision;
    private ULong  numericScale;
    private String characterSetName;
    private String collationName;
    private String columnType;
    private String columnKey;
    private String extra;
    private String privileges;
    private String columnComment;

    public Columns() {}

    public Columns(Columns value) {
        this.tableCatalog = value.tableCatalog;
        this.tableSchema = value.tableSchema;
        this.tableName = value.tableName;
        this.columnName = value.columnName;
        this.ordinalPosition = value.ordinalPosition;
        this.columnDefault = value.columnDefault;
        this.isNullable = value.isNullable;
        this.dataType = value.dataType;
        this.characterMaximumLength = value.characterMaximumLength;
        this.characterOctetLength = value.characterOctetLength;
        this.numericPrecision = value.numericPrecision;
        this.numericScale = value.numericScale;
        this.characterSetName = value.characterSetName;
        this.collationName = value.collationName;
        this.columnType = value.columnType;
        this.columnKey = value.columnKey;
        this.extra = value.extra;
        this.privileges = value.privileges;
        this.columnComment = value.columnComment;
    }

    public Columns(
        String tableCatalog,
        String tableSchema,
        String tableName,
        String columnName,
        ULong  ordinalPosition,
        String columnDefault,
        String isNullable,
        String dataType,
        ULong  characterMaximumLength,
        ULong  characterOctetLength,
        ULong  numericPrecision,
        ULong  numericScale,
        String characterSetName,
        String collationName,
        String columnType,
        String columnKey,
        String extra,
        String privileges,
        String columnComment
    ) {
        this.tableCatalog = tableCatalog;
        this.tableSchema = tableSchema;
        this.tableName = tableName;
        this.columnName = columnName;
        this.ordinalPosition = ordinalPosition;
        this.columnDefault = columnDefault;
        this.isNullable = isNullable;
        this.dataType = dataType;
        this.characterMaximumLength = characterMaximumLength;
        this.characterOctetLength = characterOctetLength;
        this.numericPrecision = numericPrecision;
        this.numericScale = numericScale;
        this.characterSetName = characterSetName;
        this.collationName = collationName;
        this.columnType = columnType;
        this.columnKey = columnKey;
        this.extra = extra;
        this.privileges = privileges;
        this.columnComment = columnComment;
    }

    public String getTableCatalog() {
        return this.tableCatalog;
    }

    public void setTableCatalog(String tableCatalog) {
        this.tableCatalog = tableCatalog;
    }

    public String getTableSchema() {
        return this.tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return this.columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public ULong getOrdinalPosition() {
        return this.ordinalPosition;
    }

    public void setOrdinalPosition(ULong ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }

    public String getColumnDefault() {
        return this.columnDefault;
    }

    public void setColumnDefault(String columnDefault) {
        this.columnDefault = columnDefault;
    }

    public String getIsNullable() {
        return this.isNullable;
    }

    public void setIsNullable(String isNullable) {
        this.isNullable = isNullable;
    }

    public String getDataType() {
        return this.dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public ULong getCharacterMaximumLength() {
        return this.characterMaximumLength;
    }

    public void setCharacterMaximumLength(ULong characterMaximumLength) {
        this.characterMaximumLength = characterMaximumLength;
    }

    public ULong getCharacterOctetLength() {
        return this.characterOctetLength;
    }

    public void setCharacterOctetLength(ULong characterOctetLength) {
        this.characterOctetLength = characterOctetLength;
    }

    public ULong getNumericPrecision() {
        return this.numericPrecision;
    }

    public void setNumericPrecision(ULong numericPrecision) {
        this.numericPrecision = numericPrecision;
    }

    public ULong getNumericScale() {
        return this.numericScale;
    }

    public void setNumericScale(ULong numericScale) {
        this.numericScale = numericScale;
    }

    public String getCharacterSetName() {
        return this.characterSetName;
    }

    public void setCharacterSetName(String characterSetName) {
        this.characterSetName = characterSetName;
    }

    public String getCollationName() {
        return this.collationName;
    }

    public void setCollationName(String collationName) {
        this.collationName = collationName;
    }

    public String getColumnType() {
        return this.columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnKey() {
        return this.columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getExtra() {
        return this.extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getPrivileges() {
        return this.privileges;
    }

    public void setPrivileges(String privileges) {
        this.privileges = privileges;
    }

    public String getColumnComment() {
        return this.columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Columns (");

        sb.append(tableCatalog);
        sb.append(", ").append(tableSchema);
        sb.append(", ").append(tableName);
        sb.append(", ").append(columnName);
        sb.append(", ").append(ordinalPosition);
        sb.append(", ").append(columnDefault);
        sb.append(", ").append(isNullable);
        sb.append(", ").append(dataType);
        sb.append(", ").append(characterMaximumLength);
        sb.append(", ").append(characterOctetLength);
        sb.append(", ").append(numericPrecision);
        sb.append(", ").append(numericScale);
        sb.append(", ").append(characterSetName);
        sb.append(", ").append(collationName);
        sb.append(", ").append(columnType);
        sb.append(", ").append(columnKey);
        sb.append(", ").append(extra);
        sb.append(", ").append(privileges);
        sb.append(", ").append(columnComment);

        sb.append(")");
        return sb.toString();
    }
}
