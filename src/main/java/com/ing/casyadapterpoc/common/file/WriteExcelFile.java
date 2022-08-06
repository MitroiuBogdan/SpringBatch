package com.ing.casyadapterpoc.common.file;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

@Slf4j
public class WriteExcelFile {

    public static void processLive(Object obj, String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Workbook workbook;
        Sheet sheet;

        try (FileInputStream inputStream = new FileInputStream("src/test/resources/" + fileName)) {
            workbook = WorkbookFactory.create(inputStream);
            sheet = workbook.getSheetAt(0);
        } catch (Exception e) {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet();
        }
        int initialRowNumber = sheet.getLastRowNum() + 1;

        log.info("Initial row number: {}", initialRowNumber);
        String jsonStr = objectMapper.writeValueAsString(obj);
        ObjectNode node = (ObjectNode) objectMapper.readTree(jsonStr);

        if (initialRowNumber < 1) {
            Row headerRow = sheet.createRow(0);
            createCellForHeader(node, "main", headerRow, 0);
        }

        for (
                int i = initialRowNumber + 1;
                i < initialRowNumber + 20; i++) {
            Row row = sheet.createRow(i);
            createCellFromNode(node, "main", row, 0);
        }

        FileOutputStream outputStream = new FileOutputStream("src/test/resources/" + fileName);
        workbook.write(outputStream);
    }

    public static int createCellFromNode(JsonNode nodes, String name, Row row, int cellIndex) throws JsonProcessingException {
        for (Iterator<Map.Entry<String, JsonNode>> it = nodes.fields(); it.hasNext(); ) {
            Map.Entry<String, JsonNode> field = it.next();
            if (!field.getValue().isValueNode()) {
                try {
                    cellIndex = createCellFromNode(field.getValue(), field.getKey(), row, cellIndex);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            } else {
                createCell(row, cellIndex, field.getValue().toString());
                log.info("index :{} - Object: {} key: {}, value: {}", cellIndex, name, field.getKey(), field.getValue().toString());
                cellIndex++;
            }
        }
        return cellIndex;
    }

    private static int createCellForHeader(JsonNode nodes, String name, Row row, int cellIndex) throws JsonProcessingException {
        for (Iterator<Map.Entry<String, JsonNode>> it = nodes.fields(); it.hasNext(); ) {
            Map.Entry<String, JsonNode> field = it.next();
            if (!field.getValue().isValueNode()) {
                try {
                    cellIndex = createCellForHeader(field.getValue(), field.getKey(), row, cellIndex);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            } else {
                createCell(row, cellIndex, field.getKey());
                log.info("index :{} - Object: {} key: {}, value: {}", cellIndex, name, field.getKey(), field.getValue().toString());
                cellIndex++;
            }
        }
        return cellIndex;
    }

    private static void createCell(Row row, int column, String values) {
        Cell cell = row.createCell(column);
        cell.setCellValue(values);
    }
}
