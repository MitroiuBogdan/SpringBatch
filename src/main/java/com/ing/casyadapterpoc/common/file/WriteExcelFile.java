package com.ing.casyadapterpoc.common.file;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.ing.casyadapterpoc.common.domain.Vendor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Slf4j
public class WriteExcelFile {

    public static void processLive2(List<? extends Object> objList, String path) {

    }

    public static void processLive3(List<? extends Object> objList, Vendor vendor, boolean overrideFile) {

    }

    public static void writeObjectsToXcel(List<? extends Object> objList, String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(objList);
        Workbook workbook;
        Sheet sheet;

        try (FileInputStream inputStream = new FileInputStream(path)) {
            workbook = WorkbookFactory.create(inputStream);
            sheet = workbook.getSheetAt(0);
        } catch (Exception e) {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet();
        }
        ArrayNode arrayNode = (ArrayNode) objectMapper.readTree(jsonStr);

        log.info("Initial row number: {}", sheet.getLastRowNum());

        if (sheet.getLastRowNum() < 1) {
            Row headerRow = sheet.createRow(0);
            insertValueIntoCell(arrayNode.get(0), RowType.HEADER, headerRow, 0);
        }

        int rowIndex = sheet.getLastRowNum() + 1;
        for (JsonNode node : arrayNode) {
            Row row = sheet.createRow(rowIndex);
            insertValueIntoCell(node, RowType.ROW, row, 0);
            rowIndex++;
        }

        FileOutputStream outputStream = new FileOutputStream(path);
        workbook.write(outputStream);
    }

    private static int insertValueIntoCell(JsonNode nodes, RowType rowType, Row row, int startCellIndex) throws JsonProcessingException {
        for (Iterator<Map.Entry<String, JsonNode>> it = nodes.fields(); it.hasNext(); ) {
            Map.Entry<String, JsonNode> field = it.next();
            if (!field.getValue().isValueNode() && !field.getValue().isArray()) {
                try {
                    startCellIndex = insertValueIntoCell(field.getValue(), rowType, row, startCellIndex);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            } else {
                String cellValue = rowType == RowType.HEADER ? field.getKey() : field.getValue().toString();
                createCell(row, startCellIndex, cellValue);
                log.info("index :{} - key: {}, value: {}", startCellIndex, field.getKey(), cellValue);
                startCellIndex++;
            }
        }
        return startCellIndex;
    }

    private static void createCell(Row row, int columnIndex, String values) {
        Cell cell = row.createCell(columnIndex);
        cell.setCellValue(values);
    }

    private enum RowType {
        HEADER,
        ROW
    }
}
