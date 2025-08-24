package com.geopokrovskiy.payments_service.utils;

public class FakeProviderTestData {

    // REQUESTS

    // TOP_UP
    public static String getCreateFakeProviderTopUpRequestBodyCorrect() {
        return """
                {
                    "first_name": "Test",
                    "last_name": "Testov",
                    "account_id": "8a17ff45-f174-428f-a8c8-a2f5a90883aa",
                    "cvv": 123,
                    "expiration_date": "07/27",
                    "card_number": 1234567812345678,
                    "amount": 1.59,
                    "country": "TST",
                    "username": "user1",
                    "password": "Password$"
                }
                """;
    }

    public static String getCreateFakeProviderTopUpRequestBodyWithoutPassword() {
        return """
                {
                    "first_name": "Test",
                    "last_name": "Testov",
                    "account_id": "8a17ff45-f174-428f-a8c8-a2f5a90883aa",
                    "cvv": 123,
                    "expiration_date": "07/27",
                    "card_number": 1234567812345678,
                    "amount": 1.59,
                    "country": "TST",
                    "username": "user1"
                }
                """;
    }

    public static String getCreateFakeProviderTopUpRequestBodyWithoutIncorrectField() {

        // cvv -> foo_bar is incorrect

        return """
                {
                    "first_name": "Test",
                    "last_name": "Testov",
                    "account_id": "8a17ff45-f174-428f-a8c8-a2f5a90883aa",
                    "cvv": "foo_bar",
                    "expiration_date": "07/27",
                    "card_number": 1234567812345678,
                    "amount": 1.59,
                    "country": "TST",
                    "username": "user1"
                }
                """;
    }

    // PAY_OUT
    public static String getCreateFakeProviderPayOutRequestBodyCorrect() {
        return """
                {
                    "first_name": "Test",
                    "last_name": "Testov",
                    "account_id": "8a17ff45-f174-428f-a8c8-a2f5a90883aa",
                    "card_number": 1234567812345678,
                    "expiration_date": "07/27",
                    "amount": 1.59,
                    "country": "TST",
                    "username": "user1",
                    "password": "Password$"
                }
                """;
    }

    public static String getCreateFakeProviderPayOutRequestBodyWithoutPassword() {
        return """
                {
                    "first_name": "Test",
                    "last_name": "Testov",
                    "account_id": "8a17ff45-f174-428f-a8c8-a2f5a90883aa",
                    "card_number": 1234567812345678,
                    "amount": 1.59,
                    "country": "TST",
                    "username": "user1"
                }
                """;
    }

    public static String getCreateFakeProviderPayOutRequestBodyWithoutIncorrectField() {

        // card_number -> foo_bar is incorrect

        return """
                {
                    "first_name": "Test",
                    "last_name": "Testov",
                    "account_id": "8a17ff45-f174-428f-a8c8-a2f5a90883aa",
                    "card_number": "foo_bar",
                    "amount": 1.59,
                    "country": "TST",
                    "username": "user1"
                }
                """;
    }

    // RESPONSES

    // TOP_UP
    public static String getFakeProviderTopUpResponseBody200() {
        return """
                {
                  "id" : "205aca4f-55af-42a5-a5de-4ffacb11bb11",
                  "transaction_status" : "IN_PROGRESS"
                }
                """;
    }


    // PAY_OUT
    public static String getFakeProviderPayOutResponseBody200() {
        return """
                {
                  "id" : "205aca4f-55af-42a5-a5de-4ffacb11bb11",
                  "transaction_status" : "SUCCESS"
                }
                """;
    }
}
