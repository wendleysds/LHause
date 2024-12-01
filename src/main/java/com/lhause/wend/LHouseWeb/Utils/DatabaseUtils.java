package com.lhause.wend.LHouseWeb.Utils;

import java.sql.SQLException;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author Wendley S
 */
public class DatabaseUtils {

    public static String getTableNameFromException(DataIntegrityViolationException ex) {

        Throwable cause = ex.getRootCause();
        if (cause instanceof SQLException) {
            String message = cause.getMessage();

            if (message.contains("for key")) {
                String[] parts = message.split("for key ");

                if (parts.length > 1) {
                    String uniqueKey = parts[1].replace("'", "").trim();

                    String tableName = extractTableName(uniqueKey);
                    return tableName;
                }
            }
        }
        return "Tabela não identificada";
    }

    public static String getUniqueKeyNameFromException(DataIntegrityViolationException ex) {
        
        Throwable cause = ex.getRootCause();
        if (cause instanceof SQLException) {
            String message = cause.getMessage();
            
            if (message.contains("for key")) {
                String[] parts = message.split("for key ");

                if (parts.length > 1) {                   
                    String uniqueKey = parts[1].replace("'", "").trim();
                    
                    return extractKeyName(uniqueKey);
                }
            }
        }
        return "Chave não identificada";
    }

    private static String extractTableName(String uniqueKey) {
        if (uniqueKey.contains(".")) {
            return uniqueKey.split("\\.")[0];
        }

        return "Tabela não identificada";
    }

    private static String extractKeyName(String uniqueKey) {
        if (uniqueKey.contains(".")) {
            return uniqueKey.split("\\.")[1];
        }

        return uniqueKey;
    }
}
