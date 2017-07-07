/*
 * This file is generated by jOOQ.
*/
package com.nextcont.drive.jooq.db.information_schema.tables.pojos;


import java.io.Serializable;
import java.sql.Timestamp;

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
public class Partitions implements Serializable {

    private static final long serialVersionUID = -926787118;

    private String    tableCatalog;
    private String    tableSchema;
    private String    tableName;
    private String    partitionName;
    private String    subpartitionName;
    private ULong     partitionOrdinalPosition;
    private ULong     subpartitionOrdinalPosition;
    private String    partitionMethod;
    private String    subpartitionMethod;
    private String    partitionExpression;
    private String    subpartitionExpression;
    private String    partitionDescription;
    private ULong     tableRows;
    private ULong     avgRowLength;
    private ULong     dataLength;
    private ULong     maxDataLength;
    private ULong     indexLength;
    private ULong     dataFree;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Timestamp checkTime;
    private ULong     checksum;
    private String    partitionComment;
    private String    nodegroup;
    private String    tablespaceName;

    public Partitions() {}

    public Partitions(Partitions value) {
        this.tableCatalog = value.tableCatalog;
        this.tableSchema = value.tableSchema;
        this.tableName = value.tableName;
        this.partitionName = value.partitionName;
        this.subpartitionName = value.subpartitionName;
        this.partitionOrdinalPosition = value.partitionOrdinalPosition;
        this.subpartitionOrdinalPosition = value.subpartitionOrdinalPosition;
        this.partitionMethod = value.partitionMethod;
        this.subpartitionMethod = value.subpartitionMethod;
        this.partitionExpression = value.partitionExpression;
        this.subpartitionExpression = value.subpartitionExpression;
        this.partitionDescription = value.partitionDescription;
        this.tableRows = value.tableRows;
        this.avgRowLength = value.avgRowLength;
        this.dataLength = value.dataLength;
        this.maxDataLength = value.maxDataLength;
        this.indexLength = value.indexLength;
        this.dataFree = value.dataFree;
        this.createTime = value.createTime;
        this.updateTime = value.updateTime;
        this.checkTime = value.checkTime;
        this.checksum = value.checksum;
        this.partitionComment = value.partitionComment;
        this.nodegroup = value.nodegroup;
        this.tablespaceName = value.tablespaceName;
    }

    public Partitions(
        String    tableCatalog,
        String    tableSchema,
        String    tableName,
        String    partitionName,
        String    subpartitionName,
        ULong     partitionOrdinalPosition,
        ULong     subpartitionOrdinalPosition,
        String    partitionMethod,
        String    subpartitionMethod,
        String    partitionExpression,
        String    subpartitionExpression,
        String    partitionDescription,
        ULong     tableRows,
        ULong     avgRowLength,
        ULong     dataLength,
        ULong     maxDataLength,
        ULong     indexLength,
        ULong     dataFree,
        Timestamp createTime,
        Timestamp updateTime,
        Timestamp checkTime,
        ULong     checksum,
        String    partitionComment,
        String    nodegroup,
        String    tablespaceName
    ) {
        this.tableCatalog = tableCatalog;
        this.tableSchema = tableSchema;
        this.tableName = tableName;
        this.partitionName = partitionName;
        this.subpartitionName = subpartitionName;
        this.partitionOrdinalPosition = partitionOrdinalPosition;
        this.subpartitionOrdinalPosition = subpartitionOrdinalPosition;
        this.partitionMethod = partitionMethod;
        this.subpartitionMethod = subpartitionMethod;
        this.partitionExpression = partitionExpression;
        this.subpartitionExpression = subpartitionExpression;
        this.partitionDescription = partitionDescription;
        this.tableRows = tableRows;
        this.avgRowLength = avgRowLength;
        this.dataLength = dataLength;
        this.maxDataLength = maxDataLength;
        this.indexLength = indexLength;
        this.dataFree = dataFree;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.checkTime = checkTime;
        this.checksum = checksum;
        this.partitionComment = partitionComment;
        this.nodegroup = nodegroup;
        this.tablespaceName = tablespaceName;
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

    public String getPartitionName() {
        return this.partitionName;
    }

    public void setPartitionName(String partitionName) {
        this.partitionName = partitionName;
    }

    public String getSubpartitionName() {
        return this.subpartitionName;
    }

    public void setSubpartitionName(String subpartitionName) {
        this.subpartitionName = subpartitionName;
    }

    public ULong getPartitionOrdinalPosition() {
        return this.partitionOrdinalPosition;
    }

    public void setPartitionOrdinalPosition(ULong partitionOrdinalPosition) {
        this.partitionOrdinalPosition = partitionOrdinalPosition;
    }

    public ULong getSubpartitionOrdinalPosition() {
        return this.subpartitionOrdinalPosition;
    }

    public void setSubpartitionOrdinalPosition(ULong subpartitionOrdinalPosition) {
        this.subpartitionOrdinalPosition = subpartitionOrdinalPosition;
    }

    public String getPartitionMethod() {
        return this.partitionMethod;
    }

    public void setPartitionMethod(String partitionMethod) {
        this.partitionMethod = partitionMethod;
    }

    public String getSubpartitionMethod() {
        return this.subpartitionMethod;
    }

    public void setSubpartitionMethod(String subpartitionMethod) {
        this.subpartitionMethod = subpartitionMethod;
    }

    public String getPartitionExpression() {
        return this.partitionExpression;
    }

    public void setPartitionExpression(String partitionExpression) {
        this.partitionExpression = partitionExpression;
    }

    public String getSubpartitionExpression() {
        return this.subpartitionExpression;
    }

    public void setSubpartitionExpression(String subpartitionExpression) {
        this.subpartitionExpression = subpartitionExpression;
    }

    public String getPartitionDescription() {
        return this.partitionDescription;
    }

    public void setPartitionDescription(String partitionDescription) {
        this.partitionDescription = partitionDescription;
    }

    public ULong getTableRows() {
        return this.tableRows;
    }

    public void setTableRows(ULong tableRows) {
        this.tableRows = tableRows;
    }

    public ULong getAvgRowLength() {
        return this.avgRowLength;
    }

    public void setAvgRowLength(ULong avgRowLength) {
        this.avgRowLength = avgRowLength;
    }

    public ULong getDataLength() {
        return this.dataLength;
    }

    public void setDataLength(ULong dataLength) {
        this.dataLength = dataLength;
    }

    public ULong getMaxDataLength() {
        return this.maxDataLength;
    }

    public void setMaxDataLength(ULong maxDataLength) {
        this.maxDataLength = maxDataLength;
    }

    public ULong getIndexLength() {
        return this.indexLength;
    }

    public void setIndexLength(ULong indexLength) {
        this.indexLength = indexLength;
    }

    public ULong getDataFree() {
        return this.dataFree;
    }

    public void setDataFree(ULong dataFree) {
        this.dataFree = dataFree;
    }

    public Timestamp getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Timestamp getCheckTime() {
        return this.checkTime;
    }

    public void setCheckTime(Timestamp checkTime) {
        this.checkTime = checkTime;
    }

    public ULong getChecksum() {
        return this.checksum;
    }

    public void setChecksum(ULong checksum) {
        this.checksum = checksum;
    }

    public String getPartitionComment() {
        return this.partitionComment;
    }

    public void setPartitionComment(String partitionComment) {
        this.partitionComment = partitionComment;
    }

    public String getNodegroup() {
        return this.nodegroup;
    }

    public void setNodegroup(String nodegroup) {
        this.nodegroup = nodegroup;
    }

    public String getTablespaceName() {
        return this.tablespaceName;
    }

    public void setTablespaceName(String tablespaceName) {
        this.tablespaceName = tablespaceName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Partitions (");

        sb.append(tableCatalog);
        sb.append(", ").append(tableSchema);
        sb.append(", ").append(tableName);
        sb.append(", ").append(partitionName);
        sb.append(", ").append(subpartitionName);
        sb.append(", ").append(partitionOrdinalPosition);
        sb.append(", ").append(subpartitionOrdinalPosition);
        sb.append(", ").append(partitionMethod);
        sb.append(", ").append(subpartitionMethod);
        sb.append(", ").append(partitionExpression);
        sb.append(", ").append(subpartitionExpression);
        sb.append(", ").append(partitionDescription);
        sb.append(", ").append(tableRows);
        sb.append(", ").append(avgRowLength);
        sb.append(", ").append(dataLength);
        sb.append(", ").append(maxDataLength);
        sb.append(", ").append(indexLength);
        sb.append(", ").append(dataFree);
        sb.append(", ").append(createTime);
        sb.append(", ").append(updateTime);
        sb.append(", ").append(checkTime);
        sb.append(", ").append(checksum);
        sb.append(", ").append(partitionComment);
        sb.append(", ").append(nodegroup);
        sb.append(", ").append(tablespaceName);

        sb.append(")");
        return sb.toString();
    }
}
