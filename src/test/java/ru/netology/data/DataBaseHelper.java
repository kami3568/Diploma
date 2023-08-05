package ru.netology.data;

import lombok.SneakyThrows;
import lombok.Value;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseHelper {
    private final static QueryRunner runner = new QueryRunner();
    private final static Connection conn = connection();

    public void dropAll() {
        dropDataBase();
    }

    @SneakyThrows
    private static Connection connection() {
        return DriverManager.getConnection
                (System.getProperty("dataBase.url"),
                        System.getProperty("username"),
                        System.getProperty("password")
                );
    }

    @Value
    public static class PaymentEntityInfo {
        int amountFromPaymentEntity;
        String createdDateFromPaymentEntity;
        String statusFromPaymentEntity;
        String transactionIdFromPaymentEntity;
    }

    @Value
    public static class CreditRequestEntityInfo {
        String bankIdFromCreditRequestEntity;
        String createdDateFromCreditRequestEntity;
        String statusFromCreditRequestEntity;
    }

    @Value
    public static class OrderEntityInfo {
        String createdDateFromOrderEntity;
        String creditIdFromOrderEntity;
        String paymentIdFromOrderEntity;
    }

    @SneakyThrows
    public static PaymentEntityInfo getPaymentEntityInfo() {
        return new PaymentEntityInfo(
                getAmountFromPaymentEntity(),
                getCreatedDateFromPaymentEntity(),
                getStatusFromPaymentEntity(),
                getTransactionIdFromPaymentEntity()
        );
    }

    @SneakyThrows
    public static CreditRequestEntityInfo getCreditRequestEntity() {
        return new CreditRequestEntityInfo(
                getBankIdFromCreditRequestEntity(),
                getCreatedDateFromCreditRequestEntity(),
                getStatusFromCreditRequestEntity()
        );
    }

    @SneakyThrows
    public static OrderEntityInfo getOrderEntityInfo() {
        return new OrderEntityInfo(
                getCreatedDateFromOrderEntity(),
                getCreditIdFromOrderEntity(),
                getPaymentIdFromOrderEntity()
        );
    }


    @SneakyThrows
    private static int getAmountFromPaymentEntity() {
        return runner.query(conn, "SELECT amount FROM payment_entity", new ScalarHandler<>());
    }

    @SneakyThrows
    private static String getCreatedDateFromPaymentEntity() {
        return runner.query(conn, "SELECT created FROM payment_entity", new ScalarHandler<>()).toString();
    }

    @SneakyThrows
    private static String getStatusFromPaymentEntity() {
        return runner.query(conn, "SELECT status FROM payment_entity", new ScalarHandler<>());
    }

    @SneakyThrows
    private static String getTransactionIdFromPaymentEntity() {
        return runner.query(conn, "SELECT transaction_id FROM payment_entity", new ScalarHandler<>());
    }

    @SneakyThrows
    private static String getBankIdFromCreditRequestEntity() {
        return runner.query(conn, "SELECT bank_id FROM credit_request_entity", new ScalarHandler<>());
    }

    @SneakyThrows
    private static String getCreatedDateFromCreditRequestEntity() throws NullPointerException {
        return runner.query(conn, "SELECT created FROM credit_request_entity", new ScalarHandler<>()).toString();
    }

    @SneakyThrows
    private static String getStatusFromCreditRequestEntity() {
        return runner.query(conn, "SELECT status FROM credit_request_entity", new ScalarHandler<>());
    }

    @SneakyThrows
    private static String getCreatedDateFromOrderEntity() {
        return runner.query(conn, "SELECT created FROM order_entity", new ScalarHandler<>()).toString();
    }

    @SneakyThrows
    private static String getCreditIdFromOrderEntity() {
        return runner.query(conn, "SELECT credit_id FROM order_entity", new ScalarHandler<>());
    }

    @SneakyThrows
    private static String getPaymentIdFromOrderEntity() {
        return runner.query(conn, "SELECT payment_id FROM order_entity", new ScalarHandler<>());
    }

    @SneakyThrows
    private static void dropDataBase() {
        runner.update(conn, "DELETE FROM credit_request_entity");
        runner.update(conn, "DELETE FROM order_entity");
        runner.update(conn, "DELETE FROM payment_entity");
    }

}